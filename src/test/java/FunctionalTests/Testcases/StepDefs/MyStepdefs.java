package FunctionalTests.Testcases.StepDefs;

import FunctionalTests.Pages.AmazonHomepage;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static junit.framework.TestCase.assertTrue;



/**
 * Created by vishal on 9/9/16.
 */

public class MyStepdefs {
    public AmazonHomepage apptest;

    @Before
    public void beforeScenario(Scenario scenario) throws Exception {
        apptest = new AmazonHomepage();
        apptest.prepare(scenario);
        apptest.initElements();
    }

    @After
    public void afterScenario(Scenario scenario) throws Throwable {
        apptest.cleanUp(scenario);
    }

    @Given("^I go to \"([^\"]*)\"$")
    public void I_go_to(String arg1) throws Throwable {
        arg1 = "http://www.amazon.com/";
        apptest.navigateTo(arg1);
    }

    @When("^I click on 'Apartment/Flat for Rent' link$")
    public void I_click_on_Apartment_Flat_for_Rent_link() throws Throwable {
        System.out.println("I click on 'Apartment/Flat for Rent' link");
        apptest.clickApartmentForRent();
    }

    @When("^I select sort by \"([^\"]*)\"$")
    public void I_select_sort_by_Price_Lowest_to_Highest(String arg1)
            throws Throwable {
        System.out.println("I select sort by "+ arg1);
        apptest.sortByLowestPrice(arg1);
    }

    @When("^I select 'Neighborhoods' as \"([^\"]*)\"$")
    public void I_select_Neighborhoods_as(String arg1) throws Throwable {
        System.out.println("I select 'Neighborhoods' as "+ arg1);
        apptest.selectArea(arg1);
    }

    @When("^I click on 'Advanced Option' in search window$")
    public void I_click_on_Advanced_Option_in_search_window() throws Throwable {
        System.out.println("I click on 'Advanced Option' in search window");
        apptest.clickAdvancedOption();
    }

    @When("^I select minimum bathrooms as two$")
    public void I_select_minimum_bathrooms_as_two() throws Throwable {
        System.out.println("I select minimum bathrooms as two");
        apptest.selectminBathroom();
    }

    @When("^I click on 'Search' button$")
    public void I_click_on_Search_button() throws Throwable {
        System.out.println("I click on 'Search' button");
        apptest.clickSearchButton();
    }

    @Then("^result page should display least expensive apartment in order which has at least two bathrooms$")
    public void result_page_should_display_least_expensive_apartment_in_order_which_has_at_least_two_bathrooms()
            throws Throwable {
        System.out.println("result page should display least expensive apartment in order which has at least two bathrooms");
        apptest.verifyResultPage();
    }

    @When("^I go to last item from result set$")
    public void I_go_to_last_item_from_result_set() throws Throwable {
        System.out.println("I go to last item from result set");
        apptest.selectLastItem();
    }

    @Then("^I verify it has two bathrooms$")
    public void I_verify_it_has_two_bathrooms() throws Throwable {
        System.out.println("I verify it has two bathrooms");
        assertTrue("Something went wrong", apptest.verifyBathroomCount());

    }

    @Given("^I am on Amazon homepage$")
    public void I_am_on_Amazon_homepage() throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }
}