package com.prerepa.car_rpc.api;

import com.google.gson.Gson;
import com.prerepa.car_rpc.controller.ControlInteractor;
import com.prerepa.car_rpc.controller.ControllerRequestBase;
import com.prerepa.car_rpc.database.DatabaseCredentials;
import com.prerepa.car_rpc.database.known_cars.KnownCarDatabase;
import com.prerepa.car_rpc.esp8266.Esp8266Interactor;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * gRPC service declaration
 * @author aditya
 */
public class CarApi {

    private Server server;

    public void start(Path databaseCredentialsPath, int serverPort) {
        // parse json
        Gson gson = new Gson();
        String jsonContent = null;
        try {
            jsonContent = Files.readString(databaseCredentialsPath, StandardCharsets.US_ASCII);
        } catch (IOException e) {
            System.err.println("Error with reading content of file");
            e.printStackTrace();
        }
        DatabaseCredentials credentials = gson.fromJson(jsonContent, DatabaseCredentials.class);
        KnownCarDatabase knownCarDatabase = new KnownCarDatabase.KnownCarDatabaseBuilder()
                .withDatabaseCredentials(credentials)
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

    private void stopServer() {
        if (server != null) server.shutdown();
    }
}
