# Car-Rpc
The objective of this project is to effectively take control of a toy car (RC), with a platform API and controllers, and give it infinite range. 

## Travis CI Build Status
[![Build Status](https://travis-ci.org/adiprerepa/Car-Rpc.svg?branch=master)](https://travis-ci.org/adiprerepa/Car-Rpc)

## Purpose
The purpose of this project is to give any hobbyist or person who loves to build things a platform to build on to make autonomous RC cars. It is to give satisfaction to any designer, programmer, or builder that they can also make cool stuff.

## Components Necessary
This project requires : 
 - Any electrically controlled car that you can expose the motherboard with, and drives with servos and motors.
 - A computer to host the server that will be doing most of the work - that has java 8 or above and maven installed.
 - An android phone to install the `Car-Rpc` app APK on (the controller), that has an accelerometer in-built.
 - An esp8266 - That was the microcontroller we used. Any wifi-compatible arduino-based microcontroller should work. (Given it supports the ESP IOT libraries).
 - Wires, a breadboard (optional), and 3 HCSR04s. (More on that later).
 - *A Network Connection* - Does not require internet access because the server is being run locally - so any old router or your home router should work.

## Setup
The repository has 3 main components (excluding test directories).
 - `CarApp` - The android App Directory.
 - `Esp8266` - the embedded system code for the microcontroller.
 - `ServicePlatform` - The main server platform.
 - One of the ideologies behind this project were to have the App and Esp8266 do as little work as possible, and have the server do all the heavy lifting/processing. Any crappy linux-based computer (what we tested it on) will work perfect. A Raspberry Pi is perfect for this job, but anything will do.

### Server Setup
 1) Clone Repositiory with command `git clone git@github.com:adiprerepa/Car-Rpc.git` - Needs to be in home directory.
 2) Do `cd ServerPlatform`. List out the contents with `ls`, you should see a `pom.xml`, `runPlatform.sh`, and a `src` directory.
 3) Run `./runServicePlatform.sh`, wait for the terminal output of the java program, you should see something like `Server Started on port <port>`.
#### Prerequisites - Server Setup 
 - An Internet Connection (Setup - git&maven)
 - Maven Installed
 - Java 8 or Higher Installed

### Esp8266 Setup

## Architecture
There are 3 main components to this project - the `esp8266`, the `api`, and the `controller`. Here, the `esp8266` is done in c++, the
`api` is done in java, and the controller is done in `c++` and/or `java`. Here, the `esp8266` is the master.

![Alt text](https://raw.githubusercontent.com/adiprerepa/Car-Rpc/master/Untitled%20Diagram.png)

## Libraries
This project uses `protobuf`, `gRPC`, `mysql-connector-jdbc` for the API, `ESP8266WiFi` and `nanopb` for the esp8266, and `gRPC for the controller`.

## Authors
 - Aditya Prerepa
 - Akshay Trivedi

## Updated Last
9/1/19 - not finished
