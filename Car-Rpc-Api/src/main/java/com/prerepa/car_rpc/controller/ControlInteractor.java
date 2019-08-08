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
import java.net.UnknownHostException;

/**
 * The Main Controller Platform that has all the logic in it.
 * @author aditya
 */
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
        Control_Esp8266Acknowledge esp8266Acknowledge;
        try {
            Socket reserve_socket = getEsp8266InteractorSocket(address.getAddress(), address.getPort());
            ValueStore.setEsp_connection_success(true);
            ValueStore.setSocket(reserve_socket);
            ValueStore.setKey(address.getAddress(), address.getPort(), address.getControllerKey());
            esp8266Acknowledge = buildAcknowledge(AcknowledgeStatus.OK);
        } catch (IOException e) { // Means we weren't able to connect
            ValueStore.setEsp_connection_success(false);
            esp8266Acknowledge = buildAcknowledge(AcknowledgeStatus.CANNOT_CONNECT_TO_ESP8266);
        }
        return esp8266Acknowledge;
    }

    public Socket getEsp8266InteractorSocket(String hostAddress, int port) throws IOException {
        Esp8266Interactor interactor = new Esp8266Interactor();
        return interactor.startConnection(hostAddress, port);
    }

    // Individual method for testablility
    public Control_Esp8266Acknowledge buildAcknowledge(AcknowledgeStatus status) {
        Control_Esp8266Acknowledge acknowledge;
        switch (status) {
            case OK:
                acknowledge = Control_Esp8266Acknowledge.newBuilder()
                        .setStatus(Control_Esp8266Acknowledge.Request_Status.OK)
                        .build();
                break;
            case CANNOT_CONNECT_TO_ESP8266:
                acknowledge = Control_Esp8266Acknowledge.newBuilder()
                        .setStatus(Control_Esp8266Acknowledge.Request_Status.UNABLE_TO_CONNECT_TO_ESP8266)
                        .build();
                break;
            default:
                acknowledge = Control_Esp8266Acknowledge.newBuilder()
                        .setStatus(Control_Esp8266Acknowledge.Request_Status.INTERNAL_SERVER_ERROR)
                        .build();
                break;
        }
        return acknowledge;
    }
}
