package com.prerepa.car_rpc.factory;

import com.prerepa.car_rpc.shared.ConnectionEntity;

import java.nio.channels.SocketChannel;

public class ConnectionEntityBuilder {

    private String esp8266HostAddress;
    private int esp8266Port;
    private int controllerKey;
    private SocketChannel socketChannel;

    public ConnectionEntityBuilder setEsp826HostAddress(String hostAddress) {
        this.esp8266HostAddress = hostAddress;
        return this;
    }

    public ConnectionEntityBuilder setEsp8266Port(int esp8266Port) {
        this.esp8266Port = esp8266Port;
        return this;
    }

    public ConnectionEntityBuilder setControllerKey(int key) {
        this.controllerKey = key;
        return this;
    }

    public ConnectionEntityBuilder setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
        return this;
    }

    public ConnectionEntity build() {
        return new ConnectionEntity(esp8266HostAddress, esp8266Port, socketChannel, controllerKey);
    }

    public String getEsp8266HostAddress() {
        return esp8266HostAddress;
    }

    public int getEsp8266Port() {
        return esp8266Port;
    }

    public int getControllerKey() {
        return controllerKey;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }
}
