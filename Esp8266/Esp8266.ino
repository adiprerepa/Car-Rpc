#include "event.pb.h"

#include <pb_common.h>
#include <pb.h>
#include <pb_encode.h>
#include <pb_decode.h>

#include <ESP8266WiFi.h>

const char* ssid     = "onhub";
const char* password = "ekchotaghar";
const char* addr     = "10.0.1.181";
const uint16_t port  = 10100;

WiFiClient client;

void sendStat(pb_Event e);

// setup WIFI and sensor
void setup() {
  pinMode(LED_BUILTIN, OUTPUT);
  Serial.begin(115200);
  delay(10);

  Serial.println();
  Serial.print("Setting up WIFI for SSID ");
  Serial.println(ssid);

//  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    Serial.println("WIFI connection failed, reconnecting...");
    delay(500);
  }
  
  Serial.print("\nWiFi connected, ");
  Serial.print("IP address: ");
  Serial.println(WiFi.localIP());
}


void loop() {
  digitalWrite(LED_BUILTIN, LOW);
  Serial.print("connecting to ");
  Serial.println(addr);
  client.connect(addr, port);

  if (!client.connect(addr, port)) {
    Serial.println("connection failed");
    Serial.println("wait 5 sec to reconnect...");
    delay(5000);
    return;
  }
    
  pb_Event temp = pb_Event_init_zero;
  temp.stat = 1000;
  
  sendStat(temp);
  digitalWrite(LED_BUILTIN, HIGH);
  
  delay(5000);
}

void sendStat(pb_Event e) {
  uint8_t buff[128];
  pb_ostream_t stream = pb_ostream_from_buffer(buff, sizeof(buff));
  char* consoleStatusInfo = (char*)buff;
  if (!pb_encode(&stream, pb_Event_fields, &e)){
    Serial.println("failed to encode temp proto");
    Serial.println(PB_GET_ERROR(&stream));
    return;
  } else {
    Serial.println("Encoded Sucessfuly");
  }
//  Serial.printf("Sending info for...%d\nMessage String : %s\n", e.stat, consoleStatusInfo);
  Serial.print("Message Length: ");
  Serial.println(stream.bytes_written);
 
  Serial.print("Message: ");
 
  for(int i = 0; i<stream.bytes_written; i++){
    Serial.printf("%02X",buff[i]);
  }
  client.write(buff, stream.bytes_written);
}
