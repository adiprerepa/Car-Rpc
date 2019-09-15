#include "pb_common.h"
#include "pb.h"
#include "pb_encode.h"
#include "pb_decode.h"
#include "Esp8266_Interface.pb.h"

#include <ESP8266WiFi.h>

const char* ssid     = "NETGEAR11";
const char* pass     = "huskyjet126";
const uint16_t port  = 42069;

// remove 1 element from the following lists to make GPIO room for the motors

const int trigPins[3] = { 14, 13, 5 };
const int echoPins[3] = { 12, 15, 4 };

int forwardPin = ;
int backwardPin = ;
int leftPin = ;
int rightPin = ;

// remove 1 element from the following list to make GPIO room for the motors

int distances[3] = { 0, 0, 0 };

WiFiServer server(port);
WiFiClient client;

struct InputData {
  static const unsigned buffer_length = Esp8266_Full_Request_size;
  unsigned char buffer[buffer_length];
  pb_istream_t input_stream;
  Esp8266_Full_Request full_request;

  InputData() { refresh(); };

  void refresh() {
    input_stream = pb_istream_from_buffer(buffer, buffer_length);
    full_request = Esp8266_Full_Request_init_zero;
  }

  void readAndDecode() {
    refresh();
    client.read(buffer, client.available());
    pb_decode(&input_stream, Esp8266_Full_Request_fields, &full_request);
  }
} input;

struct OutputData {
  static const unsigned buffer_length = Esp8266_Metrics_size;
  unsigned char buffer[buffer_length];
  pb_ostream_t output_stream;
  Esp8266_Metrics metrics;

  OutputData() { refresh(); }
  void refresh() {
    output_stream = pb_ostream_from_buffer(buffer, buffer_length);
  }

  void encodeAndWrite() {
    refresh();
    pb_encode(&output_stream, Esp8266_Metrics_fields, &metrics);
    client.write(buffer, output_stream.bytes_written);
  }
} output;

void setupWiFi();
void setupPins();

void printConnection(const char * name, IPAddress address, int port);
void getDistance();

void handleData();

// setup WIFI and sensor
void setup() {
  Serial.begin(9600);
  setupWiFi();
  setupPins();
}


void loop() {
  //client = server.available();
  //if (client) {
  //  printConnection("controller", client.remoteIP(), client.remotePort());
  //  client.setNoDelay(true);
  //  while (client.connected()) {
  //    if (client.available()) {
  //    input.readAndDecode();
  //    handleData();
  //    output.encodeAndWrite();
  //    }
  //  }
  //  Serial.println("Disconnected");
  //}
  while (true) {
  Serial.print('b');
    getDistance();
    delay(100);
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

void setupPins() {
  pinMode(trigPins[0], OUTPUT);
  pinMode(echoPins[0], INPUT);
  pinMode(trigPins[1], OUTPUT);
  pinMode(echoPins[1], INPUT);

  pinMode(trigPins[2], OUTPUT); // remove this hcsr04 to make space for motors
  pinMode(echoPins[2], INPUT);

  pinMode(forwardPin, OUTPUT);
  pinMode(backwardPin, OUTPUT);
  pinMode(leftPin, OUTPUT);
  pinMode(rightPin, OUTPUT);
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
  Serial.print("Front Rotation: ");
  Serial.println(input.full_request.frontRotation);
  Serial.print("Speed: ");
  Serial.println(input.full_request.speed);

  // handle motors
  if (full_request.command.front_rotation == 0) {
      digitalWrite(leftPin, LOW);
      digitalWrite(rightPin, LOW);
  } else  if (full_request.command.front_rotation < 0) {
      digitalWrite(leftPin, HIGH);
      digitalWrite(rightPin, LOW);
  } else if (full_request.command.front_rotation > 0) {
      digitalWrite(leftPin, LOW);
      digitalWrite(rightPin, HIGH);
  }

  if (full_request.command.speed == 0) {
      digitalWrite(backwardPin, LOW);
      digitalWrite(forwardPin, LOW);
  } else  if (full_request.command.speed < 0) {
      digitalWrite(backwardPin, HIGH);
      digitalWrite(forwardPin, LOW);
  } else if (full_request.command.speed > 0) {
      digitalWrite(backwardPin, LOW);
      digitalWrite(forwardPin, HIGH);
  }


  getDistance();

  // remove 1 of the following lines to use 2 hcsrs to make space for the motors (you will also have to change the proto)
  output.metrics.HCSR04_front_distance = echoPins[0];
  output.metrics.HCSR04_left_distance = echoPins[1];
  output.metrics.HCSR04_right_distance = echoPins[2];
}

long duration;
int distance;


void getDistance() {
  // remove one iteration of this loop to remove an hcsr04 to make space for motors
  for (int i = 0; i < 3; i++) {
    digitalWrite(trigPins[i], LOW);
    delayMicroseconds(2);

    // Sets the trigPin on HIGH state for 10 micro seconds
    digitalWrite(trigPins[i], HIGH);
    delayMicroseconds(10);
    digitalWrite(trigPins[i], LOW);

    // Reads the echoPin, returns the sound wave travel time in microseconds
    distances[i] = pulseIn(echoPins[i], HIGH);
  }

  Serial.print(distances[0]);
  Serial.print(" ");
  Serial.print(distances[1]);
  // remove the following lines to make space for motors
  Serial.print(" ");
  Serial.println(distances[2]);
}
