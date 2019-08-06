package com.prerepa.car_rpc.controller;

import com.prerepa.car_rpc.api.controller.ControllerPlatform;
import io.grpc.Server;

public class ControlInteractor extends Thread implements ControllerPlatform {

    private Server server;



    @Override
    public void start() {
        int port = 20002;
        try {
// todo finish
        }
    }

    @Override
    public ControlResponse handleControllerRequest(ControlRequest request) {
        return null;
    }

    @Override
    public boolean parseKey(int key) {
        return false;
    }
}
