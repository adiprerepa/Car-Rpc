package com.prerepa.car_rpc.api.controller;

import com.prerepa.generated.ControlRequest;
import com.prerepa.generated.ControlResponse;
import com.prerepa.generated.Control_Esp8266Acknowledge;
import com.prerepa.generated.Control_Esp8266Address;

import java.io.IOException;

/**
 * Interface the Api has to follow (Controller)
 * @author aditya
 */
public interface ControllerPlatform {
    void handleControllerRequest(ControlRequest request) throws IOException;

    ControlResponse recieveRepsonse(int controllerKey);

    boolean parseKey(int key);

    void enterEndpoint(String address, int port, int key);

    Control_Esp8266Acknowledge handleAcknowledge(Control_Esp8266Address address);


}
