package com.weather.ql.core.helper;

import org.json.simple.JSONArray;

import java.io.FileWriter;
import java.io.PrintWriter;

public class JsonHelper {
    public static void exportJsonToFile(String filePath, JSONArray jsonArray){
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {
            out.write(jsonArray.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
