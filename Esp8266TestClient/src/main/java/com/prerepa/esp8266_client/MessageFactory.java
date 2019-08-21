package com.prerepa.esp8266_client;

import com.prerepa.generated.Request;

public class MessageFactory {
    public static Request buildRequest(int data) {
        return Request.newBuilder()
                .setRequestData(data)
                .build();
    }
}
