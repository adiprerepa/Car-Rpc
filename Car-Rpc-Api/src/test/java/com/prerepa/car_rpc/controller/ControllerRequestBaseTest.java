package com.prerepa.car_rpc.controller;


import com.car_rpc.generated.ControlAcknowledge;
import com.car_rpc.generated.ControlAcknowledgeResponse;
import com.prerepa.car_rpc.esp8266.Esp8266Interactor;
import io.grpc.stub.StreamObserver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

public class ControllerRequestBaseTest {

    /**
     * todo stub method properly - right now it attempts to connect an quietly fails
     */
    @SuppressWarnings("unchecked")
    @Test
    public void controlAcknowledgeTest() {
        String address = "x.x.x.x";
        int port = 326;
        int controllerKey = 22632;
        ControllerRequestBase requestBase = new ControllerRequestBase();
        Esp8266Interactor mockInteractor = spy(Esp8266Interactor.class);
        StreamObserver<ControlAcknowledgeResponse> mockStreamObserver = mock(StreamObserver.class);
        doReturn(true).when(mockInteractor).acknowledgeConnection(address, port, controllerKey);
        ControlAcknowledge controlAcknowledge = ControlAcknowledge.newBuilder()
                .setControllerKey(controllerKey)
                .setPort(port)
                .setAddress(address)
                .build();
        ControlAcknowledgeResponse response = ControlAcknowledgeResponse.newBuilder()
                .setStatus(ControlAcknowledgeResponse.Request_Status.UNABLE_TO_CONNECT_TO_ESP8266)
                .build();
        requestBase.controlAcknowledgeService(controlAcknowledge, mockStreamObserver);
        verify(mockStreamObserver).onNext(response);
    }
}
