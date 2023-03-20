package com.weather.ql.actions.get_weather_info.tasks;

import com.weather.ql.core.helper.JsonHelper;
import com.weather.ql.core.helper.Utilities;
import com.weather.ql.page.TenDaysPage;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.core.webdriver.RemoteDriver;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.thucydides.core.environment.SystemEnvironmentVariables;
import net.thucydides.core.util.EnvironmentVariables;
import org.json.simple.JSONArray;

public class GetWeatherInfoToJsonFileTask implements Performable{

    TenDaysPage tenDaysPage = new TenDaysPage();
    private int numOfDay;
    private String filePath;

    public GetWeatherInfoToJsonFileTask(String filePath, int numOfDay){
        this.numOfDay = numOfDay;
        this.filePath = filePath;
    }
    @Override
    public <T extends Actor> void performAs(T t) {
        try {
            JSONArray data = tenDaysPage.retrieveWeatherInformation(this.numOfDay);
            JsonHelper.exportJsonToFile(this.filePath, data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
