package com.prerepa.car_rpc.controller;

import com.car_rpc.generated.*;
import com.prerepa.car_rpc.esp8266.Esp8266Interactor;
import com.prerepa.car_rpc.factory.CommandFactory;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static com.prerepa.car_rpc.factory.CommandFactory.buildCommand;
import static com.prerepa.car_rpc.factory.CommandFactory.buildControlResponseFromEsp8266Metrics;
import static org.mockito.Mockito.*;

public class ControlInteractorTest {

    private static Metrics testMetrics = Metrics.newBuilder()
            .setHCSR04LeftDistance(315)
            .setHCSR04FrontDistance(2365)
            .setHCSR04RightDistance(426)
            .build();

    @Test
    public void esp8266VerifyCommandIsSent() throws IOException {
        // Given
        // fake interactor to inject
        TestEsp8266Interactor testEsp8266Interactor = mock(TestEsp8266Interactor.class);
        ControlInteractor controlInteractor = new ControlInteractor(testEsp8266Interactor);
        ControlRequest testRequest = ControlRequest.newBuilder()
                .setAxisPlane(Vector.newBuilder().setX(2).setZ(3).setY(23).build())
                .setPowerState(false)
                .setControllerKey(1235)
                .build();
        // When
        controlInteractor.handleControllerRequest(testRequest);

        // Then
        Mockito.verify(testEsp8266Interactor, times(1)).sendCommand(buildCommand(testRequest));
    }

    @SuppressWarnings("all") // for accessing commandfactory through reference
    @Ignore("This passes when run alone, but fails during" +
            " a package run? debug this later.")
    @Test
    public void esp8266VerifyCommandIsBuilt() throws IOException {
        // Given
        // fake interactor to inject
        CommandFactory commandFactory = mock(CommandFactory.class);
        ControlInteractor controlInteractor = new ControlInteractor(new TestEsp8266Interactor());
        ControlRequest testRequest = ControlRequest.newBuilder()
                .setAxisPlane(Vector.newBuilder().setX(2).setZ(3).setY(23).build())
                .setPowerState(false)
                .setControllerKey(1235)
                .build();
        // When
        controlInteractor.handleControllerRequest(testRequest);

        // Then
        Mockito.verify(commandFactory, times(1)).buildCommand(testRequest);
    }

    @Test
    public void esp8266RecieveMetricsCalled() throws Throwable {
        // Given
        // fake interactor to inject
        TestEsp8266Interactor testEsp8266Interactor = spy(new TestEsp8266Interactor());
        ControlInteractor controlInteractor = new ControlInteractor(testEsp8266Interactor);
        // When
        controlInteractor.recieveRepsonse();

        // Then
        Mockito.verify(testEsp8266Interactor, times(1)).recieveMetrics();
    }

    @Test
    public void esp8266RecieveMetricsCorrect() throws Throwable {
        // Given
        // fake interactor to inject
        TestEsp8266Interactor testEsp8266Interactor = spy(new TestEsp8266Interactor());
        ControlInteractor controlInteractor = new ControlInteractor(testEsp8266Interactor);
        // When
        ControlResponse metrics = controlInteractor.recieveRepsonse();

        Assert.assertEquals(buildControlResponseFromEsp8266Metrics(testMetrics), metrics);
    }

     static class TestEsp8266Interactor extends Esp8266Interactor {

        @Override
        public void sendCommand(Full_Request request) {
            // No-op - we test to see if we got here with mockito.verify
        }

        @Override
        public Metrics recieveMetrics() {
            return testMetrics;
        }
    }
}
