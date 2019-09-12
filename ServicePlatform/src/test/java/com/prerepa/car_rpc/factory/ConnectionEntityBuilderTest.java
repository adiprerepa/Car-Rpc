package com.prerepa.car_rpc.factory;

import com.prerepa.car_rpc.shared.ConnectionEntity;
import org.junit.Assert;
import org.junit.Test;

public class ConnectionEntityBuilderTest {

    @Test
    public void connectionEntity_nullControllerKey() {
        ConnectionEntity connectionEntity = new ConnectionEntityBuilder()
                .setControllerKey(null)
                .build();
        Assert.assertNull(connectionEntity.getControllerKey());
    }

    @Test
    public void connectionEntity_nullAddress() {
        ConnectionEntity connectionEntity = new ConnectionEntityBuilder()
                .setEsp8266HostAddress(null)
                .build();
        Assert.assertNull(connectionEntity.getEsp8266Address());
    }

    @Test
    public void connectionEntity_nullSocketChannel() {
        ConnectionEntity connectionEntity = new ConnectionEntityBuilder()
                .setSocketChannel(null)
                .build();
        Assert.assertNull(connectionEntity.getEsp8266SocketChannel());
    }

    @Test
    public void connectionEntity_nullPort() {
        ConnectionEntity connectionEntity = new ConnectionEntityBuilder()
                .setEsp8266Port(null)
                .build();
        Assert.assertNull(connectionEntity.getEsp8266Port());
    }

    @Test
    public void connectionEntity_notNullPort() {
        Integer port = 12578;
        ConnectionEntity connectionEntity = new ConnectionEntityBuilder()
                .setEsp8266Port(port)
                .build();
        Assert.assertEquals(port, connectionEntity.getEsp8266Port());
    }

    @Test
    public void connectionEntity_notNullAddress() {
        String address = "x.x.x.x";
        ConnectionEntity connectionEntity = new ConnectionEntityBuilder()
                .setEsp8266HostAddress(address)
                .build();
        Assert.assertEquals(address, connectionEntity.getEsp8266Address());
    }

    @Test
    public void connectionEntity_notNullControllerKey() {
        Integer controllerKey = 203957;
        ConnectionEntity connectionEntity = new ConnectionEntityBuilder()
                .setControllerKey(controllerKey)
                .build();
        Assert.assertEquals(controllerKey, connectionEntity.getControllerKey());
    }
}
