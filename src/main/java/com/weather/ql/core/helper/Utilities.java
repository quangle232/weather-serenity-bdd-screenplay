package com.weather.ql.core.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
    public static String getCurrentDateTimeSuffix(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        return String.format("%s_%d", formatter.format(date), date.getTime());
    }

    public static boolean isFileNotEmpty(String filePath){
        File file = new File(filePath);
        return file.length() != 0;
    }

    public static JSONArray loadJSonArrayFromFile(String filePath){
        JSONParser jsonParser = new JSONParser();
        JSONArray data = new JSONArray();

        try (FileReader reader = new FileReader(filePath))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            data = (JSONArray) obj;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static boolean isJsonArrayEqual(JSONArray firstJson, JSONArray secondJson) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return  objectMapper.readTree(firstJson.toJSONString()) == objectMapper.readTree(secondJson.toJSONString());
    }
}
