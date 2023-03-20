package com.weather.ql.actions.navigation;

import com.weather.ql.page.WeatherDotComHomePage;
import com.weather.ql.page.component.NavigatorTabs;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;

public class NavigateTo {
    public static Performable theWeatherDotComHomePage() {
        return Task.where("{0} opens the Weather home page",
                Open.browserOn().the(WeatherDotComHomePage.class));
    }

    public static Performable theTenDaysPage(){
        return Task.where("{0} opens the ten days page",
                Click.on(NavigatorTabs.NAVIGATOR_TAB.of("10 Day")));
    }
}
