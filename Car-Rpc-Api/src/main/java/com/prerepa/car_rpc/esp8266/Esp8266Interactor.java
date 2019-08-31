package com.prerepa.car_rpc.esp8266;

import com.car_rpc.generated.Full_Request;
import com.car_rpc.generated.Metrics;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedOutputStream;
import com.prerepa.car_rpc.api.esp_8266.Esp8266Platform;
import com.prerepa.car_rpc.factory.ConnectionEntityBuilder;
import com.prerepa.car_rpc.shared.ConnectionEntity;
import com.prerepa.car_rpc.shared.ValueStore;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * The way the code interacts with the esp8266
 * inherits from {@link Esp8266Platform}
 *
 * 4 things it does :
 * Acknowledgements
 *  - sendAck
 *  - startConn
 * Commands
 * - recieveMetrics
 * - sendCommand
 *
 * @author aditya
 */
public class Esp8266Interactor implements Esp8266Platform {

    private SocketChannel socket;

    /**
     * Recieves metrics from the pre-set socket in the class, from the
     * esp8266 such as sensor positions.
     */
    @Override
    public Metrics recieveMetrics() throws Throwable {
        ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
        int numBytesRead = socket.read(byteBuffer);
        if (numBytesRead == -1) socket.close();
        byteBuffer.clear();
        return Metrics.parseFrom(ByteString.copyFrom(byteBuffer));
    }

    /**
     * Send a command to the esp8266 with the
     * {@link Full_Request#writeTo(CodedOutputStream)}
     */
    @Override
    public void sendCommand(Full_Request command) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
        byteBuffer.put(command.toByteArray());
        byteBuffer.flip();
        socket.write(byteBuffer);
    }

    /**
     * Start a connection with a server,
     * exists for testability and granularity.
     */
    @Override
    public SocketChannel startConnection(String address, int port) throws IOException {
        socket = SocketChannel.open(new InetSocketAddress(InetAddress.getByName(address), port));
        return socket;
    }

    /**
     * Set socket key and connection status to Valuestore -
     * and set socket locally. We return the connection status
     * for junit testability.
     * Also set the ConnectionEntity to the ValueStore for later use (callbacks maybe).
     */
    @Override
    public boolean acknowledgeConnection(String hostAddress, int port, int controllerKey) {
        boolean status;
        SocketChannel esp8266_Socket = null;
        try {
            // Starts connection - socket set along with key, in interactor too
            esp8266_Socket = startConnection(hostAddress, port);

            // conn succeded
            status = true;

            // set to valuestore
            ValueStore.singletonStore.setConnectionStatus(controllerKey, true);

            // build a connectionEntity for the valuestore
            ConnectionEntity connectionEntity = new ConnectionEntityBuilder()
                    .setEsp8266HostAddress(hostAddress)
                    .setEsp8266Port(port)
                    .setControllerKey(controllerKey)
                    .setSocketChannel(esp8266_Socket)
                    .build();

            // set to valuestore
            ValueStore.singletonStore.setConnectionEntity(controllerKey, connectionEntity);
        } catch (IOException e) {
            // conn failed
            status = false;

            // no need to set a connection entity here.
            // set fail to valuestore
            ValueStore.singletonStore.setConnectionStatus(controllerKey, false);
        }
        // set the socket if it was initialized
        if (esp8266_Socket != null) {
            this.socket = esp8266_Socket;
        }
        return status;
    }
}
