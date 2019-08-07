package com.prerepa.car_rpc.api;

import com.prerepa.car_rpc.controller.ControllerRequestBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * gRPC service declaration
 */
public class CarApi {
    private Server server;

    public void start(int serverPort) {
        try {
            server = ServerBuilder
                    .forPort(serverPort)
                    .addService(new ControllerRequestBase())
                    .build()
                    .start();
            System.out.println("Server Started");
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
            server.shutdown();
    }

    private void stopServer() {
        if (server != null) server.shutdown();
    }
}
