package com.prerepa.car_rpc.shared;

import com.prerepa.car_rpc.factory.ConnectionEntityBuilder;
import org.junit.Assert;
import org.junit.Test;

public class ValueStoreTest {

    @Test
    public void singletonIsInitialized() {
        Assert.assertNotNull(ValueStore.singletonStore);
    }

    @Test
    public void connectionEntitySet_notNullControllerKey() {
        int controllerKey = 11111;
        ConnectionEntity testConnectionEntity = new ConnectionEntityBuilder()
                .setEsp8266HostAddress("x.x.x.x")
                .setEsp8266Port(0)
                .setControllerKey(controllerKey)
                .build();
        ValueStore.singletonStore.setConnectionEntity(controllerKey, testConnectionEntity);
        ConnectionEntity returnedEntity = ValueStore.singletonStore.getConnectionEntity(controllerKey);
        Assert.assertEquals(testConnectionEntity, returnedEntity);
    }

    @Test
    public void connectionEntitySet_badControllerKey() {
        int controllerKey = 11111;
        ConnectionEntity testConnectionEntity = new ConnectionEntityBuilder()
                .setEsp8266HostAddress("x.x.x.x")
                .setEsp8266Port(0)
                .setControllerKey(controllerKey)
                .build();
        ValueStore.singletonStore.setConnectionEntity(controllerKey, testConnectionEntity);
        ConnectionEntity returnedEntity = ValueStore.singletonStore.getConnectionEntity(15);
        Assert.assertNull(returnedEntity);
    }

    @Test
    public void connectionEntitySet_verifyNull() {
        int controllerKey = 11111;
        ValueStore.singletonStore.setConnectionEntity(controllerKey, null);
        ConnectionEntity returnedEntity = ValueStore.singletonStore.getConnectionEntity(controllerKey);
        Assert.assertNull(returnedEntity);
    }

    @Test
    public void connectionStatusSet_connectionSucceeded() {
        int controllerKey = 11111;
        ValueStore.singletonStore.setConnectionStatus(controllerKey, true);
        Boolean returnedStatus = ValueStore.singletonStore.getLastConnectionStatus(controllerKey);
        Assert.assertTrue(returnedStatus);
    }

    @Test
    public void connectionStatusSet_connectionFailed() {
        int conntrollerKey = 11111;
        ValueStore.singletonStore.setConnectionStatus(conntrollerKey, false);
        Boolean returnedStatus = ValueStore.singletonStore.getLastConnectionStatus(conntrollerKey);
        Assert.assertFalse(returnedStatus);
    }

    @Test
    public void connectionStatusNotSet_badControllerKey() {
        int badControllerKey = 1667;
        ValueStore.singletonStore.setConnectionStatus(badControllerKey, null);
        Boolean badStatus = ValueStore.singletonStore.getLastConnectionStatus(badControllerKey);
        Assert.assertNull(badStatus);
    }
}
