package com.prerepa.car_rpc.shared;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Shared store for all constants and information
 * about connections.
 * @author aditya
 */
public class ValueStore {
    /**
     * We use {@link Vector} because it is thread safe.
     * TODO make a class that holds all the information of a server entity and
     * make a hashmap of it (for speed). if it is not possible, make
     * a hashCode() for this class or the vector member class.
     */
    private static ArrayList<Boolean> connectionStatuses = new ArrayList<>();
    private static ArrayList<String> esp8266Addresses = new ArrayList<>();
    private static ArrayList<Integer> controllerKeys = new ArrayList<>();
    private static ArrayList<SocketChannel> sockets = new ArrayList<>();

    public static void setEsp_connection_success(int key, boolean value) {
        int position = controllerKeys.indexOf(key);
        connectionStatuses.add(position, value);
    }

    public static boolean getEsp_connection_success(int key) {
        return connectionStatuses.get(controllerKeys.indexOf(key));
    }

    /**
     * The key is associated with the socket and address
     * @param socket
     * @param key
     * @throws IOException
     */
    public static void setKey(SocketChannel socket,  Integer key) throws IOException {
        controllerKeys.add(key);
        int position = controllerKeys.indexOf(key);
        esp8266Addresses.add(position, socket.getRemoteAddress().toString());
    }

    public static void setSocket(SocketChannel socket, int key) {
        // add to the index corresponding with the key
        sockets.add(controllerKeys.indexOf(key), socket);
    }

    public static String getAddress(int key) {
        return esp8266Addresses.get(controllerKeys.indexOf(key));
    }

    public static SocketChannel getSocket(int key) {
        return sockets.get(controllerKeys.indexOf(key));
    }

    public static boolean keyExists(int key) {
        return controllerKeys.contains(key);
    }
}
