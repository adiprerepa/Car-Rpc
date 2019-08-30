package com.prerepa.car_rpc.api.esp_8266;

import com.car_rpc.generated.Full_Request;
import com.car_rpc.generated.Metrics;
import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * Interface For the Esp8266 Platform
 * @author aditya
 */
public interface Esp8266Platform {
    Metrics recieveMetrics() throws Throwable;

    void sendCommand(Full_Request command) throws IOException;

    SocketChannel startConnection(String esp8266Endpoint, int port) throws IOException;

    boolean acknowledgeConnection(String hostAddress, int port, int controllerKey);
}
