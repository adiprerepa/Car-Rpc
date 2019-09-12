package com.prerepa.car_rpc.service;

import com.car_rpc.generated.ControlAcknowledge;
import com.car_rpc.generated.ControlAcknowledgeResponse;
import com.car_rpc.generated.ControllerServiceGrpc;
import com.prerepa.car_rpc.controller.ControlInteractor;
import com.prerepa.car_rpc.controller.ControllerRequestBase;
import com.prerepa.car_rpc.database.DatabaseCredentials;
import com.prerepa.car_rpc.database.InsertionStatus;
import com.prerepa.car_rpc.database.known_cars.KnownCarDatabase;
import com.prerepa.car_rpc.database.known_cars.entities.KnownCarEntity;
import com.prerepa.car_rpc.esp8266.Esp8266Interactor;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import io.grpc.inprocess.InProcessChannelBuilder;
import java.io.IOException;

/**
 * Tests for the acknowledge service
 * @author aditya
 */
public class AcknowledgeServiceTest {

    /**
     * Rule for cleaning up mock clients
     */
    @Rule
    public GrpcCleanupRule cleanupRule = new GrpcCleanupRule();

    /**
     * Test that when the server could connect to the esp8266, it returns the right response
     * @throws IOException for serverBuilder - TestManagedChannel
     */
    @Test
    public void carApiServiceAcknowledge_couldConnect() throws IOException {
        /*
          Given
         */
        TestKnownCarDatabase knownCarDatabase = new TestKnownCarDatabase();
        String serverName = InProcessServerBuilder.generateName();
        TestEsp8266Interactor testEsp8266Interactor = new TestEsp8266Interactor();
        // could connect
        testEsp8266Interactor.setToReturn(true);
        // register service with testEsp8266Interactor
        cleanupRule.register(
                InProcessServerBuilder.forName(serverName).directExecutor().addService(new ControllerRequestBase(new ControlInteractor(testEsp8266Interactor, knownCarDatabase))).build().start());
        ControllerServiceGrpc.ControllerServiceBlockingStub blockingStub = ControllerServiceGrpc.newBlockingStub(
                // Create a client channel and register for automatic graceful shutdown.
                cleanupRule.register(InProcessChannelBuilder.forName(serverName).directExecutor().build())
        );
        // Build the acknowledge to be sent
        ControlAcknowledge controlAcknowledge = ControlAcknowledge.newBuilder().setAddress("X.X.X.X").setPort(3262).setName("fa").setControllerKey(27).build();
        // the expected response
        ControlAcknowledgeResponse expectedResponse = ControlAcknowledgeResponse.newBuilder()
                .setStatus(ControlAcknowledgeResponse.Request_Status.OK).build();
        /*
          When
         */
        // the actual response
        ControlAcknowledgeResponse response = blockingStub.controlAcknowledgeService(controlAcknowledge);
        /*
          Then
         */
        Assert.assertEquals(expectedResponse, response);
    }

    /**
     * Test that when the server was not able to connect to the esp8266, then
     * it returns the appropriate response, which is {@link ControlAcknowledgeResponse.Request_Status}
     * Unable to connect
     * @throws IOException for the testManagedChannel
     */
    @Test
    public void carApiServiceAcknowledge_unableToConnect() throws IOException {
        TestKnownCarDatabase knownCarDatabase = new TestKnownCarDatabase();
        String serverName = InProcessServerBuilder.generateName();
        TestEsp8266Interactor testEsp8266Interactor = new TestEsp8266Interactor();
        // could not connect
        testEsp8266Interactor.setToReturn(false);
        cleanupRule.register(
                InProcessServerBuilder.forName(serverName).directExecutor().addService(new ControllerRequestBase(new ControlInteractor(testEsp8266Interactor, knownCarDatabase))).build().start());
        ControllerServiceGrpc.ControllerServiceBlockingStub blockingStub = ControllerServiceGrpc.newBlockingStub(
                // Create a client channel and register for automatic graceful shutdown.
                cleanupRule.register(InProcessChannelBuilder.forName(serverName).directExecutor().build())
        );

        ControlAcknowledge controlAcknowledge = ControlAcknowledge.newBuilder().setAddress("X.X.X.X").setPort(1515).setName("f").setControllerKey(125071).build();
        ControlAcknowledgeResponse expectedResponse = ControlAcknowledgeResponse.newBuilder()
                .setStatus(ControlAcknowledgeResponse.Request_Status.UNABLE_TO_CONNECT_TO_ESP8266).build();
        ControlAcknowledgeResponse response = blockingStub.controlAcknowledgeService(controlAcknowledge);
        Assert.assertEquals(expectedResponse, response);
    }

