#pragma once

// ifdef ARDUINO: then this script is being compiled by the Esp8266 (Esp8266.ino), and has to
//      - Recieve command
//      - Send metrics
// else: then this script is being compiled by a Linux machine (dummy_client.cpp), and has to
//      - Send command
//      - Receive metrics
//
// in that order.

struct InputData {
#ifdef ARDUINO
    static const unsigned buffer_size = esp8266_Esp8266_Command_size;
    esp8266_Esp8266_Command command;
#else
    static const unsigned buffer_size = esp8266_Esp8266_Metrics_size;
    esp8266_Esp8266_Metrics metrics;
#endif

    uint8_t buffer[buffer_size];
    pb_istream_t stream;

    InputData() { refresh(); }

    void refresh() {
        memset(buffer, 0, buffer_size);
#ifdef ARDUINO
        command = esp8266_Esp8266_Command_init_zero;
        stream = pb_istream_from_buffer(buffer, buffer_size);
#else
        metrics = esp8266_Esp8266_Metrics_init_zero;
        stream = pb_istream_from_buffer(buffer, buffer_size);
#endif
    }

    void readBufferFromClient() {
#ifdef ARDUINO
        client.read(buffer, client.available());
#else
        read(client_sock, buffer, buffer_size);
#endif
    }

    void decode() {
#ifdef ARDUINO
        pb_decode(&stream, esp8266_Esp8266_Command_fields, &command);
#else
        pb_decode(&stream, esp8266_Esp8266_Metrics_fields, &metrics);
#endif
    }

    void readAndDecode() { readBufferFromClient(); decode(); }
} input;

struct OutputData {
#ifdef ARDUINO
    static const unsigned buffer_size = esp8266_Esp8266_Metrics_size;
    esp8266_Esp8266_Metrics metrics;
#else
    static const unsigned buffer_size = esp8266_Esp8266_Command_size;
    esp8266_Esp8266_Command command;
#endif

    uint8_t buffer[buffer_size];
    pb_ostream_t stream;

    OutputData() { refresh(); }

    void refresh() {
        memset(buffer, 0, buffer_size);
#ifdef ARDUINO
        metrics = esp8266_Esp8266_Metrics_init_zero;
        stream = pb_ostream_from_buffer(buffer, buffer_size);
#else
        command = esp8266_Esp8266_Command_init_zero;
        stream = pb_ostream_from_buffer(buffer, buffer_size);
#endif
    }

    void writeBufferToClient() {
#ifdef ARDUINO
        client.write(buffer, stream.bytes_written);
#else
        write(client_sock, buffer, buffer_size);
#endif
    }

    void encode() {
#ifdef ARDUINO
        pb_encode(&stream, esp8266_Esp8266_Metrics_fields, &metrics);
#else
        pb_encode(&stream, esp8266_Esp8266_Command_fields, &command);
#endif
    }

    void encodeAndWrite() { encode(); writeBufferToClient(); }
} output;
