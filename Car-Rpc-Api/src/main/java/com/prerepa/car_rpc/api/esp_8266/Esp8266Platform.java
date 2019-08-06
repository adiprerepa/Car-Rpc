package com.prerepa.car_rpc.api.esp_8266;

import java.net.Socket;

/**
 * Interface For the Esp8266 Platform
 * todo add metrics support
 */
public interface Esp8266Platform {
    Esp8266_Metrics recieveMetrics(Socket raw_socket, Esp8266_Command command);
}
