package com.prerepa.car_rpc.api.controller;

import com.car_rpc.generated.ControlAcknowledge;
import com.car_rpc.generated.ControlAcknowledgeResponse;
import com.car_rpc.generated.ControlRequest;
import com.car_rpc.generated.ControlResponse;

import java.io.IOException;

/**
 * Interface the Api has to follow (Controller)
 * @author aditya
 */
public interface ControllerPlatform {
    void handleControllerRequest(ControlRequest request) throws IOException;

    ControlResponse recieveRepsonse() throws Throwable;

    ControlAcknowledgeResponse handleAcknowledge(ControlAcknowledge address);
}
