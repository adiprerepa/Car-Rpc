#ifndef ARDUINO

#include "pb_encode.h"
#include "pb_decode.h"
#include "Esp8266_Interface.pb.h"
#include <stdio.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <string.h>

const unsigned output_buffer_length = esp8266_Esp8266_Command_size;
unsigned char output_buffer[output_buffer_length];
pb_ostream_t output_stream;
esp8266_Esp8266_Command command;

const unsigned input_buffer_length = esp8266_Esp8266_Metrics_size;
unsigned char input_buffer[input_buffer_length];
pb_istream_t input_stream;
esp8266_Esp8266_Metrics metrics;

int main() {
    command = esp8266_Esp8266_Command_init_zero;
    output_stream = pb_ostream_from_buffer(output_buffer, output_buffer_length);
    metrics = esp8266_Esp8266_Metrics_init_zero;
    input_stream = pb_istream_from_buffer(input_buffer, input_buffer_length);


    int sock = 0, valread;
    struct sockaddr_in serv_addr;
    if ((sock = socket(AF_INET, SOCK_STREAM, 0)) < 0)
    {
        printf("\n Socket creation error \n");
        return -1;
    }

    serv_addr.sin_family = AF_INET;
    serv_addr.sin_port = htons(42069);

    // Convert IPv4 and IPv6 addresses from text to binary form
    if(inet_pton(AF_INET, "192.168.1.48", &serv_addr.sin_addr)<=0) {
        printf("\nInvalid address/ Address not supported \n");
        return -1;
    }

    if (connect(sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0) {
        printf("\nConnection Failed \n");
        return -1;
    }

    command.direction = esp8266_Esp8266_Command_Esp8266_Direction_MOTOR_FORWARD;

    pb_encode(&output_stream, esp8266_Esp8266_Command_fields, &command);
    write(sock, output_buffer, output_stream.bytes_written);
    read(sock, input_buffer, input_buffer_length);
    pb_decode(&input_stream,  esp8266_Esp8266_Metrics_fields, &metrics);

    printf("%f\n", metrics.hcsr04_calibrate);
    printf("%i\n", metrics.returnCode);

    close(sock);
}

#endif
