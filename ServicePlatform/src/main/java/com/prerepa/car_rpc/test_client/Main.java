package com.prerepa.car_rpc.test_client;

import com.car_rpc.generated.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;

/**
 * Test client for server- ignore
 */
public class Main {

    private final ManagedChannel channel;
    private final ControllerServiceGrpc.ControllerServiceBlockingStub blockingStub;

    public Main(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host ,port)
                .usePlaintext()
                .build());
    }

    Main (ManagedChannel channel) {
        this.channel= channel;
        blockingStub = ControllerServiceGrpc.newBlockingStub(channel);
    }

    private void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    void serverAck() {
        ServerAcknowledge serverAcknowledge = ServerAcknowledge.newBuilder()
                .setControllerKey(235)
                .build();
        ServerAcknowledgeResponse acknowledgeResponse = null;
        try {
            acknowledgeResponse = blockingStub.serverAcknowledgeService(serverAcknowledge);
            System.out.println("after ack");
        } catch (StatusRuntimeException e) {
            System.out.println("rpc failed");
            e.printStackTrace();
        }

        acknowledgeResponse.getCarEntitiesList().forEach(carEntity ->
                System.out.println(carEntity.getName()));
        System.out.println(acknowledgeResponse.getCarEntitiesList().toString());
    }

    void controlAck() {
        ControlAcknowledge controlAcknowledge = ControlAcknowledge.newBuilder()
                .setName("Third CaR!")
                .setAddress("10.0.1.198")
                .setPort(42069)
                .setControllerKey(235)
                .build();
        ControlAcknowledgeResponse response = null;
        try {
            response = blockingStub.controlAcknowledgeService(controlAcknowledge);
        } catch (StatusRuntimeException e) {
            e.printStackTrace();
        }
        System.out.println(response.getStatus());
    }

    void commandReq() {
        ControlRequest controlRequest = ControlRequest.newBuilder()
                .setAxisPlane(
                        Vector.newBuilder().setX(0).setY(0).setZ(-10).build()
                ).setControllerKey(235)
                .setPowerState(true)
                .build();
        ControlResponse controlResponse = null;
        try {
            controlResponse = blockingStub.controlCommandService(controlRequest);
        } catch (StatusRuntimeException e) {
            e.printStackTrace();
        }
        System.out.printf("%d %d %d\n", controlResponse.getHcsr04FrontDistance(), controlResponse.getHcsr04LeftDistance(), controlResponse.getHcsr04RightDistance());
    }

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main("localhost", 2000);
        try {
            main.serverAck();
            main.controlAck();
            main.commandReq();
            main.commandReq();
            main.commandReq();
        } finally {
            main.shutdown();
        }
    }
}
