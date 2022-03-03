package com.ipiecoles.java.mdd324.homepage.utils;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
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
        List<Ephemeride> ephemerides = null;
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Access-Control-Allow-Origin", "https://pjvilloud.github.io");


        try {
            ephemerides = (List<Ephemeride>) EphemerideService.getResponse(LocalDate.now());
        } catch (Exception e) {
            //500
            return new GatewayResponse(
                    "{'error':'Erreur interne'}",
                    headers,
                    500
            );
        }
        Genson g = new GensonBuilder().useRuntimeType(true).create();
        String jsonOutput = g.serialize(ephemerides);
        GatewayResponse gatewayResponse = new GatewayResponse(
                jsonOutput,//body
                headers,//headers
                200);//statusCode
        return gatewayResponse;
    }
}
