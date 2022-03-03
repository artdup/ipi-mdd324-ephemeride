package com.ipiecoles.java.mdd324.homepage.utils;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Handler;

public class Main {
    public static void main(String[] args) throws IOException {
        EphemerideService ephemerideService = new EphemerideService();
        //System.out.println(ephemerideService.getResponse());
        //System.out.println(new Gson().toJson(ephemerideService.getResponse(LocalDate.now())));
        //System.out.println(new Handler().handleRequest(null, null));
       /* Handler handler = new Handler();*/
        /*GatewayRequest params = new GatewayRequest();
        params.setBody("{\"name\":[[\"theodore\"]]}");
        //params.setBody("");
        System.out.println(handler.handleRequestWithParameter(params, null));*/
    }
}
