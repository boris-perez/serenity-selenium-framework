package com.dharbor.serenity.steps;

import com.dharbor.setForms.entities.User;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class LoginSteps {

    @Given("I load SET Forms portal")
    public void loadPortal() {

    }

    /**
     * Logs in user with the given credentials.
     *
     * @param user - User entity.
     */
    @When("I login with the following credentials:")
    public void login(User user) {

    }
}
