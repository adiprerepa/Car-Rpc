package com.prerepa.car_rpc.api.controller;

public interface ControllerPlatform {
    ControlResponse handleControllerRequest(ControlRequest request);

    boolean parseKey(int key);
}
