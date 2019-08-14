pp/.#include <ESP8266WiFi.h>
#include "pb.h"
#include "pb_encode.h"
#include "pb_decode.h"
#include "Esp8266_Interface.pb.h"

const char* ssid     = "NETGEAR11";
const char* password = "huskyjet126";
const uint16_t port  = 42969;

WiFiServer server(port);
WiFiClient client;

#include "SocketPB_IO.h"

void setupWiFiServer();
void printConnection(const char * name, IPAddress ip, uint16_t port);
void handleData();

void setup() {
  Serial.begin(9600);
  setupWiFiServer();
}

void loop() {
  client = server.available();
  if (client) {
    printConnection("Controller", client.remoteIP(), client.remotePort());
    client.setNoDelay(true);
    while (client.connected()) {
      if (client.available()) {
        input.refresh();
        output.refresh();

        input.readAndDecode();
        handleData();
        output.encodeAndWrite();
      }
    }
    Serial.println("Disconnected");
  }
}

void setupWiFiServer() {
  Serial.print("Connecting to WiFi with SSID ");
  Serial.println(ssid);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(1000);
  }
  Serial.println();
  printConnection("WiFi", WiFi.localIP(), port);
  server.begin();
}

void printConnection(const char * name, IPAddress ip, uint16_t port) {
  Serial.print("Connected to ");
  Serial.print(name);
  Serial.print(": IP=");
  Serial.print(ip);
  Serial.print(", port=");
  Serial.println(port);
}

void handleData() {
  switch (input.command.direction) {
    case esp8266_Esp8266_Command_Esp8266_Direction_MOTOR_FORWARD: Serial.println("got: MOTOR FORWARD"); break;
    case esp8266_Esp8266_Command_Esp8266_Direction_MOTOR_BACK:    Serial.println("got: MOTOR BACK");    break;
    case esp8266_Esp8266_Command_Esp8266_Direction_WHEELS_LEFT:   Serial.println("got: WHEELS LEFT");   break;
    case esp8266_Esp8266_Command_Esp8266_Direction_WHEELS_RIGHT:  Serial.println("got: WHEELS RIGHT");  break;
    case esp8266_Esp8266_Command_Esp8266_Direction_KEEP_ALIVE:    Serial.println("got: KEEP ALIVE");    break;
  }
  output.metrics.hcsr04_calibrate = 1;
  output.metrics.returnCode = esp8266_Esp8266_Metrics_Esp8266_Return_Status_Codes_OPERATION_OK;
}
