package FunctionalTests.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Created by vishal on 9/9/16.
 */

public class AmazonHomepage extends DriverConfig{

    @FindBy(how = How.XPATH, using = "(//*[@id='col3'])//a[@href='/property-for-rent/residential/apartmentflat/']")
    public WebElement propertyForRentLink;
    @FindBy(how = How.XPATH, using = "//select[@name='sort_by']")
    public WebElement sortBY;
    @FindBy(how = How.XPATH, using = "//*[@id='as_values_id_places__id__in']")
    public WebElement area;
    @FindBy(how = How.XPATH, using = "//li[@id='as-result-item-65']")
    public WebElement areaMarinWalk;
    @FindBy(how = How.XPATH, using = "//*[@id='advanced-search-header-button']")
    public WebElement advancedOption;
    @FindBy(how = How.XPATH, using = "//*[@id='bathrooms__gte:swfield']")
    public WebElement minBathroomListbox;
    @FindBy(how = How.XPATH, using = "//input[@id='search-button-mini']")
    public WebElement searchButton;
    @FindBy(xpath = ".//*[@id='results-list']/div")
    private List<WebElement> resultSet;
    @FindBy(xpath = "//*[@id='as-selection-0000']")
    private List<WebElement> crossMark;
    @FindBy(how = How.XPATH, using = "//*[@id='listing-details-list']/ul[1]/li[2]/strong")
    public WebElement bathroomCount;

    public AmazonHomepage() {
        PageFactory.initElements(driver, this);
    }

    public void initElements() {
        PageFactory.initElements(driver, this);
    }

    public AmazonHomepage navigateTo(String arg1) {
        System.out.println("Navigating to " + arg1);
        driver.get(arg1);
        return this;
    }

    public void clickApartmentForRent() {
        propertyForRentLink.click();
    }

    public void sortByLowestPrice(String arg1) throws InterruptedException {
        new Select(sortBY).selectByVisibleText(arg1);
        sortBY.sendKeys(Keys.RETURN);
        Thread.sleep(3000);
    }

    public void selectArea(String arg1) throws InterruptedException {
        area.click();
        area.clear();
        Thread.sleep(2000);
        area.sendKeys(" ");
        area.sendKeys(arg1);
        Thread.sleep(2000);
        area.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        // area.sendKeys(Keys.ENTER);
        // Thread.sleep(2000);
        // isWebElementExists(crossMark);
    }

    public boolean isWebElementExists(List<WebElement> webelement) {
        try {
            return webelement.size() > 0;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickAdvancedOption() throws InterruptedException {
        advancedOption.click();
        Thread.sleep(2000);
    }

    public void selectminBathroom() {
        new Select(minBathroomListbox).selectByVisibleText("2");
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void verifyResultPage() {

    }

    public void selectLastItem() {
        int count = resultSet.size();
        String xPath = ".//*[@id='results-list']/div[10]/div/div/div[2]/div[1]/a/div";
        driver.findElement(By.xpath(xPath)).click();
    }

    public boolean verifyBathroomCount() throws InterruptedException {
        int numberOfRecords = Integer.parseInt(bathroomCount.getText().trim());
        Thread.sleep(2000);
        return numberOfRecords >= 2 ? true : false;
    }

}
