package com.weather.ql.stepdefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.weather.ql.actions.get_weather_info.GetWeatherInfoToJSonFile;
import com.weather.ql.actions.navigation.NavigateTo;
import com.weather.ql.core.helper.Utilities;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.core.webdriver.RemoteDriver;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.thucydides.core.environment.SystemEnvironmentVariables;
import net.thucydides.core.util.EnvironmentVariables;

public class FilterTenDayInformationStepDefinitions {

    String filePath;

    @Given("{actor} does navigate to weather site")
    public void williamDoesNavigateToWeatherSite(Actor actor) {
        actor.wasAbleTo(
                NavigateTo.theWeatherDotComHomePage());
    }

    @Given("{actor} does navigate to ten days page")
    public void williamDoesNavigateToTenDaysPage(Actor actor) {
        actor.wasAbleTo(
                NavigateTo.theTenDaysPage());
    }

    @When("{actor} does retrieve weather information for {int} days and ignore today is {}")
    public void gavinDoesRetrieveWeatherInformationForDays(Actor actor, int numberOfDay, boolean ignoreToday) {
        EnvironmentVariables objEnvVar = SystemEnvironmentVariables.createEnvironmentVariables();
        String ignore = "includeToday";
        if(ignoreToday){
            ignore = "excludeToday";
        }
        filePath = String.format("weather/weather_info_%s_%ddays_%s_%s_%s.json",
                EnvironmentSpecificConfiguration.from(objEnvVar).getProperty("country"),
                numberOfDay,
                ignore,
                RemoteDriver.of(Serenity.getDriver()).getCapabilities().getBrowserName(),
                Utilities.getCurrentDateTimeSuffix());
        actor.attemptsTo(
                GetWeatherInfoToJSonFile.execute(filePath,numberOfDay, ignoreToday));
    }

    @Then("{actor} should check the file exist and is not empty")
    public void gavinShouldCheckTheFileExistAndIsNotEmpty(Actor actor) throws JsonProcessingException {
        actor.attemptsTo(
                Ensure.that(Utilities.isFileNotEmpty(filePath)).isTrue()
        );
    }
}
