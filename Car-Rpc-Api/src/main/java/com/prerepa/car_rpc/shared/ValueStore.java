package com.prerepa.car_rpc.shared;

import java.net.Socket;
import java.util.Vector;

/**
 * Shared store for all constants and information
 * about connections.
 * @author aditya
 */
public class ValueStore {
    private static boolean esp_connection_success = true;
    /**
     * We use {@link Vector} because it is thread safe.
     */
    private static Vector<String> esp8266Addresses = new Vector<>();
    private static Vector<Integer> esp8266Ports = new Vector<>();
    private static Vector<Integer> controllerKeys = new Vector<>();
    private static Vector<Socket> sockets = new Vector<>();

    public static void setEsp_connection_success(boolean value) {
        esp_connection_success = value;
    }

    public static boolean getEsp_connection_success() {
        return esp_connection_success;
    }

    public static void setKey(String esp8266Address, Integer port, Integer key) {
        esp8266Addresses.add(esp8266Address);
        esp8266Ports.add(port);
        controllerKeys.add(key);
    }

    public static void setSocket(Socket socket) {
        sockets.add(socket);
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
