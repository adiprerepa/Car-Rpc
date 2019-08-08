package com.prerepa.car_rpc.controller;

import com.prerepa.generated.Control_Esp8266Acknowledge;
import com.prerepa.generated.Control_Esp8266Address;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Tests for the Controller Interactor
 * @author aditya
 */
public class ControllerInteractorTest {


    /*
      TODO find out a way to mock a socket with
      {@link Mockito}
     */

    /**
     * Make sure given a bogus address and port, the
     * @see Control_Esp8266Acknowledge.Request_Status - Unable to connect
     * is set in the enum and returned back.
     * Actually tries to connect.
     */
    @Test
    public void handleAcknowledgeTest() {
        ControlInteractor mockInteractor = new ControlInteractor();
        String host = "0.0.0.0";
        int port = 20193;
        int controller_key = 1362;
        Control_Esp8266Address mockAddress = Control_Esp8266Address.newBuilder()
                .setAddress(host)
                .setPort(port)
                .setControllerKey(controller_key)
                .build();
        Control_Esp8266Acknowledge esp8266Acknowledge = Control_Esp8266Acknowledge.newBuilder()
                .setStatus(Control_Esp8266Acknowledge.Request_Status.UNABLE_TO_CONNECT_TO_ESP8266).build();
        Control_Esp8266Acknowledge realAck = mockInteractor.handleAcknowledge(mockAddress);
        assertEquals(esp8266Acknowledge, realAck);
    }

    /**
     * Build a mock Acknowledge for status OK and run another
     * through the method and assert they are the same.
     */
    @Test
    public void buildAcknowledgeResponseTest_OK() {
        ControlInteractor controlInteractor = new ControlInteractor();
        Control_Esp8266Acknowledge okAck = Control_Esp8266Acknowledge.newBuilder()
                .setStatus(Control_Esp8266Acknowledge.Request_Status.OK)
                .build();
        Control_Esp8266Acknowledge mockAcknowledge = controlInteractor.buildAcknowledge(AcknowledgeStatus.OK);
        assertEquals(mockAcknowledge, okAck);
    }

    /**
     * Build a mock Acknowledge for status SERVER_ERROR and run
     * the same scenario through the {@link ControlInteractor#buildAcknowledge(AcknowledgeStatus)} ()} method
     */
    @Test
    public void buildAcknowledgeResponseTest_Internal_Server_Error() {
        ControlInteractor controlInteractor = new ControlInteractor();
        Control_Esp8266Acknowledge serverErrorAcknowledge = Control_Esp8266Acknowledge.newBuilder()
                .setStatus(Control_Esp8266Acknowledge.Request_Status.INTERNAL_SERVER_ERROR)
                .build();
        Control_Esp8266Acknowledge mockAcknowledge = controlInteractor.buildAcknowledge(AcknowledgeStatus.INTERNAL_SERVER_ERROR);
        assertEquals(mockAcknowledge, serverErrorAcknowledge);
    }

    @Test
    public void buildAcknowledgeResponseTest_Cannot_Connect_To_Esp8266() {
        ControlInteractor controlInteractor = new ControlInteractor();
        Control_Esp8266Acknowledge cannotConnectAcknowledge = Control_Esp8266Acknowledge.newBuilder()
                .setStatus(Control_Esp8266Acknowledge.Request_Status.UNABLE_TO_CONNECT_TO_ESP8266)
                .build();
        Control_Esp8266Acknowledge mockAcknowledge = controlInteractor.buildAcknowledge(AcknowledgeStatus.CANNOT_CONNECT_TO_ESP8266);
        assertEquals(mockAcknowledge, cannotConnectAcknowledge);
    }
}
