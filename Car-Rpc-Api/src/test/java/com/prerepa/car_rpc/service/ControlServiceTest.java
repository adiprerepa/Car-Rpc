package com.prerepa.car_rpc.service;

import com.car_rpc.generated.*;
import com.prerepa.car_rpc.controller.ControlInteractor;
import com.prerepa.car_rpc.controller.ControllerRequestBase;
import com.prerepa.car_rpc.database.DatabaseCredentials;
import com.prerepa.car_rpc.database.known_cars.KnownCarDatabase;
import com.prerepa.car_rpc.esp8266.Esp8266Interactor;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static com.prerepa.car_rpc.factory.CommandFactory.buildControlResponseFromEsp8266Metrics;

public class ControlServiceTest {

    @Rule
    public GrpcCleanupRule grpcCleanupRule = new GrpcCleanupRule();

    /**
     * Test that the control flow for the service works
     * (more of an instrumentation test)
     * @throws IOException for building the stub
     */
    @Test
    public void controlServiceTest_validCommand() throws IOException {
        TestKnownCarDatabase testKnownCarDatabase = new TestKnownCarDatabase();
        String serverName = InProcessServerBuilder.generateName();
        TestEsp8266Interactor testEsp8266Interactor = new TestEsp8266Interactor();
        Metrics expectedMetrics = Metrics.newBuilder()
                .setHCSR04RightDistance(3156)
                .setHCSR04FrontDistance(2624)
                .setHCSR04LeftDistance(2626)
                .build();
        testEsp8266Interactor.metrics = expectedMetrics;
        grpcCleanupRule.register(
                // Register service and add test interactor
                InProcessServerBuilder.forName(serverName).directExecutor().addService(new ControllerRequestBase(new ControlInteractor(testEsp8266Interactor, testKnownCarDatabase))).build().start()
        );
        ControllerServiceGrpc.ControllerServiceBlockingStub blockingStub = ControllerServiceGrpc.newBlockingStub(
                grpcCleanupRule.register(InProcessChannelBuilder.forName(serverName).directExecutor().build())
        );
        ControlRequest controlRequest = ControlRequest.newBuilder()
                .setControllerKey(24607)
                .setPowerState(true)
                .setAxisPlane(
                        Vector.newBuilder()
                                .setY(10)
                                .setZ(0)
                                .setX(0)
                                .build()
                ).build();
        // bogus hcsr04 numbers
        ControlResponse expectedResponse = buildControlResponseFromEsp8266Metrics(expectedMetrics);
        ControlResponse controlResponse = blockingStub.controlCommandService(controlRequest);
        Assert.assertEquals(expectedResponse, controlResponse);
    }

    /**
     * Mock out receiving and sending metrics
     */
    static class TestEsp8266Interactor extends Esp8266Interactor {

        Metrics metrics;

        @Override
        public Metrics recieveMetrics() {
            return metrics;
        }

        @Override
        public void sendCommand(Full_Request fullRequest) {
            // No-op
        }
    }

    static class TestKnownCarDatabase extends KnownCarDatabase {

        TestKnownCarDatabase() {
            // no -op
        }
    }
}
