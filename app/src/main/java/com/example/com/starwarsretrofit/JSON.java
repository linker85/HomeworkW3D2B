package com.example.com.starwarsretrofit;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by raul on 01/11/2016.
 */

public class JSON {
    public Object object       = null;
    public boolean isJsonArray = false;

    public JSON(Object object, boolean isJsonArray){
        this.object      = object;
        this.isJsonArray = isJsonArray;
    }

    public static JSON fromStringToJSON(String jsonString){

        boolean isJsonArray = false;
        Object object = null;

        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            object = jsonArray;
            isJsonArray = true;
        } catch (Throwable t) {
        }

        if (object == null) {
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                object = jsonObject;
                isJsonArray = false;
            } catch (Throwable t) {
                t.printStackTrace();
                System.out.println("Malformed JSON: \"" + jsonString + "\"");
            }
        }

        return new JSON(object, isJsonArray);
    }


}
