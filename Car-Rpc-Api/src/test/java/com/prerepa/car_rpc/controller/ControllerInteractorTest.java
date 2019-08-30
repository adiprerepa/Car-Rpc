package com.prerepa.car_rpc.controller;

import com.car_rpc.generated.ControlAcknowledge;
import com.car_rpc.generated.ControlAcknowledgeResponse;
import com.prerepa.car_rpc.esp8266.Esp8266Interactor;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class ControllerInteractorTest {

    /**
     * TODO ADD TESTS
     *  - Find out how to mock sockets
     *  - Add those dependencies to the test
     *  - add tests to depend on when the conn is successful
     */

    @Ignore("Dependencies not Mocked out yet")
    @Test
    public void handleAcknowledgeTest_ConnectionFailed() {
        ControlInteractor controlInteractor = new ControlInteractor();
        ControlAcknowledgeResponse expectedResponse = ControlAcknowledgeResponse.newBuilder().setStatus(ControlAcknowledgeResponse.Request_Status.UNABLE_TO_CONNECT_TO_ESP8266).build();
        String testHostAddress = "x.x.x.x";
        int port = 55555;
        int controllerKey = 3210628;
        ControlAcknowledge acknowledge = ControlAcknowledge.newBuilder().setAddress(testHostAddress).setPort(port).setControllerKey(controllerKey).build();
        boolean wasAbleToConnect = false;
        Esp8266Interactor mockInteractor = Mockito.mock(Esp8266Interactor.class);
        when(mockInteractor.acknowledgeConnection(testHostAddress, port, controllerKey)).thenReturn(wasAbleToConnect);
        ControlAcknowledgeResponse response = controlInteractor.handleAcknowledge(acknowledge);
        Assert.assertEquals(response, expectedResponse);
    }
}
