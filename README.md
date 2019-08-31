# Car-Rpc
The objective of this project is to effectively take control of a toy car (RC), with a platform API and controllers, and give it infinite range. 

## Architecture
There are 3 main components to this project - the `esp8266`, the `api`, and the `controller`. Here, the `esp8266` is done in c++, the
`api` is done in java, and the controller is done in `c++` and/or `java`. Here, the `esp8266` is the master.

![Alt text](https://raw.githubusercontent.com/adiprerepa/Car-Rpc/master/Untitled%20Diagram.png)

## Libraries
This project uses `protobuf`, `gRPC`, `mysql-connector-jdbc` for the API, `ESP8266WiFi` and `nanopb` for the esp8266, and `gRPC for the controller`.

travis test branch

## Authors
 - Aditya Prerepa
 - Nikhil Dhomse
 
## Updated Last
8/5/19
