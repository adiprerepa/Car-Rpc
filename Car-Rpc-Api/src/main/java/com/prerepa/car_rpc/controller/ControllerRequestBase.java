package com.prerepa.car_rpc.controller;

import io.grpc.stub.StreamObserver;

public class ControllerRequestBase extends ControllerServiceGrpc.ControllerServiceImplBase {

    @Override
    public void controlService(ControlRequest controlRequest, StreamObserver<ControlResponse> responseStreamObserver) {

    }
}
