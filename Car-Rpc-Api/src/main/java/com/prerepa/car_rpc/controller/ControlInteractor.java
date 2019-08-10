package com.prerepa.car_rpc.controller;

import com.prerepa.car_rpc.api.controller.ControllerPlatform;
import com.prerepa.car_rpc.esp8266.Esp8266Interactor;
import com.prerepa.car_rpc.shared.ValueStore;
import com.prerepa.generated.*;

import java.io.IOException;

import static com.prerepa.car_rpc.factory.CommandFactory.buildAcknowledge;
import static com.prerepa.car_rpc.factory.CommandFactory.buildCommand;

/**
 * The Main Controller Platform that has all the logic in it.
 * @author aditya
 */
public class ControlInteractor implements ControllerPlatform {

    Esp8266Interactor esp8266Interactor = new Esp8266Interactor();

    /**
     * Handle the request, return void. Gets
     * the {@link ControlRequest#getControllerKey()}
     * and gets the socket associated with it with
     * {@link ValueStore#getSocket(int)}. The rpc
     * uses the method {@link com.prerepa.car_rpc.factory.CommandFactory#buildCommand(ControlRequest)},
     * and passes in the {@link ControlRequest}, and it gets a
     * {@link Esp8266_Command}. It then writes the command to the
     * socket's {@link java.io.OutputStream}.
     *
     * Uses {@link ValueStore#getSocket(int)} - so assumes that the connection
     * was already set by the acknowledge found in :
     * {@link ControlInteractor#handleAcknowledge(Control_Esp8266Address)}
     *
     * request -> builder -> command -> write to socket
     * @param request
     * @throws IOException
     */
    @Override
    public void handleControllerRequest(ControlRequest request) throws IOException {
        // build the command from CommandFactory#buildCommand()
        Esp8266_Command esp8266_command = buildCommand(request);
        // sends the command from the interactor set above, with a socket
        // set in the class during the initialization,
        // acknowledgeConnection()
        esp8266Interactor.sendCommand(esp8266_command);
    }

    /**
     * Recieves a response from the esp8266, from
     * the interactor set in {@link ControlInteractor#esp8266Interactor},
     * and the socket is set in the {@link Esp8266Interactor} class,
     * we use it straight from there.
     * @return
     * @throws IOException
     */
    @Override
    public ControlResponse recieveRepsonse() throws IOException {
        return ControlResponse.parseFrom(esp8266Interactor.getSocket().getInputStream());
    }

    /**
     * Take the address of an esp8266 (said addr), and use the
     * {@link Esp8266Interactor#acknowledgeConnection(String, int, int)}
     * to initialize a socket in the class and the value store.
     * @param address
     * @return
     */
    @Override
    public Control_Esp8266Acknowledge handleAcknowledge(Control_Esp8266Address address) {
        Control_Esp8266Acknowledge esp8266Acknowledge;
        esp8266Interactor.acknowledgeConnection(address.getAddress(), address.getPort(), address.getControllerKey());
        if (ValueStore.getEsp_connection_success()) {
            esp8266Acknowledge = buildAcknowledge(AcknowledgeStatus.OK);
        } else {
            esp8266Acknowledge = buildAcknowledge(AcknowledgeStatus.CANNOT_CONNECT_TO_ESP8266);
        }
        return esp8266Acknowledge;
    }
}
