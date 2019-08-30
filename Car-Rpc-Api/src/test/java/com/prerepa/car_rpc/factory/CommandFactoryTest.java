package com.prerepa.car_rpc.factory;

import com.car_rpc.generated.*;
import com.prerepa.car_rpc.controller.AcknowledgeStatus;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Works only on non-analog systems
 * The way the {@link CommandFactory#buildCommand(ControlRequest)} works
 * is by taking the absolute the value of x, y, and z of the accelerometer,
 * seeing which one is the largest, and setting the request to the esp8266 in that
 * way.
 * @author aditya
 */
public class CommandFactoryTest {

    /**
     * Front Rotation Rules : -1 is left, 1 is right
     */
    @Test
    public void buildCommand_ForwardTest() {
        Full_Request full_request = Full_Request.newBuilder().setPowerState(false).setCommand(Command.newBuilder().setFrontRotation(0).setSpeed(1).build()).build();
        // mock values from accelerometer -> x, y = 0 and z = 10 to represent forward - power state is false
        ControlRequest controlRequest = ControlRequest.newBuilder().setPowerState(false).setControllerKey(1515).setAxisPlane(Vector.newBuilder().setX(0).setY(0).setZ(10).build()).build();
        assertEquals(CommandFactory.buildCommand(controlRequest), full_request);
    }

    /**
     * Expects when (x,y,z) with -z -> to have backward motor with -1 speed
     */
    @Test
    public void buildCommand_BackwardTest() {
        Full_Request full_request = Full_Request.newBuilder().setPowerState(false).setCommand(Command.newBuilder().setFrontRotation(0).setSpeed(-1).build()).build();
        // negative Z -> expect motor back
        ControlRequest controlRequest = ControlRequest.newBuilder().setPowerState(false).setControllerKey(1515).setAxisPlane(Vector.newBuilder().setX(0).setY(0).setZ(-10).build()).build();
        assertEquals(CommandFactory.buildCommand(controlRequest), full_request);
    }

    /**
     * Servo right -> frontRotation = 1, speed 1
     */
    @Test
    public void buildCommand_ServoRight() {
        Full_Request full_request = Full_Request.newBuilder().setPowerState(false).setCommand(Command.newBuilder().setFrontRotation(1).setSpeed(1).build()).build();
        // negative X -> expect front rotation 1 (accelerometer values)
        ControlRequest controlRequest = ControlRequest.newBuilder().setPowerState(false).setControllerKey(1515).setAxisPlane(Vector.newBuilder().setX(-10).setY(0).setZ(0).build()).build();
        assertEquals(CommandFactory.buildCommand(controlRequest), full_request);
    }

    /**
     * Servo left -> frontRotation = -1, speed 1
     */
    @Test
    public void buildCommand_ServoLeft() {
        Full_Request full_request = Full_Request.newBuilder().setPowerState(false).setCommand(Command.newBuilder().setFrontRotation(-1).setSpeed(1).build()).build();
        // positive X -> expect front rotation -1
        ControlRequest controlRequest = ControlRequest.newBuilder().setPowerState(false).setControllerKey(1515).setAxisPlane(Vector.newBuilder().setX(10).setY(0).setZ(0).build()).build();
        assertEquals(CommandFactory.buildCommand(controlRequest), full_request);
    }

    @Test
    public void buildResponse_Metrics() {
        int hcsr04Front = 265;
        int hcsr04Left = 834;
        int hcsr04Right = 7807;
        Metrics metrics = Metrics.newBuilder()
                .setHCSR04FrontDistance(hcsr04Front)
                .setHCSR04LeftDistance(hcsr04Left)
                .setHCSR04RightDistance(hcsr04Right)
                .build();
        ControlResponse expectedResponse = ControlResponse.newBuilder()
                .setRequestCode(ControlResponse.RequestStatusCode.OK)
                .setHcsr04RightDistance(hcsr04Right)
                .setHcsr04LeftDistance(hcsr04Left)
                .setHcsr04FrontDistance(hcsr04Front)
                .build();
        ControlResponse response = CommandFactory.buildControlResponseFromEsp8266Metrics(metrics);
        assertEquals(response, expectedResponse);
    }

    @Test
    public void buildAcknowledge_UnableToConnect() {
        AcknowledgeStatus acknowledgeStatus = AcknowledgeStatus.CANNOT_CONNECT_TO_ESP8266;
        ControlAcknowledgeResponse expectedControlAcknowledgeResponse = ControlAcknowledgeResponse.newBuilder().setStatus(ControlAcknowledgeResponse.Request_Status.UNABLE_TO_CONNECT_TO_ESP8266).build();
        ControlAcknowledgeResponse response = CommandFactory.buildAcknowledge(acknowledgeStatus);
        assertEquals(response, expectedControlAcknowledgeResponse);
    }

    @Test
    public void buildAcknowledge_OK() {
        AcknowledgeStatus acknowledgeStatus = AcknowledgeStatus.OK;
        ControlAcknowledgeResponse expectedControlAcknowledgeResponse = ControlAcknowledgeResponse.newBuilder().setStatus(ControlAcknowledgeResponse.Request_Status.OK).build();
        ControlAcknowledgeResponse response = CommandFactory.buildAcknowledge(acknowledgeStatus);
        assertEquals(response, expectedControlAcknowledgeResponse);
    }

    @Test
    public void buildAcknowledge_InternalServerError() {
        AcknowledgeStatus acknowledgeStatus = AcknowledgeStatus.INTERNAL_SERVER_ERROR;
        ControlAcknowledgeResponse expectedControlAcknowledgeResponse = ControlAcknowledgeResponse.newBuilder().setStatus(ControlAcknowledgeResponse.Request_Status.INTERNAL_SERVER_ERROR).build();
        ControlAcknowledgeResponse response = CommandFactory.buildAcknowledge(acknowledgeStatus);
        assertEquals(response, expectedControlAcknowledgeResponse);
    }
}
