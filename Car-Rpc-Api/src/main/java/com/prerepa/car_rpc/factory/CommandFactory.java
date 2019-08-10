package com.prerepa.car_rpc.factory;

import com.prerepa.car_rpc.controller.AcknowledgeStatus;
import com.prerepa.generated.ControlRequest;
import com.prerepa.generated.Control_Esp8266Acknowledge;
import com.prerepa.generated.Esp8266_Command;

public class CommandFactory {

    /**
     * Encodes {@link ControlRequest} -> {@link Esp8266_Command}
     */
    public static Esp8266_Command buildCommand(ControlRequest request) {
        Esp8266_Command command;
        switch(request.getDirection()) {
            case MOTOR_FORWARD:
                command = Esp8266_Command.newBuilder().setDirection(Esp8266_Command.Esp8266_Direction.MOTOR_FORWARD).build();
                break;
            case MOTOR_BACK:
                command = Esp8266_Command.newBuilder().setDirection(Esp8266_Command.Esp8266_Direction.MOTOR_BACK).build();
                break;
            case SHUTDOWN:
                command = Esp8266_Command.newBuilder().setDirection(Esp8266_Command.Esp8266_Direction.SHUTDOWN).build();
                break;
            case WHEELS_LEFT:
                command = Esp8266_Command.newBuilder().setDirection(Esp8266_Command.Esp8266_Direction.WHEELS_LEFT).build();
                break;
            case WHEELS_RIGHT:
                command = Esp8266_Command.newBuilder().setDirection(Esp8266_Command.Esp8266_Direction.WHEELS_RIGHT).build();
                break;
            default:
                command = Esp8266_Command.newBuilder().setDirection(Esp8266_Command.Esp8266_Direction.KEEP_ALIVE).build();
                break;
        }
        return command;
    }
    public static Control_Esp8266Acknowledge buildAcknowledge(AcknowledgeStatus status) {
        Control_Esp8266Acknowledge acknowledge;
        switch (status) {
            case OK:
                acknowledge = Control_Esp8266Acknowledge.newBuilder()
                        .setStatus(Control_Esp8266Acknowledge.Request_Status.OK)
                        .build();
                break;
            case CANNOT_CONNECT_TO_ESP8266:
                acknowledge = Control_Esp8266Acknowledge.newBuilder()
                        .setStatus(Control_Esp8266Acknowledge.Request_Status.UNABLE_TO_CONNECT_TO_ESP8266)
                        .build();
                break;
            default:
                acknowledge = Control_Esp8266Acknowledge.newBuilder()
                        .setStatus(Control_Esp8266Acknowledge.Request_Status.INTERNAL_SERVER_ERROR)
                        .build();
                break;
        }
        return acknowledge;
    }
}
