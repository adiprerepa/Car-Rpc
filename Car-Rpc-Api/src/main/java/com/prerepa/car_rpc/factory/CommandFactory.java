package com.prerepa.car_rpc.factory;

import com.car_rpc.generated.*;
import com.prerepa.car_rpc.controller.AcknowledgeStatus;

public class CommandFactory {

    /**
     * Encodes {@link ControlRequest} -> {@link Full_Request}
     */
    public static Full_Request buildCommand(ControlRequest request) {
        Full_Request full_request;
        float gyroscopeXPosition = request.getAxisPlane().getX();
        float gyroscopeYPosition = request.getAxisPlane().getY();
        float gyroscopeZPosition = request.getAxisPlane().getZ();
        boolean powerState = request.getPowerState();
        if (Math.abs(gyroscopeXPosition) > Math.abs(gyroscopeYPosition) &&  Math.abs(gyroscopeXPosition) > Math.abs(gyroscopeZPosition)) {
            // x is the largest and it means either servo left or right, and speed full ahead
            if (gyroscopeXPosition > 0) {
                // -1 is left -> with full speed
                full_request = Full_Request.newBuilder().setPowerState(powerState).setCommand(Command.newBuilder().setFrontRotation(-1).build()).build();
            } else {
                // +1 is right -> with full speed
                full_request = Full_Request.newBuilder().setPowerState(powerState).setCommand(Command.newBuilder().setFrontRotation(1).setSpeed(1).build()).build();
            }
        } else if (Math.abs(gyroscopeYPosition) > Math.abs(gyroscopeXPosition) && Math.abs(gyroscopeYPosition) > Math.abs(gyroscopeZPosition)) {
            // y is the largest and it means no signals sent
            full_request = Full_Request.newBuilder().setPowerState(powerState).setCommand(Command.newBuilder().setSpeed(0).setFrontRotation(0)).build();
        } else {
            // z is largest and means motor up or down
            if (gyroscopeZPosition > 0) {
                full_request = Full_Request.newBuilder().setPowerState(powerState).setCommand(Command.newBuilder().setFrontRotation(0).setSpeed(1)).build();
            } else {
                full_request = Full_Request.newBuilder().setPowerState(powerState).setCommand(Command.newBuilder().setFrontRotation(0).setSpeed(-1)).build();
            }
        }
        return full_request;
    }

    public static ControlAcknowledgeResponse buildAcknowledge(AcknowledgeStatus status) {
        ControlAcknowledgeResponse acknowledge;
        switch (status) {
            case OK:
                acknowledge = ControlAcknowledgeResponse.newBuilder()
                        .setStatus(ControlAcknowledgeResponse.Request_Status.OK)
                        .build();
                break;
            case CANNOT_CONNECT_TO_ESP8266:
                acknowledge = ControlAcknowledgeResponse.newBuilder()
                        .setStatus(ControlAcknowledgeResponse.Request_Status.UNABLE_TO_CONNECT_TO_ESP8266)
                        .build();
                break;
            default:
                acknowledge = ControlAcknowledgeResponse.newBuilder()
                        .setStatus(ControlAcknowledgeResponse.Request_Status.INTERNAL_SERVER_ERROR)
                        .build();
                break;
        }
        return acknowledge;
    }

    public static ControlResponse buildControlResponseFromEsp8266Metrics(Metrics metrics) {
        return null;
    }
}
