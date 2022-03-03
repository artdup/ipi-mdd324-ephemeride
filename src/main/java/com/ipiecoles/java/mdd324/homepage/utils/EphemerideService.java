package com.ipiecoles.java.mdd324.homepage.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class EphemerideService {

    /**
     * Méthode allant chercher toutes les informations d'une année
     * @return Json avec toutes les informations
     */
    public static JsonObject getEphemerideAnnee(){
        String url = "https://raw.githubusercontent.com/theofidry/ephemeris/master/src/ephemeris.json";
        String contentOfTheUrl="";
        try{
            contentOfTheUrl = Utils.getPageContents(url);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        JsonObject jsonObject = new JsonParser().parse(contentOfTheUrl).getAsJsonObject();
        return jsonObject;
    }

    public static JsonArray getEphemerideMonth(JsonObject jsonObject, String monthYouWant){
        JsonArray monthAsJson = jsonObject.get(monthYouWant.toLowerCase()).getAsJsonArray();
        return monthAsJson;
    }

    public static String getEphemerideDay(JsonArray monthEphemeride, Integer dayYouWant){
        String ephemerideOfTheDay = "";
        JsonArray dayAsJson = monthEphemeride.get(dayYouWant).getAsJsonArray();
        if(!dayAsJson.get(1).equals("")){
            ephemerideOfTheDay = dayAsJson.get(1).toString() + " ";
        }
        ephemerideOfTheDay += dayAsJson.get(0).toString();
        return ephemerideOfTheDay.replaceAll("\"","");
    }

    public static String getEphemeride(LocalDate date){
        JsonObject year = getEphemerideAnnee();
        JsonArray month = getEphemerideMonth(year, date.getMonth().toString());
        String ephemeride = getEphemerideDay(month, date.getDayOfMonth()-1);
        return ephemeride;
    }

    public static Ephemeride getResponse(LocalDate date){
        LocalDate aujourdhui = date;
        Ephemeride ephemeride = new Ephemeride();
        ephemeride.setDateJour(aujourdhui.format(DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy", Locale.FRENCH)));
        ephemeride.setFeteDuJour(getEphemeride(aujourdhui));
        ephemeride.setJourAnnee(aujourdhui.getDayOfYear());
        ephemeride.setJoursRestants(aujourdhui.isLeapYear() ? 366 - aujourdhui.getDayOfYear() : 365 - aujourdhui.getDayOfYear());
        ephemeride.setNumSemaine((aujourdhui.getDayOfYear() / 7) + 1);
        return ephemeride;
    }
}