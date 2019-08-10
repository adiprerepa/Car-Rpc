package com.prerepa.car_rpc.shared;

import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * Tests for the {@link ValueStore} class.
 * Todo add more tests
 * @author aditya
 */
public class ValueStoreTest {

    @Test
    public void setKeyTest() {
        ValueStore mockStore = mock(ValueStore.class);
        String espAddress = "Address-false-lol";
        int port = 23;
        int key = 1626;
        ValueStore.setKey(espAddress, port, key);
    }
}
