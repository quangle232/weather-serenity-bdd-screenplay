package com.weather.ql.questions.get_weather_info;

import com.weather.ql.page.TenDaysPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.thucydides.core.annotations.Managed;
import org.json.simple.JSONArray;
import org.openqa.selenium.WebDriver;

public class AskWeatherInfoForTenDays implements Question<JSONArray> {


    public static Question<JSONArray> getTenDaysInfo() { return new AskWeatherInfoForTenDays();}

    @Override
    public JSONArray answeredBy(Actor actor) {
        // TODO: ask to get JSONArray from web
        return null;
    }
}
