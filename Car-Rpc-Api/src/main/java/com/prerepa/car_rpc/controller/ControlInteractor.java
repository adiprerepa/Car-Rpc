package com.prerepa.car_rpc.controller;

import com.prerepa.car_rpc.api.controller.ControllerPlatform;
import com.prerepa.car_rpc.esp8266.Esp8266Interactor;
import com.prerepa.car_rpc.shared.ValueStore;
import com.prerepa.generated.ControlRequest;
import com.prerepa.generated.ControlResponse;
import com.prerepa.generated.Control_Esp8266Acknowledge;
import com.prerepa.generated.Control_Esp8266Address;

import java.io.IOException;
import java.net.Socket;

public class ControlInteractor implements ControllerPlatform {

    @Override
    public ControlResponse handleControllerRequest(ControlRequest request) {
        return null;
    }

    @Override
    public boolean parseKey(int key) {
        return ValueStore.keyExists(key);
    }

    @Override
    public void enterEndpoint(String address, int port, int key) {
        ValueStore.setKey(address, port, key);
    }

    @Override
    public Control_Esp8266Acknowledge handleAcknowledge(Control_Esp8266Address address) {
        Esp8266Interactor interactor = new Esp8266Interactor();
        try {
            Socket reserve_socket = interactor.startConnection(address.getAddress(), address.getPort());
            ValueStore.setSocket(reserve_socket);
            ValueStore.setEsp_connection_success(true);
        } catch (IOException e) {
            ValueStore.setEsp_connection_success(false);
            e.printStackTrace();
        }
        Control_Esp8266Acknowledge esp8266Acknowledge;
        if (ValueStore.getEsp_connection_success()) {
            esp8266Acknowledge = Control_Esp8266Acknowledge.newBuilder().setStatus(Control_Esp8266Acknowledge.Request_Status.OK).build();
        } else {
            esp8266Acknowledge = Control_Esp8266Acknowledge.newBuilder().setStatus(Control_Esp8266Acknowledge.Request_Status.UNABLE_TO_CONNECT_TO_ESP8266).build();
        }
        return esp8266Acknowledge;
    }

}
