package com.prerepa.car_rpc.api.controller;

import com.prerepa.generated.*;

import java.io.IOException;

/**
 * Interface the Api has to follow (Controller)
 * @author aditya
 */
public interface ControllerPlatform {
    void handleControllerRequest(ControlRequest request) throws IOException;

    ControlResponse recieveRepsonse() throws IOException;

    Control_Esp8266Acknowledge handleAcknowledge(Control_Esp8266Address address);
}
