package com.prerepa.esp8266_client;

import com.google.protobuf.ByteString;
import com.prerepa.generated.Request;
import com.prerepa.generated.Response;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

class Esp8266ClientInteractor {

    private SocketChannel esp8266Socket;

    public void setEsp8266Socket(String host, int port) throws IOException {
        esp8266Socket = SocketChannel.open(new InetSocketAddress(InetAddress.getByName(host), port));
    }

    public void sendRequest(Request request) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
        byteBuffer.put(request.toByteArray());
        byteBuffer.flip();
        esp8266Socket.write(byteBuffer);
    }

    public Response recieveResponse() throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
        int numBytesRead = esp8266Socket.read(byteBuffer);
        if (numBytesRead == -1) esp8266Socket.close();
        byteBuffer.flip();
        Response response = Response.parseFrom(ByteString.copyFrom(byteBuffer));
        return response;
    }
}
