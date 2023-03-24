package com.weather.ql.page;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.UIInteractions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.util.HashMap;
import java.util.Map;

public class TenDaysPage extends UIInteractions {

    private String rowDateTitle = "[data-testid=DailyContent] h3 span";
    private String rowTemperatureValue = "[data-testid=ConditionsSummary] [data-testid=TemperatureValue]";
    private String rowHumidityPercent = "[data-testid=HumiditySection] [data-testid=PercentageValue]";

    private final Map<String, Integer> dayOrNight = new HashMap<String, Integer>() {{
        put("onlynight", 0);
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
        if(this.dayOrNight.keySet().contains(dayOrNight.toLowerCase())){
            dayOrNight = dayOrNight.toLowerCase();
        }
        else {
            throw new Exception("Please fill day or night or only_night to get correct temperature value");
        }
        return this.getRowByIndex(index).findElements(By.cssSelector(this.rowTemperatureValue)).get(this.dayOrNight.get(dayOrNight)).getText();
    }

    public String retrieveHumidityPercent(int index, String dayOrNight) throws Exception {
        if(this.dayOrNight.keySet().contains(dayOrNight.toLowerCase())){
            dayOrNight = dayOrNight.toLowerCase();
        }
        else {
            throw new Exception("Please fill day or night or only_night to get correct temperature value");
        }
        return this.getRowByIndex(index).findElements(By.cssSelector(this.rowHumidityPercent)).get(this.dayOrNight.get(dayOrNight)).getText();
    }

    // After submitted assignment
    public JSONObject retrieveTodayWeather() throws Exception {
        JSONObject todayData = new JSONObject();

        // 1 is only night
        // 2 is both day and night
        int dayAndNightTileNumber = this.getRowByIndex(0).findElements(By.cssSelector(this.rowTemperatureValue)).size();

        // Today will not contain Day information if you retrieve weather after 5pm
        JSONObject temperatureInfo = new JSONObject();
        JSONObject humidityInfo = new JSONObject();

        if(dayAndNightTileNumber != 1){
            temperatureInfo.put("day", this.retrieveTemperatureValue(0, "day"));
            humidityInfo.put("day", this.retrieveHumidityPercent(0, "day"));
            temperatureInfo.put("night", this.retrieveTemperatureValue(0, "night"));
            humidityInfo.put("night", this.retrieveHumidityPercent(0, "night"));
        }
        else {
            temperatureInfo.put("day", "you retrieved data in night time");
            humidityInfo.put("day", "you retrieved data in night time");
            temperatureInfo.put("night", this.retrieveTemperatureValue(0, "onlyNight"));
            humidityInfo.put("night", this.retrieveHumidityPercent(0, "onlyNight"));
        }
        todayData.put("date", this.retrieveDayTitle(0));
        todayData.put("temperature", temperatureInfo);
        todayData.put("humidity", humidityInfo);

        return todayData;
    }

    public JSONArray retrieveWeatherInformationInNumberOfDay(int numberOfDay, boolean ignoreToday) throws Exception {
        // Maximum row on web is 15
        // Start from tomorrow, index from 1 (tomorrow) instead of 0 (today)

        // After submitted assignment: refactor to fix no such element: Unable to locate element:[id='detailIndex15']
        if(numberOfDay >= 14){
            numberOfDay = 14;
        }

        if(numberOfDay < 0){
            numberOfDay = 0;
        }

        // After submitted assignment: refactor to fix no such element: Unable to locate element: [id='detailIndex15']
        // if number = 0 will get today weather
        JSONArray data = new JSONArray();
        if(ignoreToday){
            if(numberOfDay == 0){
                throw new Exception("Cannot retrieve data if you ignore today and get today weather at the same time");
            }
        } else {
            data.add(this.retrieveTodayWeather());
            if(numberOfDay == 0){
                return data;
            }

            // Can get 15 days information include today
            if(numberOfDay < 14){
                numberOfDay = numberOfDay - 1;
            }

        }

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
