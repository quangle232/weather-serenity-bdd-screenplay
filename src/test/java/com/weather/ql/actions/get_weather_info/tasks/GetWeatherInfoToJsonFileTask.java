package com.weather.ql.actions.get_weather_info.tasks;

import com.weather.ql.core.helper.JsonHelper;
import com.weather.ql.page.TenDaysPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import org.json.simple.JSONArray;

public class GetWeatherInfoToJsonFileTask implements Performable{

    TenDaysPage tenDaysPage = new TenDaysPage();
    private int numOfDay;
    private String filePath;
    private boolean ignoreToday;

    public GetWeatherInfoToJsonFileTask(String filePath, int numOfDay, boolean ignoreToday){
        this.numOfDay = numOfDay;
        this.filePath = filePath;
        this.ignoreToday = ignoreToday;
    }
    @Override
    public <T extends Actor> void performAs(T t) {
        try {
            JSONArray data = tenDaysPage.retrieveWeatherInformationInNumberOfDay(this.numOfDay, this.ignoreToday);
            JsonHelper.exportJsonToFile(this.filePath, data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
