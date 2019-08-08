package com.prerepa.car_rpc.controller;

import com.prerepa.generated.*;
import io.grpc.stub.StreamObserver;

/**
 * The actual service rpc definition, links to
 * {@link ControlInteractor} for the most part.
 */
public class ControllerRequestBase extends ControllerServiceGrpc.ControllerServiceImplBase {

    private ControlInteractor controlInteractor;

    @Override
    public void controlService(ControlRequest controlRequest, StreamObserver<ControlResponse> responseStreamObserver) {
        responseStreamObserver.onNext(controlInteractor.handleControllerRequest(controlRequest));
        responseStreamObserver.onCompleted();
    }

    @Override
    public void controlAcknowledge(Control_Esp8266Address esp8266Address, StreamObserver<Control_Esp8266Acknowledge> responseStreamObserver) {
        controlInteractor = new ControlInteractor();
        controlInteractor.enterEndpoint(esp8266Address.getAddress(), esp8266Address.getPort(), esp8266Address.getControllerKey());
        responseStreamObserver.onNext(controlInteractor.handleAcknowledge(esp8266Address));
        responseStreamObserver.onCompleted();
    }
}
