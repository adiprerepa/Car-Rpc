package com.prerepa.esp8266_client;

import com.prerepa.generated.Response;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String host = "192.168.1.48";
        int port = 42069;
        Esp8266ClientInteractor clientInteractor = new Esp8266ClientInteractor();
//        while (true) {
//            try {
//                String dataToSend = "bitch";
//                System.out.println("Starting connection...");
//                clientInteractor.setEsp8266Socket(host, port);
//                System.out.printf("SENDING REQUEST : %s\n", dataToSend);
//                clientInteractor.sendRequest(MessageFactory.buildRequest(dataToSend));
////                Response response = clientInteractor.recieveResponse();
////                System.out.printf("GOT RESPONSE : %s\n", response.getResponseData());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }

        int dataToSend = 69420;
        System.out.println("Starting connection...");
        clientInteractor.setEsp8266Socket(host, port);
        System.out.printf("SENDING REQUEST : %s\n", dataToSend);
        clientInteractor.sendRequest(MessageFactory.buildRequest(dataToSend));
        Response response = clientInteractor.recieveResponse();
        System.out.printf("RECIEVED : %d", response.getResponseData());
    }
}
