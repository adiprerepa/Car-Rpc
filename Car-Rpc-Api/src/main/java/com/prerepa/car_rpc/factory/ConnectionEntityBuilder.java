package com.prerepa.car_rpc.factory;

import com.prerepa.car_rpc.shared.ConnectionEntity;

import java.nio.channels.SocketChannel;

/**
 * Builder for {@link ConnectionEntity}.
 */
public class ConnectionEntityBuilder {

    private String esp8266HostAddress;
    private Integer esp8266Port;
    private Integer controllerKey;
    private SocketChannel socketChannel;

    public ConnectionEntityBuilder setEsp8266HostAddress(String hostAddress) {
        this.esp8266HostAddress = hostAddress;
        return this;
    }

    public ConnectionEntityBuilder setEsp8266Port(Integer esp8266Port) {
        this.esp8266Port = esp8266Port;
        return this;
    }

    public ConnectionEntityBuilder setControllerKey(Integer key) {
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
}
