package com.ipiecoles.java.mdd324.homepage.utils;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LambdaApp implements RequestHandler<Object, GatewayResponse> {
    @Override
    public GatewayResponse handleRequest(Object input, final Context context) {
        EphemerideService ephemerideService = new EphemerideService();
        Ephemeride ephemeride = null;
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Access-Control-Allow-Origin", "https://pjvilloud.github.io");


        try{
            ephemeride = ephemerideService.getResponse(LocalDate.now());
        } catch (Exception e){
            //Error
            return new GatewayResponse("{\"error\":\"Problème lors de la récupération de la fête du jour\")", headers, 500);
        }

        String body = new Gson().toJson(ephemeride);
        return new GatewayResponse(body,headers,200);
    }

    /*Genson g = new GensonBuilder().useRuntimeType(true).create();
        String jsonOutput = g.serialize(ephemerides);
        GatewayResponse gatewayResponse = new GatewayResponse(
                jsonOutput,//body
                headers,//headers
                200);//statusCode
        return gatewayResponse;
    }*/
}
