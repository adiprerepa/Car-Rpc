package com.prerepa.car_rpc.esp8266;

import com.prerepa.car_rpc.api.esp_8266.Esp8266Platform;
import com.prerepa.car_rpc.controller.ControlInteractor;
import com.prerepa.car_rpc.controller.ControlReturnType;
import com.prerepa.car_rpc.shared.ValueStore;
import com.prerepa.generated.Esp8266_Command;
import com.prerepa.generated.Esp8266_Metrics;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;

public class Esp8266Interactor implements Esp8266Platform {

    @Override
    public Esp8266_Metrics recieveMetrics(Socket socket) throws Throwable {
        return Esp8266_Metrics.parseFrom(socket.getInputStream());
    }

    @Override
    public void sendCommand(Esp8266_Command command) {

    }

    @Override
    public Socket startConnection(String address, int port) throws IOException {
        return new Socket(address, port);
    }
}
