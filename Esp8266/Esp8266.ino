#include <ESP8266WiFi.h>
#include <WiFiClient.h>
#include <ESP8266WebServer.h>
#include "Esp8266_Interface.pb.h"
#include "pb_encode.h"
#include "pb_decode.h"

struct StatusLED {
  int red, green;
} mainStatusLED = { .red = 12, .green = 13 };

struct MotorCTL {
  int enable, ctl1, ctl2;
} motorA = { .enable = -1, .ctl1 = -1, .ctl2 = -1 }, 
  motorB = { .enable = -1, .ctl1 = -1, .ctl2 = -1 };

struct Hcsr04 {
  int trig, echo;
} front = { .trig = -1, .echo = -1 }, 
  left = { .trig = -1, .echo = -1 }, 
  right = { .trig = -1, .echo = -1 };

static const unsigned input_buffer_length = Esp8266_Full_Request_size;
unsigned char input_buffer[input_buffer_length];
pb_istream_t input_stream;
Esp8266_Full_Request command;

static const unsigned output_buffer_length = Esp8266_Metrics_size;
unsigned char output_buffer[output_buffer_length];
pb_ostream_t output_stream;
Esp8266_Metrics metrics;

String softAPSSID = "esp8266Network";
String wifiSSID;
String wifiPassword;
int wifiConnTime;

int webServerPort = 80;
int wifiServerPort = 42069;

ESP8266WebServer webServer(webServerPort);
WiFiServer wifiServer(wifiServerPort);
WiFiClient wifiClient;

IPAddress local_IP(192,168,4,22);
IPAddress gateway(192,168,4,9);
IPAddress subnet(255,255,255,0);

const char htmlPage[]PROGMEM=R"=====(
<!DOCTYPE html>
<html>
  <body>
    <h3>Enter Wifi SSID and Password</h3>
    <form method="POST" action="/sent">
      <table>
        <tr>
          <td>SSID:</td>
          <td><input type="text" name="SSID"></td>
        </tr>
        <tr>
          <td>Password:</td>
          <td><input type="text" name="PASS"></td>
        </tr>
        <tr>
          <td>Connection Timeout:</td>
          <td><input type="text" name="CONN_TIME" value="10"></td>
        </tr>
        <tr>
          <td><input type="submit" value="Connect"></td>
        </tr>
      </table>
    </form>
    <ul>
      <li>Type an SSID and Password to connect the esp8266 to a WiFi Network.</li>
      <li>If the esp8266 cannot connect to 'SSID' in 'Connection Timeout' seconds, it will recreate this website.</li>
      <li>Do not change 'Connection Timeout' unless you are on a slow network.</li>
      <li>The 'SSID' and 'Password' inputs are invalid if they cannot be used to connect to an existing WiFi network.</li>
      <li>The 'Connection Timeout' input is invalid if it is not an integer greater than zero.</li>
      <li>If any of the above fields are invalid, the esp8266 will recreate this website.</li>
    </ul>
  </body>
</html>
)=====";

void setupWebServer();
void setupStatusLED(StatusLED&);
void setupMotorCTL(MotorCTL&);
void setupHcsr04(Hcsr04&);

void setStatusLED(StatusLED&, unsigned char, unsigned char);
void controlMotor(MotorCTL&, int, int);
int getHcsr04Distance(Hcsr04&);

void setup(void) {
  Serial.begin(9600);
  setupWebServer();
  setupStatusLED(mainStatusLED);
}

void loop(){
  while (WiFi.status() != WL_CONNECTED) {
    setStatusLED(mainStatusLED, 200, 0);
    WiFi.disconnect();
    wifiSSID.remove(0, wifiSSID.length());
    wifiPassword.remove(0, wifiPassword.length());
    if ((!WiFi.softAPConfig(local_IP, gateway, subnet)) || (!WiFi.softAP(softAPSSID))) {
      continue;
    }
    webServer.begin();
    while ((!wifiSSID.length()) || (!wifiPassword.length())) {
      webServer.handleClient();
    }
    webServer.stop();
    WiFi.softAPdisconnect(true);
    WiFi.begin(wifiSSID, wifiPassword);
    int connectionCounter;
    setStatusLED(mainStatusLED, 200, 200);
    for (connectionCounter = 0; (connectionCounter < wifiConnTime) && (WiFi.status() != WL_CONNECTED); connectionCounter++) {
      setStatusLED(mainStatusLED, 200 * (connectionCounter % 2), 200 * (1 + connectionCounter % 2));
      delay(1000);
    }
  }
  setStatusLED(mainStatusLED, 0, 200);
  wifiServer.begin();
  wifiServer.setNoDelay(false);
  while (WiFi.status() == WL_CONNECTED) {
    wifiClient = wifiServer.available();
    if (wifiClient) {
      wifiClient.setNoDelay(true);
      while (wifiClient.connected()) {
        if (wifiClient.available()) {
          input_stream = pb_istream_from_buffer(input_buffer, input_buffer_length);
          output_stream = pb_ostream_from_buffer(output_buffer, output_buffer_length);          
          command = Esp8266_Full_Request_init_default;
          wifiClient.read(input_buffer, wifiClient.available());
          pb_decode(&input_stream, Esp8266_Full_Request_fields, &command);
          metrics.HCSR04_front_distance = 1.0;
          metrics.HCSR04_left_distance = 1.0;
          metrics.HCSR04_right_distance = 1.0;
          pb_encode(&output_stream, Esp8266_Metrics_fields, &metrics);
          wifiClient.write(output_buffer, output_stream.bytes_written);
        }
      }
    }
  }
  wifiServer.stop();
}

void setupWebServer() {
    webServer.on("/", [&]() {
      webServer.send(200, "text/html", htmlPage);
  });
  webServer.on("/sent", [&]() {
    wifiSSID = webServer.arg("SSID");
    wifiPassword = webServer.arg("PASS");
    wifiConnTime = webServer.arg("CONN_TIME").toInt();
    webServer.sendHeader("Location","/");
    webServer.send(303);
  });
  webServer.onNotFound([&]() {
    webServer.send(404, "text/plain", "404: Not found");
  });
}

void setupStatusLED(StatusLED& statusLED) {
  pinMode(statusLED.red, OUTPUT);
  pinMode(statusLED.green, OUTPUT);
}

void setupMotorCTL(MotorCTL& motorCTL) {
  pinMode(motorCTL.enable, OUTPUT);
  pinMode(motorCTL.ctl1, OUTPUT);
  pinMode(motorCTL.ctl2, OUTPUT);
}

void setupHcsr04(Hcsr04& hcsr04) {
  pinMode(hcsr04.trig, OUTPUT);
  pinMode(hcsr04.echo, OUTPUT);
}

void setStatusLED(StatusLED& statusLED, unsigned char redValue, unsigned char greenValue) {
  analogWrite(statusLED.red, redValue);
  analogWrite(statusLED.green, greenValue);
}