    @Test
    public void carApiServiceAcknowledge_mockControlInteractorResponse() throws IOException{
        TestKnownCarDatabase knownCarDatabase = new TestKnownCarDatabase();
        String serverName = InProcessServerBuilder.generateName();
        TestControlInteractor testControlInteractor = new TestControlInteractor(new Esp8266Interactor(), knownCarDatabase);
        // could connect
        testControlInteractor.setRequestStatus(ControlAcknowledgeResponse.Request_Status.OK);
        cleanupRule.register(
                InProcessServerBuilder.forName(serverName).directExecutor().addService(new ControllerRequestBase(testControlInteractor)).build().start());
        ControllerServiceGrpc.ControllerServiceBlockingStub blockingStub = ControllerServiceGrpc.newBlockingStub(
                // Create a client channel and register for automatic graceful shutdown.
                cleanupRule.register(InProcessChannelBuilder.forName(serverName).directExecutor().build())
        );
        ControlAcknowledge controlAcknowledge = ControlAcknowledge.newBuilder().setAddress("X.X.X.X").setPort(2673).setName("f").setControllerKey(943).build();
        ControlAcknowledgeResponse expectedResponse = ControlAcknowledgeResponse.newBuilder()
                .setStatus(ControlAcknowledgeResponse.Request_Status.OK).build();
        ControlAcknowledgeResponse response = blockingStub.controlAcknowledgeService(controlAcknowledge);
        Assert.assertEquals(expectedResponse, response);
    }

    @Test
    public void carApiServiceAcknowledge_unableToConnectControlInteractor() throws IOException {
        // mock out db
        TestKnownCarDatabase knownCarDatabase = new TestKnownCarDatabase();
        String serverName = InProcessServerBuilder.generateName();
        // pass interactor and db
        TestControlInteractor testControlInteractor = new TestControlInteractor(new Esp8266Interactor(), knownCarDatabase);
        // could connect
        testControlInteractor.setRequestStatus(ControlAcknowledgeResponse.Request_Status.UNABLE_TO_CONNECT_TO_ESP8266);
        cleanupRule.register(
                InProcessServerBuilder.forName(serverName).directExecutor().addService(new ControllerRequestBase(testControlInteractor)).build().start());
        ControllerServiceGrpc.ControllerServiceBlockingStub blockingStub = ControllerServiceGrpc.newBlockingStub(
                // Create a client channel and register for automatic graceful shutdown.
                cleanupRule.register(InProcessChannelBuilder.forName(serverName).directExecutor().build())
        );
        ControlAcknowledge controlAcknowledge = ControlAcknowledge.newBuilder().setAddress("X.X.X.X").setPort(2673).setName("f").setControllerKey(943).build();
        ControlAcknowledgeResponse expectedResponse = ControlAcknowledgeResponse.newBuilder()
                .setStatus(ControlAcknowledgeResponse.Request_Status.UNABLE_TO_CONNECT_TO_ESP8266).build();
        ControlAcknowledgeResponse response = blockingStub.controlAcknowledgeService(controlAcknowledge);
        Assert.assertEquals(expectedResponse, response);
    }

    /**
     * We inject this class - simulate acknowledgements
     */
     static class TestEsp8266Interactor extends Esp8266Interactor {

         // return t/f for was able to connect
        boolean toReturn;

        @Override
        public boolean acknowledgeConnection(String host, int port, int controllerKey) {
            return toReturn;
        }

        void setToReturn(boolean toReturn) {
            this.toReturn = toReturn;
        }
    }

    /**
     * Inject the mock interactor in last 2 tests
     */
    static class TestControlInteractor extends ControlInteractor {

         ControlAcknowledgeResponse.Request_Status requestStatus;

         TestControlInteractor(Esp8266Interactor esp8266Interactor, KnownCarDatabase knownCarDatabase) {
            super(esp8266Interactor, knownCarDatabase);
        }

        @Override
        public ControlAcknowledgeResponse handleAcknowledge(ControlAcknowledge address) {
            return ControlAcknowledgeResponse.newBuilder().setStatus(requestStatus).build();
        }

        void setRequestStatus(ControlAcknowledgeResponse.Request_Status requestStatus) {
            this.requestStatus = requestStatus;
        }
    }

    static class TestKnownCarDatabase extends KnownCarDatabase {

        TestKnownCarDatabase() {
            // no-op
        }

        @Override
        public InsertionStatus insertIntoDatabase(KnownCarEntity knownCarEntity) {
            return InsertionStatus.OK;
        }
    }
}
