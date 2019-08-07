package com.prerepa.car_rpc.controller;

public enum ControlReturnType {
    MALFORMED_COMAND,
    UNABLE_TO_CONNECT_TO_ESP8266,
    INTERNAL_SERVER_ERROR,
    INVALID_KEY,
    OK
}
