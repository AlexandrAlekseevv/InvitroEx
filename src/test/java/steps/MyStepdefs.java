package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.invitro.MainPage;
import pages.invitro.RadiologyPage;

public class MyStepdefs {
    private RadiologyPage radiologyPage;
    private MainPage mainPage;
    @Given("open the Radiology page")
    public void openTheRadiology() {
        radiologyPage =new RadiologyPage();
        radiologyPage.openPage();
    }

    @When("click through all the items in the medical services menu")
    public void clickThroughAllTheItemsInTheMedicalServicesMenu() {
    }

    @Then("All menu items should be accessible")
    public void allMenuItemsShouldBeAccessible() {
        Assertions.assertTrue(radiologyPage.clickAllMenuItemsSuccess());
    }

    @Given("open the Invitro main page")
    public void openTheInvitroWebsite() {
        mainPage = new MainPage();
        mainPage.openPage();
    }

    @When("change the city to {string}")
    public void changeTheCityTo(String city) {
        mainPage.changeCity(city);
    }

    @Then("the city should be changed to {string}")
    public void theCityShouldBeChangedTo(String city) {
        Assertions.assertEquals(mainPage.getSelectedCityName(),city);
    }
}
