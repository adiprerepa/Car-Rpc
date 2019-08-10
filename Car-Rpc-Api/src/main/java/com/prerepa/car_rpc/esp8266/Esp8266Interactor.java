package com.prerepa.car_rpc.esp8266;

import com.prerepa.car_rpc.api.esp_8266.Esp8266Platform;
import com.prerepa.generated.Esp8266_Command;
import com.prerepa.generated.Esp8266_Metrics;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * The way the code interacts with the esp8266
 * inherits from {@link Esp8266Platform}
 * @author aditya
 */
public class Esp8266Interactor implements Esp8266Platform {

    private Socket socket;

    @Override
    public Esp8266_Metrics recieveMetrics(Socket socket) throws Throwable {
        return Esp8266_Metrics.parseFrom(socket.getInputStream());
    }

    @Override
    public void sendCommand(Esp8266_Command command) throws IOException {
        OutputStream protoOutputStream = socket.getOutputStream();
        command.writeTo(protoOutputStream);
    }

    @Override
    public Socket startConnection(String address, int port) throws IOException {
        return new Socket(address, port);
    }

    @Override
    public void setSocketConnection(Socket socket) {
        this.socket = socket;
    }
}
