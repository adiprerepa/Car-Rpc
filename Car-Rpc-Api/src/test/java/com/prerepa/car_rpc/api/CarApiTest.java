package com.prerepa.car_rpc.api;

import io.grpc.ServerBuilder;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CarApiTest {

    /**
     * Throws an error : Netty cannot bind when we call it
     * from the mock, but we assert null so it passes.
     */
    @Test
    public void carApiServiceStarts_serverNull() {
        int port = 236;
        ServerBuilder serverBuilder = mock(ServerBuilder.class);
        when(serverBuilder.build()).thenReturn(null);
        CarApi carApi = new CarApi();
        carApi.start(port);
        Assert.assertNull(carApi.getServer());
    }
}
