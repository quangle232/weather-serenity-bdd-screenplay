package com.weather.ql.page;

import com.weather.ql.core.helper.JsonHelper;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.UIInteractions;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.util.HashMap;
import java.util.Map;

public class TenDaysPage extends UIInteractions {

    private String rowDateTitle = "[data-testid=DailyContent] span";
    private String rowTemperatureValue = "[data-testid=ConditionsSummary] [data-testid=TemperatureValue]";
    private String rowHumidityPercent = "[data-testid=HumiditySection] [data-testid=PercentageValue]";

    private final Map<String, Integer> dayOrNight = new HashMap<String, Integer>() {{
        put("day", 0);
        put("night", 1);
    }};

    // end page elements section

    // start page actions section

    public WebElementFacade getRowByIndex(int index){
        String rowLocator = String.format("[id='detailIndex%d']", index);
        return $(By.cssSelector(rowLocator));

    }

    public void expandDateRow(int index) throws InterruptedException {
        if(this.isRowCollapsed(index)){
            WebElementFacade rowToExpand = this.getRowByIndex(index);
            ((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView();", rowToExpand);
            Thread.sleep(1000); // hard wait 1s after scrolling to make script stable
            rowToExpand.click();
        }
    }

    public void collapseDateRow(int index){
        if(!this.isRowCollapsed(index)){
            this.getRowByIndex(index).click();
        }
    }

    public String retrieveDayTitle(int index){
        return this.getRowByIndex(index).find(By.cssSelector(this.rowDateTitle)).getText();
    }

    public String retrieveTemperatureValue(int index, String dayOrNight) throws Exception {
        if(dayOrNight.toLowerCase().equals("day") || dayOrNight.toLowerCase().equals("night")){
            dayOrNight = dayOrNight.toLowerCase();
        }
        else {
            throw new Exception("Please fill day or night to get correct temperature value");
        }
        return this.getRowByIndex(index).findElements(By.cssSelector(this.rowTemperatureValue)).get(this.dayOrNight.get(dayOrNight)).getText();
    }

    public String retrieveHumidityPercent(int index, String dayOrNight) throws Exception {
        if(dayOrNight.equalsIgnoreCase("day") || dayOrNight.equalsIgnoreCase("night")){
            dayOrNight = dayOrNight.toLowerCase();
        }
        else {
            throw new Exception("Please fill day or night to get correct humidity value");
        }
        return this.getRowByIndex(index).findElements(By.cssSelector(this.rowHumidityPercent)).get(this.dayOrNight.get(dayOrNight)).getText();
    }

    public JSONArray retrieveWeatherInformation(int numberOfDay) throws Exception {
        // Maximum row on web is 15
        // Start from tomorrow, index from 1 (tomorrow) instead of 0 (today)
        if(numberOfDay > 15 || numberOfDay == 0) {
            numberOfDay = 15;
        }
        JSONArray data = new JSONArray();
        for(int i = 1; i <=  numberOfDay; i++){
            this.expandDateRow(i);
            JSONObject dateInfo = new JSONObject();
            dateInfo.put("date", this.retrieveDayTitle(i));

            JSONObject temperatureInfo = new JSONObject();
            temperatureInfo.put("day", this.retrieveTemperatureValue(i, "day"));
            temperatureInfo.put("night", this.retrieveTemperatureValue(i, "night"));

            JSONObject humidityInfo = new JSONObject();
            humidityInfo.put("day", this.retrieveHumidityPercent(i, "day"));
            humidityInfo.put("night", this.retrieveHumidityPercent(i, "night"));

            dateInfo.put("temperature", temperatureInfo);
            dateInfo.put("humidity", humidityInfo);

            data.add(dateInfo);
        }
        System.out.print(data);
        return data;
    }

    // end page actions section

    // start page validation section
    public boolean isRowCollapsed(int index){
        String collapsedState = this.getRowByIndex(index).getAttribute("data-track-string");
        if(collapsedState.equals("false")){
            return false;
        }
        return true;
    }
    // end page validation section

}
