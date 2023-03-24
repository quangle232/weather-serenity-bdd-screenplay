package com.weather.ql.actions.get_weather_info;

import com.weather.ql.actions.get_weather_info.tasks.GetWeatherInfoToJsonFileTask;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class GetWeatherInfoToJSonFile {
    public static Performable execute(String filePath, int numOfDay, boolean ignoreToday) {
        return Task.where("{0} get weather information data for " + numOfDay + " days and export to " + filePath,
                new GetWeatherInfoToJsonFileTask(filePath, numOfDay, ignoreToday));
    }
}
