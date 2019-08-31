package com.prerepa.car_rpc.shared;

import java.net.InetAddress;
import java.nio.channels.SocketChannel;

/**
 * Connection Entity between device client and esp8266.
 */
public class ConnectionEntity {

    private String esp8266Address;
    private int esp8266Port;
    private SocketChannel esp8266SocketChannel;
    private int controllerKey;

    public ConnectionEntity(String esp8266Address, int esp8266Port, SocketChannel esp8266SocketChannel, int controllerKey) {
        this.esp8266Address = esp8266Address;
        this.esp8266Port = esp8266Port;
        this.esp8266SocketChannel = esp8266SocketChannel;
        this.controllerKey = controllerKey;
    }

    public String getEsp8266Address() {
        return esp8266Address;
    }

    public int getEsp8266Port() {
        return esp8266Port;
    }

    public SocketChannel getEsp8266SocketChannel() {
        return esp8266SocketChannel;
    }

    public int getControllerKey() {
        return controllerKey;
    }
}
