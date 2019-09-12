package com.prerepa.car_rpc.main;

import com.prerepa.car_rpc.api.CarApi;

/**
 * Main class. The gRPC server is run
 * in {@link CarApi}
 * @author aditya
 */
public class Main {
    public static void main(String[] args) throws Throwable {
        CarApi carApi = new CarApi();
        carApi.start(2000);
        carApi.blockUntilShutdown();
    }
}