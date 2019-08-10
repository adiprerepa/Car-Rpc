package com.prerepa.car_rpc.api.esp_8266;

import com.prerepa.generated.Esp8266_Command;
import com.prerepa.generated.Esp8266_Metrics;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Interface For the Esp8266 Platform
 * todo add metrics support
 * @author aditya
 */
public interface Esp8266Platform {
    Esp8266_Metrics recieveMetrics(Socket raw_socket) throws Throwable;

    void sendCommand(Esp8266_Command command) throws IOException;

    Socket startConnection(String esp8266Endpoint, int port) throws UnknownHostException, IOException;

    void acknowledgeConnection(String hostAddress, int port, int controllerKey);
}
