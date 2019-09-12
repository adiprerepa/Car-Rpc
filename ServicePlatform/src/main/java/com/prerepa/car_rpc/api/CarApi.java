package com.prerepa.car_rpc.api;

import com.prerepa.car_rpc.controller.ControlInteractor;
import com.prerepa.car_rpc.controller.ControllerRequestBase;
import com.prerepa.car_rpc.database.DatabaseCredentials;
import com.prerepa.car_rpc.database.known_cars.KnownCarDatabase;
import com.prerepa.car_rpc.esp8266.Esp8266Interactor;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * gRPC service declaration
 * @author aditya
 */
public class CarApi {

    private Server server;

    public void start(int serverPort) {
        // provide database
        DatabaseCredentials databaseCredentials = new DatabaseCredentials.DatabaseCredentialsBuilder()
                .withDatabaseUrl("---")
                .withDatabaseUsername("aditya")
                .withDatabasePassword("adityapc")
                .build();
        KnownCarDatabase knownCarDatabase = new KnownCarDatabase.KnownCarDatabaseBuilder()
                .withDatabaseCredentials(databaseCredentials)
                .withTableName("known_cars")
                .build();

        try {
            server = ServerBuilder
                    .forPort(serverPort)
                    .addService(new ControllerRequestBase(
                            new ControlInteractor(new Esp8266Interactor(), knownCarDatabase)))
                    .build()
                    .start();
            System.out.printf("Server Started on port %d\n", serverPort);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.err.println("JVM Shutdown, bye!");
                CarApi.this.stopServer();
            }));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void blockUntilShutdown() throws Throwable {
        if (server != null)
            server.awaitTermination();
    }

    Server getServer() {
        return this.server;
    }

    private void stopServer() {
        if (server != null) server.shutdown();
    }
}
