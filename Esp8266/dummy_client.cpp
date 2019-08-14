#ifndef ARDUINO

#include <arpa/inet.h>
#include <netinet/in.h>
#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>
#include "pb.h"
#include "pb_encode.h"
#include "pb_decode.h"
#include "Esp8266_Interface.pb.h"

#define PORT 8080

int client_sock;

#include "SocketPB_IO.h"

int main(int argc, char const *argv[])
{
    struct sockaddr_in serv_addr;
    if ((client_sock = socket(AF_INET, SOCK_STREAM, 0)) < 0)
    {
        printf("\n Socket creation error \n");
        return -1;
    }

    serv_addr.sin_family = AF_INET;
    serv_addr.sin_port = htons(42969);

    // Convert IPv4 and IPv6 addresses from text to binary form
    if(inet_pton(AF_INET, "192.168.1.48", &serv_addr.sin_addr) <= 0) {
        printf("\nInvalid address/ Address not supported \n");
        return -1;
    }

    if (connect(client_sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0) {
        printf("\nConnection Failed \n");
        return -1;
    }

    printf("Connected\n");

    input.refresh();
    output.refresh();

    output.command.direction = esp8266_Esp8266_Command_Esp8266_Direction_KEEP_ALIVE;

    output.encodeAndWrite();
    input.readAndDecode();

    printf("hcsr04 calib: %f\n", input.metrics.hcsr04_calibrate);
    printf("returnCode %i\n", input.metrics.returnCode);

    return 0;
}

#endif // ARDUINO
