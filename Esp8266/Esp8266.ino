#include "pb_common.h"
#include "pb.h"
#include "pb_encode.h"
#include "pb_decode.h"
#include "Esp8266_Interface.pb.h"

#include <ESP8266WiFi.h>
#include <ESP8266WebServer.h>
char* externalSsid;
char* externalPass;
const uint16_t port  = 42069;
const char* genWifiSsid = "Esp8266Net";
const char* genWifiPass = "esp8266";
const char htmlPage[]PROGMEM=R"=====(
<!DOCTYPE html>
<html>
<body>
<h3>Enter Wifi Ssid and Pass</h3>
<FORM METHOD="POST"action="/sent">
<input type="text" name="SSID" value = "ssid">
<input type="text" name="PASS" value = "pass">
<input type="submit" value="Send Creds">
</form>
</body>
</html>
)=====";
WiFiServer server(port);
WiFiClient client;
IpAddress local_ip(192,168,1,1);
IpAddress gateway(192, 168, 1, 1);
IpAddress subnet(255, 255, 255, 0);
Esp8266WebServer webServer(80);

struct InputData {
	static const unsigned buffer_length = Esp8266_Full_Request_size;
	unsigned char buffer[buffer_length];
	pb_istream_t input_stream;
	Esp8266_Full_Request command;
	
	InputData() { refresh(); };
	
	void refresh() {
		input_stream = pb_istream_from_buffer(buffer, buffer_length);
		command = Esp8266_Full_Request_init_zero;
	}
	
	void readAndDecode() {
		refresh();
		client.read(buffer, client.available());
		pb_decode(&input_stream, Esp8266_Full_Request_fields, &command);
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
void reqCreds();
void printConnection(const char * name, IPAddress address, int port);
void onSend();
void onConnect();
void onNotFound();
void handleData();

// setup WIFI and sensor
void setup() {
	Serial.begin(9600);
  reqCreds;
  // wait for credentials
  while (externalPass == null) {
    Serial.print(".");
  }
  Serial.print("\n");
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
	Serial.print(externalSsid);
	
	WiFi.begin(externalSsid, externalPass);
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
	// things to do here
	// - handle request data by sending voltage
	// - get hcsr04 distances
	// - write to outputdata
	// front rotation rules - -1 is left, 1 is right
	// backward speed -1
	// forward speed 1	
	Serial.print("got frontrotation: ");
	Serial.print(input.command.command.frontRotation);
	Serial.print("\ngot speed: ");
	Serial.print(input.command.command.speed);
	output.metrics.HCSR04_front_distance = 1.0;
	output.metrics.HCSR04_left_distance = 1.0;
	output.metrics.HCSR04_right_distance = 1.0;
}

void reqCreds() {
  WiFi.softAP(genWifiPass, genWifiSsid);
  WiFi.softAPConfig(local_ip, gateway, subnet);
  delay(100);
  webServer.on("/", onConnect);
  webServer.on("/sent", onSend);
  webServer.onNotFound(onNotFound);
  webServer.begin();
  Serial.print("HTTP Server started on port 80\n");
  webServer.handleClient();
} 

void onConnect() {
  String page = htmlPage;
  
}

void onSend() {
  String page = htmlPage;
  externalSsid = server.arg("SSID");
  externalPass = server.arg("PASS");
  Serial.print("Recieved wifi ssid:");
  Serial.print(externalSsid);
  Serial.print("\nRecieved wifi pass:");
  Serial.print(externalPass);
  Serial.print("\nWill try to connect...");
}
