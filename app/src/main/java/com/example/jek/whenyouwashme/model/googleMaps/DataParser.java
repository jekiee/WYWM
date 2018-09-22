package com.example.jek.whenyouwashme.model.googleMaps;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jek on 27.07.2017.
 */

public class DataParser {

    public List<HashMap<String, String>> parse(String jsonData) {
        JSONArray jsonArray = null;
        JSONObject jsonObject;
        JSONArray jsonArrayVenues = null;
        JSONObject jsonObjectResponse;

        try {
            Log.d("Places", "parse data: " + jsonData);
            jsonObject = new JSONObject(jsonData);
            jsonObjectResponse = jsonObject.getJSONObject("response");
            jsonArrayVenues = jsonObjectResponse.getJSONArray("venues");
//            JSONObject ob = (JSONObject) jsonArrayVenues.get(1);
//            for (int i = 0; i < jsonArrayVenues.length(); i++) {
//                ob = (JSONObject) jsonArrayVenues.get(i);
//                JSONObject jsonObjectLoc = (JSONObject) ob.getJSONObject("location");
//                Log.d("TEST NAME ", (String) jsonObjectLoc.getString("lat"));
//                String d = jsonObjectLoc.getString("lng");
//                String n = jsonObjectLoc.getString("lat");
//
//            }
//            Log.d("TEST NAME ", (String) ob.get("name"));

        } catch (JSONException e) {
            Log.d("Places", "parse error");
            e.printStackTrace();
        }
        return getPlaces(jsonArrayVenues);
    }

    private List<HashMap<String, String>> getPlaces(JSONArray jsonArray) {
//        int     placesCount = jsonArray.length();
//        List<HashMap<String, String>> placesList = new ArrayList<>();
//        HashMap<String, String> placeMap = null;
//        Log.d("Places", "getPlaces");
//
//        for (int i = 0; i < placesCount; i++) {
//            try {
//                placeMap = getPlace((JSONObject) jsonArray.get(i));
//                placesList.add(placeMap);
//                Log.d("Places", "Adding places");
//
//            } catch (JSONException e) {
//                Log.d("Places", "Error in Adding places");
//                e.printStackTrace();
//            }
//        }
        int     placesCount = jsonArray.length();
        List<HashMap<String, String>> placesList = new ArrayList<>();
        HashMap<String, String> placeMap = null;
        Log.d("Places", "getPlaces");

        for (int i = 0; i < placesCount; i++) {
            try {
                placeMap = getPlace((JSONObject) jsonArray.get(i));
                placesList.add(placeMap);
                Log.d("Places", "Adding places");

            } catch (JSONException e) {
                Log.d("Places", "Error in Adding places");
                e.printStackTrace();
            }
        }
        return placesList;
    }

    private HashMap<String, String> getPlace(JSONObject googlePlaceJson) throws JSONException {
        HashMap<String, String> googlePlaceMap = new HashMap<String, String>();
        JSONObject jsonLocation = googlePlaceJson.getJSONObject("location");
        String placeName = "-NA-";
        String vicinity = "-NA-";
        String latitude = "";
        String longitude = "";
        String reference = "";
        longitude = jsonLocation.getString("lng");
        latitude = jsonLocation.getString("lat");
        Log.d("getPlace", "Entered");

        try {
            if (!googlePlaceJson.isNull("name")) {
                placeName = googlePlaceJson.getString("name");
            }
            if (!googlePlaceJson.isNull("vicinity")) {
                vicinity = googlePlaceJson.getString("vicinity");
            }
           // latitude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lat");
           // longitude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lng");
           // reference = googlePlaceJson.getString("reference");
            googlePlaceMap.put("place_name", placeName);
            googlePlaceMap.put("vicinity", vicinity);
            googlePlaceMap.put("lat", latitude);
            googlePlaceMap.put("lng", longitude);
            googlePlaceMap.put("reference", reference);
            Log.d("getPlace", "Putting Places");
        } catch (JSONException e) {
            Log.d("getPlace", "Error");
            e.printStackTrace();
        }
        return googlePlaceMap;
    }
}
