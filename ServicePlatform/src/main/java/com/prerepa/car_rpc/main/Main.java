package com.prerepa.car_rpc.main;

import com.prerepa.car_rpc.api.CarApi;

import java.nio.file.Path;

/**
 * Main class. The gRPC server is run
 * in {@link CarApi}
 * @author aditya
 */
public class Main {
    public static void main(String[] args) throws Throwable {
        CarApi carApi = new CarApi();
        Path databseCredentialsPath = Path.of(
                String.format("%s/Car-Rpc/ServicePlatform/src/main/resources/database_credentials.json", System.getenv("HOME")));

        if (args.length != 0)
            carApi.start(databseCredentialsPath, Integer.parseInt(args[0]));
        else
            carApi.start(databseCredentialsPath, 2000);
        carApi.blockUntilShutdown();
    }
}