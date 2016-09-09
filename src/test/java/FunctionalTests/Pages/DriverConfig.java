package FunctionalTests.Pages;

import com.Example.JunitRestAssuredDemo.ReadFromPropertiesFile;
import com.saucelabs.saucerest.SauceREST;
import cucumber.api.Scenario;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by vishal on 9/9/16.
 */
public class DriverConfig {


    public static WebDriver driver;
    public static Scenario scenario;
    public String sessionId;

    // public static String exeEnvironment = System.getProperty("environment");
    public static String exeEnvironment = "uat";
    public String exeBrowserName = System.getProperty("browserName");
    public String exePlatfom = System.getProperty("platform");
    public static final String CHROME_LOCATION = "/src/test/resources/chromedriver";
    public static ReadFromPropertiesFile readFromPropertiesFile = new ReadFromPropertiesFile(
            getExeEnvironment());

    public static WebDriver getDriver() {
        return driver;
    }

    public static Scenario getScenario() {
        return scenario;
    }

    public static String getExeEnvironment() {
        return exeEnvironment;
    }

    public static FirefoxDriver setFirefoxDriver() {
        return new FirefoxDriver();
    }

    public static ChromeDriver setChromeDriver() {
        return new ChromeDriver();
    }

    public DriverConfig() {
        // Empty constructor
    }

    public void prepare(Scenario scenario) throws Exception {
        if (readFromPropertiesFile.readPropertiesFile("executeLocal")
                .equalsIgnoreCase("y")) {
            executeOnLocal();

        } else {
            executeOnSauce(scenario);
        }
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

    }

    public void executeOnLocal() {
        try {
            System.out.println("Executing on local system");
            // Code to read scenario and feature name

            if (readFromPropertiesFile.readPropertiesFile("browser")
                    .equalsIgnoreCase("firefox")) {
                System.setProperty("webdriver.firefox.profile", "default");
                driver = setFirefoxDriver();
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();
                System.out.println("Opening browser "
                        + readFromPropertiesFile.readPropertiesFile("browser"));

            }
            if (readFromPropertiesFile.readPropertiesFile("browser")
                    .equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver",
                        System.getProperty("user.dir") + CHROME_LOCATION);
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Dimension screenResolution = new Dimension((int) toolkit
                        .getScreenSize().getWidth(), (int) toolkit
                        .getScreenSize().getHeight());
                driver = new ChromeDriver();
                driver.manage().window().setSize(screenResolution);
                System.out.println("Opening browser "
                        + readFromPropertiesFile.readPropertiesFile("browser"));
            }
            if (readFromPropertiesFile.readPropertiesFile("browser")
                    .equalsIgnoreCase("safari")) {
                driver = new SafariDriver();
                driver.manage().deleteAllCookies();
                System.out.println("Opening browser "
                        + readFromPropertiesFile.readPropertiesFile("browser"));
            }

        } catch (Exception e) {
            System.out
                    .println("Unable to execute on local. Please check the configuration "
                            + e.getMessage());
        }
    }

    public void executeOnSauce(Scenario scenario) {
        // Choose the browser, version, and platform to test
        try {

            // Choose the browser, version, and platform to test
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setCapability("version", "30");
            capabilities.setCapability("platform", Platform.WIN8);
            // Create the connection to Sauce Labs to run the tests
            driver = new RemoteWebDriver(
                    new URL(
                            "http://<username>:<user_key>@<sauceLabURL>.com:<port>/wd/hub"),
                    capabilities);
        } catch (Exception e) {
            System.out
                    .println("Unable to execute on Sauce Labs. Please check the Sauce lab configuration "
                            + e.getMessage());
        }
    }

    public void cleanUp(Scenario scenario) throws InterruptedException {
        String jobId = ((RemoteWebDriver) driver).getSessionId().toString();
        SauceREST client = new SauceREST(
                readFromPropertiesFile.readPropertiesFile("userName"),
                readFromPropertiesFile.readPropertiesFile("accessKey"));
        Map<String, Object> sauceJob = new HashMap<String, Object>();
        sauceJob.put("Name", "Scenario: ");

        // Add Screen shot for failure
        if (scenario.isFailed()) {
            try {

                if (readFromPropertiesFile.readPropertiesFile("executeLocal")
                        .equalsIgnoreCase("y")) {
                    byte[] screenshot = ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.BYTES);
                    scenario.embed(screenshot, "image/png");
                } else {
                    client.jobFailed(jobId);
                }

            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots
                        .getMessage());
            }

        } else {
            if (!readFromPropertiesFile.readPropertiesFile("executeLocal")
                    .equalsIgnoreCase("y")) {
                client.jobPassed(jobId);
            }
        }
        if (!readFromPropertiesFile.readPropertiesFile("executeLocal")
                .equalsIgnoreCase("y")) {
            client.updateJobInfo(jobId, sauceJob);
        }

        System.out.println("Closing browser ");
        System.out.println("*****************************************");
        driver.close();
        driver.quit();
        driver = null;
    }

}
