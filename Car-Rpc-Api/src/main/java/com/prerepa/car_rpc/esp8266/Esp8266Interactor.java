package com.prerepa.car_rpc.esp8266;

import com.google.protobuf.CodedOutputStream;
import com.prerepa.car_rpc.api.esp_8266.Esp8266Platform;
import com.prerepa.car_rpc.shared.ValueStore;
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

    /**
     * Recieves metrics from a socket
     * @param socket
     * @return
     * @throws Throwable
     */
    @Override
    public Esp8266_Metrics recieveMetrics(Socket socket) throws Throwable {
        return Esp8266_Metrics.parseFrom(socket.getInputStream());
    }

    /**
     * Send a command to the esp8266 with the
     * {@link Esp8266_Command#writeTo(CodedOutputStream)}
     * @param command
     * @throws IOException
     */
    @Override
    public void sendCommand(Esp8266_Command command) throws IOException {
        OutputStream protoOutputStream = socket.getOutputStream();
        command.writeTo(protoOutputStream);
    }

    /**
     * Start a connection with a server,
     * exists for testability.
     * @param address
     * @param port
     * @return
     * @throws IOException
     */
    @Override
    public Socket startConnection(String address, int port) throws IOException {
        return new Socket(address, port);
    }


    /**
     * Set socket key and connection status to Valuestore -
     * and set socket locally
     * @param hostAddress
     * @param port
     * @param controllerKey
     */
    @Override
    public void acknowledgeConnection(String hostAddress, int port, int controllerKey) {
        Socket esp8266_Socket = null;
        try {
            // Starts connection - socket set along with key, in interactor too
            esp8266_Socket = startConnection(hostAddress, port);
            ValueStore.setEsp_connection_success(true);
            ValueStore.setKey(esp8266_Socket, controllerKey);
        } catch (IOException e) {
            ValueStore.setEsp_connection_success(false);
        }
        // error checking - todo
        if (esp8266_Socket != null) {
            this.socket = esp8266_Socket;
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
