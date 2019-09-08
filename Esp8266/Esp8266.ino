   qq#include "pb_common.h"
#include "pb.h"
#include "pb_encode.h"
#include "pb_decode.h"
#include "Esp8266_Interface.pb.h"

#include <ESP8266WiFi.h>

const char* ssid     = "SSID";
const char* pass     = "PASS";
const uint16_t port  = 42069;

WiFiServer server(port);
WiFiClient client;

struct InputData {
  static const unsigned buffer_length = esp8266_Esp8266_Command_size;
  unsigned char buffer[buffer_length];
  pb_istream_t input_stream;
  esp8266_Esp8266_Command command;

  InputData() { refresh(); };

  void refresh() {
    input_stream = pb_istream_from_buffer(buffer, buffer_length);
    command = esp8266_Esp8266_Command_init_zero;
  }

  void readAndDecode() {
    refresh();
    client.read(buffer, client.available());
    pb_decode(&input_stream, esp8266_Esp8266_Command_fields, &command);
  }
} input;

struct OutputData {
  static const unsigned buffer_length = esp8266_Esp8266_Metrics_size;
  unsigned char buffer[buffer_length];
  pb_ostream_t output_stream;
  esp8266_Esp8266_Metrics metrics;

  OutputData() { refresh(); }
  void refresh() {
    output_stream = pb_ostream_from_buffer(buffer, buffer_length);
  }

  void encodeAndWrite() {
    refresh();
    pb_encode(&output_stream, esp8266_Esp8266_Metrics_fields, &metrics);
    client.write(buffer, output_stream.bytes_written);
  }
} output;

void setupWiFi();
void printConnection(const char * name, IPAddress address, int port);

void handleData();

// setup WIFI and sensor
void setup() {
  Serial.begin(9600);
  setupWiFi();
}


void loop() {
  client = server.available();
  if (client) {
    printConnection("controller", client.remoteIP(), client.remotePort());
    client.setNoDelay(true);
    while (client.connected()) {
      if (client.available()) {
      input.readAndDecode();
      handleData();
      output.encodeAndWrite();
      }
    }
    Serial.println("Disconnected");
  }
}

void setupWiFi() {
  Serial.print("Attempting to connect to WiFi SSID: ");
  Serial.print(ssid);

  WiFi.begin(ssid, pass);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println();
  server.begin();
  printConnection("WiFi", WiFi.localIP(), port);
}

void printConnection(const char * name, IPAddress address, int port) {
  Serial.print("Connected to ");
  Serial.print(name);
  Serial.print(". Address is ");
  Serial.print(address);
  Serial.print(":");
  Serial.println(port);
}

void handleData() {
  Serial.print("got: ");
  switch (input.command.direction) {
    case esp8266_Esp8266_Command_Esp8266_Direction_MOTOR_FORWARD: Serial.println("MOTOR FORWARD"); break;
    case esp8266_Esp8266_Command_Esp8266_Direction_MOTOR_BACK: Serial.println("MOTOR BACK"); break;
    case esp8266_Esp8266_Command_Esp8266_Direction_WHEELS_LEFT: Serial.println("WHEELS LEFT"); break;
    case esp8266_Esp8266_Command_Esp8266_Direction_WHEELS_RIGHT: Serial.println("WHEELS RIGHT"); break;
    case esp8266_Esp8266_Command_Esp8266_Direction_KEEP_ALIVE: Serial.println("KEEP ALIVE"); break;
  }
  output.metrics.hcsr04_calibrate = 1.0;
  output.metrics.returnCode = esp8266_Esp8266_Metrics_Esp8266_Return_Status_Codes_OPERATION_OK;
}
