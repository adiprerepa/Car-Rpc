package com.prerepa.car_rpc.api.controller;

import com.car_rpc.generated.*;

import java.io.IOException;

/**
 * Interface the Api has to follow (Controller)
 * @author aditya
 */
public interface ControllerPlatform {
    void handleControllerRequest(ControlRequest request) throws IOException;

    ControlResponse recieveRepsonse() throws Throwable;

    ControlAcknowledgeResponse handleAcknowledge(ControlAcknowledge address);

    ServerAcknowledgeResponse handleServerAcknowledge(ServerAcknowledge serverAcknowledge);

    Esp8266DiscoveryResponse handleDiscoveryRequest(Esp8266DiscoveryRequest discoveryRequest);
}
