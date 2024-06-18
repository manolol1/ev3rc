#include <Arduino.h>
#include "BluetoothSerial.h"
#include "WiFi.h"
#include <ESPAsyncWebServer.h>
#include <FS.h>
#include <SPIFFS.h>

#if !defined(CONFIG_BT_ENABLED) || !defined(CONFIG_BLUEDROID_ENABLED)
#error Bluetooth is not enabled! Please run `make menuconfig` to and enable it
#endif

BluetoothSerial bt;

const char *ssid = "EV3RC";
const char *password = "12345678";

AsyncWebServer server(80);

void setup() {
    Serial.begin(115200);

    delay(200);

    /* Setting up WiFi AP */
    Serial.println("Setting up WiFi AP...");
    WiFi.softAP(ssid, password);
    Serial.println("Done!");
    Serial.print("IP Address: ");
    Serial.println(WiFi.softAPIP());


    /* Initialize SPIFFS */
    Serial.println("Initializing SPIFFS...");
    if (!SPIFFS.begin()) {
    Serial.println("An Error has occurred while mounting SPIFFS");
    return;
    }


    /* Setting up Web Server */
    Serial.println("Setting up Web Server...");

    // routes
    server.on("/", HTTP_GET, [](AsyncWebServerRequest *request) {
        request->send(SPIFFS, "/index.html", "text/html");
    });

    server.on("/stop", HTTP_GET, [](AsyncWebServerRequest *request) {
        bt.write(0);
        request->send(200);
    });

    server.on("/forward", HTTP_GET, [](AsyncWebServerRequest *request) {
        bt.write(1);
        request->send(200);
    });

    server.on("/backward", HTTP_GET, [](AsyncWebServerRequest *request) {
        bt.write(2);
        request->send(200);
    });

    server.on("/left", HTTP_GET, [](AsyncWebServerRequest *request) {
        bt.write(3);
        request->send(200);
    });

    server.on("/right", HTTP_GET, [](AsyncWebServerRequest *request) {
        bt.write(4);
        request->send(200);
    });

    server.on("/forward-left", HTTP_GET, [](AsyncWebServerRequest *request) {
        bt.write(5);
        request->send(200);
    });

    server.on("/forward-right", HTTP_GET, [](AsyncWebServerRequest *request) {
        bt.write(6);
        request->send(200);
    });

    server.on("/backward-left", HTTP_GET, [](AsyncWebServerRequest *request) {
        bt.write(7);
        request->send(200);
    });

    server.on("/backward-right", HTTP_GET, [](AsyncWebServerRequest *request) {
        bt.write(8);
        request->send(200);
    });

    server.begin(); // start server

    /* Setting up Bluetooth */
    Serial.println("Setting up Bluetooth...");
    bt.begin("ESP32_rc");
    Serial.println("Done! Waiting for connection...");

    // Wait for connection
    while (!bt.hasClient()) {
        Serial.print(".");
        delay(100);
    }

    Serial.println("\nBluetooth Connected!");
}

void loop() {
    
}
