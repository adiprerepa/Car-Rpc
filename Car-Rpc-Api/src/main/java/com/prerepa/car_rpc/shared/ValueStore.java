package com.prerepa.car_rpc.shared;

import java.util.HashMap;

/**
 * Shared store for all constants and information
 * about connections.
 * @author aditya
 */
public class ValueStore {

    /**
     * Singleton instance.
     */
    public static ValueStore singletonStore = new ValueStore();

    /**
     * The integer is the key which we pull the connection entity from.
     */
    private HashMap<Integer, ConnectionEntity> connectionEntities = new HashMap<>();

    /**
     * HashMap of successes of connections, integer is key and boolean is the
     * last success/fail - previous connection statuses get overwritten.
     */
    private HashMap<Integer, Boolean> connectionSuccesses = new HashMap<>();

    /**
     * The logic here is to put the connection entity in position
     * controllerKey for easy access (HashMap find time is O(1)).
     */
    public void setConnectionEntity(int controllerKey, ConnectionEntity connectionEntity) {
        connectionEntities.put(controllerKey, connectionEntity);
    }

    /**
     * Get a connection entity from a controller key, which will be sent
     * every iteration of command from the app.
     */
    public ConnectionEntity getConnectionEntity(int controllerKey) {
        return connectionEntities.get(controllerKey);
    }

    /**
     * Set a connection success/fail after trying to connect with the car
     * Usage : to set status for app.
     */
    public void setConnectionStatus(int controllerKey, Boolean connectionStatus) {
        connectionSuccesses.put(controllerKey, connectionStatus);
    }

    /**
     * Get the last connection status after trying to connect to the esp8266
     * Usage : to get and send status to app.
     */
    public Boolean getLastConnectionStatus(int controllerKey) {
        return connectionSuccesses.get(controllerKey);
    }
}
