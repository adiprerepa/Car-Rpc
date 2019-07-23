#include <temp.pb.h>

#include <pb_common.h>
#include <pb.h>
#include <pb_encode.h>
#include <pb_decode.h>

#include <ESP8266WiFi.h>

const char* ssid     = "<wifi-ssid>";
const char* password = "<wifi-password>";
const char* addr     = "<server-ip-addr>";
const uint16_t port  = 10101;

WiFiClient client;

// setup WIFI and sensor
void setup() {
  pinMode(LED_BUILTIN, OUTPUT);
  Serial.begin(115200);
  delay(10);

  Serial.println();
  Serial.print("Setting up WIFI for SSID ");
  Serial.println(ssid);

  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    Serial.println("WIFI connection failed, reconnecting...");
    delay(500);
  }

  Serial.println("");
  Serial.print("WiFi connected, ");
  Serial.print("IP address: ");
  Serial.println(WiFi.localIP());
}


void loop() {
  digitalWrite(LED_BUILTIN, LOW);
  Serial.print("connecting to ");
  Serial.println(addr);

  if (!client.connect(addr, port)) {
    Serial.println("connection failed");
    Serial.println("wait 5 sec to reconnect...");
    delay(5000);
    return;
  }
    
  pb_Event temp = pb_Event_init_zero;
  temp.stat = 1000
  
  sendTemp(temp);
  digitalWrite(LED_BUILTIN, HIGH);
  
  delay(5000);
}

void sendTemp(pb_Event e) {
  uint8_t buffer[128];
  pb_ostream_t stream = pb_ostream_from_buffer(buffer, sizeof(buffer));
  
  if (!pb_encode(&stream, pb_Event_fields, &e)){
    Serial.println("failed to encode temp proto");
    Serial.println(PB_GET_ERROR(&stream));
    return;
  }
  
  Serial.print("sending temp...");
  Serial.println(e.stat);
  client.write(buffer, stream.bytes_written);
}
