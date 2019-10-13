package com.prerepa.car_rpc.controller;

import com.car_rpc.generated.*;
import com.prerepa.car_rpc.api.controller.ControllerPlatform;
import com.prerepa.car_rpc.database.known_cars.KnownCarDatabase;
import com.prerepa.car_rpc.database.known_cars.entities.CarEntityIdentifier;
import com.prerepa.car_rpc.database.known_cars.entities.KnownCarEntity;
import com.prerepa.car_rpc.esp8266.Esp8266Interactor;
import com.prerepa.car_rpc.factory.CommandFactory;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

import static com.prerepa.car_rpc.factory.CommandFactory.buildAcknowledge;
import static com.prerepa.car_rpc.factory.CommandFactory.buildCommand;

/**
 * The Main Controller Platform that has all the logic in it.
 * @author aditya
 */
public class ControlInteractor implements ControllerPlatform {

    private Esp8266Interactor esp8266Interactor;
    private KnownCarDatabase knownCarDatabase;

    public ControlInteractor(Esp8266Interactor esp8266Interactor, KnownCarDatabase knownCarDatabase) {
        this.esp8266Interactor = esp8266Interactor;
        this.knownCarDatabase = knownCarDatabase;
    }

    /**
     *
     * Takes a Request that contains the (x,y,z) accelerometer position of
     * the phone, and builds the {@link Full_Request} through the {@link CommandFactory#buildCommand(ControlRequest)}.
     * We then send that command to the esp8266 through the esp8266Interactor with
     * {@link Esp8266Interactor#sendCommand(Full_Request)}
     * request -> builder -> command -> write to socket (in esp8266 interactor)
     */
    @Override
    public void handleControllerRequest(ControlRequest request) throws IOException {
        // build the command from CommandFactory#buildCommand()
        Full_Request esp8266_command = buildCommand(request);
        // sends the command from the interactor set above, with a socket
        // set in the class during the initialization,
        // acknowledgeConnection()
        esp8266Interactor.sendCommand(esp8266_command);
    }

    /**
     * Recieves a response from the esp8266, from
     * the interactor set in {@link ControlInteractor#esp8266Interactor},
     * and the socket is set in the {@link Esp8266Interactor} class,
     * we use it straight from there. Usually recieves metrics
     */
    @Override
    public ControlResponse recieveRepsonse() throws Throwable {
        Metrics metrics = esp8266Interactor.recieveMetrics();
        // builder for esp8266 metrics -> control response
        return CommandFactory.buildControlResponseFromEsp8266Metrics(metrics);
    }

    /**
     * Take the address of an esp8266 (said addr), and use the
     * {@link Esp8266Interactor#acknowledgeConnection(String, int, int)}
     * to initialize a socket in the class and the value store.
     * For Hard-Coded IP and port
     */
    @Override
    public ControlAcknowledgeResponse handleAcknowledge(ControlAcknowledge address) {
        ControlAcknowledgeResponse esp8266Acknowledge;
        // Acknowledge Connection
        boolean connStatus = esp8266Interactor.acknowledgeConnection(address.getAddress(), address.getPort(), address.getControllerKey());
        if (connStatus) {
            esp8266Acknowledge = buildAcknowledge(AcknowledgeStatus.OK);
            String name = address.getName();
            String addr = address.getAddress();
            int port = address.getPort();
            Integer controllerKey = address.getControllerKey();
            KnownCarEntity knownCarEntity = new KnownCarEntity.KnownCarEntityBuilder()
                    .withControllerKey(controllerKey)
                    .withName(name)
                    .withTime(Instant.now())
                    .withPort(port)
                    .withIpAddress(addr)
                    .build();
            // todo add database faliure
            knownCarDatabase.insertIntoDatabase(knownCarEntity);
        }
        // failed
        else esp8266Acknowledge = buildAcknowledge(AcknowledgeStatus.CANNOT_CONNECT_TO_ESP8266);
        return esp8266Acknowledge;
    }

    @Override
    public ServerAcknowledgeResponse handleServerAcknowledge(ServerAcknowledge serverAcknowledge) {
        ArrayList<KnownCarEntity> carEntities = knownCarDatabase.retrieveEntities(new CarEntityIdentifier<>(
                serverAcknowledge.getControllerKey()
        ));

        // null means error, we return on purpose if there was an exception in the db,
        //
        if (carEntities == null) {
            return ServerAcknowledgeResponse.newBuilder()
                    .setRequestStatus(ServerAcknowledgeResponse.RequestStatus.SERVER_ERROR)
                    .addAllCarEntities(new ArrayList<>())
                    .build();
        } else {
            // here, even if db authentication does not work, we dont want the
            // program not to work. So we return an OK with an empty ArrayList
            // if authentication didnt work, and the mobile client would just send
            // us the IP and port to connect to on their own, the purpose of this
            // rpc is to have mobile clients acknowledge servers AND to send
            // back the known cars to make their life easier IF they have mysql setup.
            // if not, for whatever reason, we don't want to force them to set it up,
            // it should work without it.
            ArrayList<CarEntity> protobufCarEntities = new ArrayList<>();
            carEntities.forEach(knownCarEntity -> protobufCarEntities.add(
                    CarEntity.newBuilder()
                            .setLastIP(knownCarEntity.getIpAddress())
                            .setLastPort(knownCarEntity.getPort())
                            .setLastConnected(knownCarEntity.getDiscoveryTime().toString())
                            .setName(knownCarEntity.getName())
                            .build()
            ));
            carEntities.forEach(
                    knownCarEntity -> System.out.println(knownCarEntity.getName())
            );
            return ServerAcknowledgeResponse.newBuilder()
                    .setRequestStatus(ServerAcknowledgeResponse.RequestStatus.OK)
                    .addAllCarEntities(protobufCarEntities)
                    .build();
        }
    }

    /**
     * This only works if the server is in the same network
     * as the esp8266s.
     * @param discoveryRequest req
     * @return EspResp
     */
    @Override
    public Esp8266DiscoveryResponse handleDiscoveryRequest(Esp8266DiscoveryRequest discoveryRequest) {
        /*
          todo java mDNS here
            it would also be possible to send a prompt to the esp8266 to start
            discovery, given we have the connection at our disposal.
         */
        return null;
    }
}
