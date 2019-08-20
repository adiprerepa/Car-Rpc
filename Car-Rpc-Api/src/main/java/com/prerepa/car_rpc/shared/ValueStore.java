package com.prerepa.car_rpc.shared;

import java.net.Socket;
import java.util.Vector;

/**
 * Shared store for all constants and information
 * about connections.
 * @author aditya
 */
public class ValueStore {
    /**
     * We use {@link Vector} because it is thread safe.
     */
    private static Vector<Boolean> connectionStatuses = new Vector<>();
    private static Vector<String> esp8266Addresses = new Vector<>();
    private static Vector<Integer> esp8266Ports = new Vector<>();
    private static Vector<Integer> controllerKeys = new Vector<>();
    private static Vector<Socket> sockets = new Vector<>();

    public static void setEsp_connection_success(int key, boolean value) {
        int position = controllerKeys.indexOf(key);
        connectionStatuses.add(position, value);
    }

    public static boolean getEsp_connection_success(int key) {
        return connectionStatuses.get(controllerKeys.indexOf(key));
    }

    public static void setKey(String esp8266Address, Integer port, Integer key) {
        controllerKeys.add(key);
        int position = controllerKeys.indexOf(key);
        esp8266Addresses.add(position, esp8266Address);
        esp8266Ports.add(position, port);
    }

    public static void setSocket(Socket socket, int key) {
        // add to the index corresponding with the key
        sockets.add(controllerKeys.indexOf(key), socket);
    }

    public static String getAddress(int key) {
        return esp8266Addresses.get(controllerKeys.indexOf(key));
    }

    public static int getPort(int key) {
        return esp8266Ports.get(controllerKeys.indexOf(key));
    }

    public static Socket getSocket(int key) {
        return sockets.get(controllerKeys.indexOf(key));
    }

    public static boolean keyExists(int key) {
        return controllerKeys.contains(key);
    }
}
