# Car-Rpc
The objective of this project is to effectively take control of a toy car (RC), with a platform API and controllers, and give it infinite range. 

## Travis CI Build Status
[![Build Status](https://travis-ci.org/adiprerepa/Car-Rpc.svg?branch=master)](https://travis-ci.org/adiprerepa/Car-Rpc)

## Purpose
The purpose of this project is to give any hobbyist or person who loves to build things a platform to build on to make autonomous RC cars. It is to give satisfaction to any designer, programmer, or builder that they can also make cool stuff.

## How it works
If you have an rc car, a phone, and a computer, this project will work. Essentially, you can control the rc car from your phone's accelerometer, and the control is instantaneous. So, tilt left will turn the car left, right will turn the car right, forward will move the car forward, and backward will more it backward. Some more options would be to implement different methods of control, such as a __smart glove__ and all that jazz. Setup is relatively easy, payoff is amazing.

# Journey
Making this project was one hell of a journey. From conception to building to finishing, I learned more than I possibly could have. Some of the things I am proud of about this program are the way I built it and how reliable (I think) the server is. I thought about the design for a long time, and it paid off in the end. I started during my internship at life360, where I was learning various valuable design techniques, and it all helped. The code on the server - from controller endpoints to the database code architecture - was carefully thought out and designed. The server is also extremely scalable - and you can add your own __features__ to the Server. It is multithreaded and is able to handle multiple controller connections at once. Everything about the server is a product of my knowledge of Java and gRPC.

## Design Decisions I am Proud Of - Server
 - A good database design decision was the `BaseDatabase` class, which was an abstract class which was generified for the Database Identifier (for pulling from mySQL), and the Database Entity Itself - which was returned on the `retrieve()` abstract function. You passed in the Entity Type (which inherited from a `BaseDatabaseEntity` class) to the generic. To use the BaseDatabase class you would need to directly inherit from it in your own database class, like `KnownCarDatabase`, but you would define a `KnownCarEntity` and `KnownCarIdentifier` to give the Generic Constructor. Those custom Entities and Identifiers would inherit from `BaseDatabaseEntity` and `BaseDatabaseIdentifier`. All in all, the way the java connector was designed was a good idea.
 - The layers of abstraction implemented in the application was crucial to reliable, fast development. The package structure : a good thing I realized about this project was that in a project of this scale, you would need to split everything up appropriately. So, the package structure and RIB (Router Interactor Builder) pattern that was used was extremely useful. For example, the class `ControlInteractor` was used only for controller-related communication, and that would be defined in the gRPC service definition implementation. The `ControlInteractor` contained an `Esp8266Interactor`, which had everything to do with the low-level socket communication (using protobuf), The `ControlInteractor` contained the database function calls too, which were organized well.
 - The Algorithm used to translate accelerometer positions to car velocity and wheel rotation was pretty useful. You Can find it in `CommandFactory#buildCommand`.
 - Code Patterns used : 
    - dependency injection
    - object pools
    - Factories and Builders
    - Base Generics and Interfaces

## Components Necessary
This project requires : 
 - Any electrically controlled car that you can expose the motherboard with, and drives with servos and motors.
 - A computer to host the server that will be doing most of the work - that has java 8 or above and maven installed.
 - An android phone to install the `Car-Rpc` app APK on (the controller), that has an accelerometer in-built.
 - An esp8266 - That was the microcontroller we used. Any wifi-compatible arduino-based microcontroller should work. (Given it supports the ESP IOT libraries).
 - Wires, a breadboard (optional), and 3 HCSR04s. (More on that later).
 - *A Network Connection* - Does not require internet access because the server is being run locally - so any old router or your home router should work.

## Make your own controller
One of the points of this project was to have other people build on it. Use the esp8266 and Server Code to make your own controller! The gRPC service definitions and declarations are readily available - come up with creative ways to control the Car - such as an app with various inputs or some other type of input - like a sensor array on your hand to map hand movements to car movements - you build it!

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
#### MySQL Database Setup
 - You can easily install mysql with this link : https://support.rackspace.com/how-to/installing-mysql-server-on-ubuntu/
##### After Installation
 - run `sudo -i` on your computer - and enter the password.
 - run `mysql -u root -p` and do not put anything in for the password - you should automatically log in.
 - now run  : 
 ```mysql
GRANT ALL PRIVILEGES ON *.* TO 'username'@'localhost' IDENTIFIED BY 'password';
```
where you insert username and password at your discretion. Remember this username and password.
 - to create the car_rpc database : 
 ```mysql
CREATE DATABASE car_rpc;
```
 - Now you are done!
##### Repository Database Credentials setup
In order to stop from committing usernames and passwords to repository sources, make the file `database_credentials.json` in
the directory `/Car-Rpc/ServicePlatform/src/main/resources/`, where you put the username, url, and password as follows(assuming that you run the database locally) : 
```json
 {
     "databaseUrl" : "jdbc:mysql://localhost/car_rpc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
     "databaseUsername" : "USERNAME!!!!", 
     "databasePassword" : "PASSWORD"
 }
```
Where you can insert the username and password from earlier. Thats it for the database setup - the program will automatically make tables for you.
#### Prerequisites - Server Setup 
 - An Internet Connection (Setup - git&maven)
 - Maven Installed
 - Java 8 or Higher Installed

### Esp8266 Setup
#### Prerequisites
 - Arduino Compiler with Esp8266 - https://dzone.com/articles/programming-the-esp8266-with-the-arduino-ide-in-3
 - Nanopb protobuf plugin : https://github.com/nanopb/nanopb - clone into home directory.
 - An RC car
#### RC Car setup
In order to effectively set up the rc car-rpc, you need a remote control car. You should expose the motherboard of this car, and get a multimeter. Switch it into completion mode and play around with the pins - see which pins move which motors. You are basically mocking what the RC reciever would do. Once that happens - note down which combinations of pins work for which commands - such as left, right, forward, and backward. This is useful later on.
 - You should solder the wires onto the car's motherboard.
### App Setup
Install the Car-Rpc app, and on the corresponding screens input the server IP and port, as well as the esp8266 IP and port (defined in the .ino file in `Esp8266`). The app should connect to the esp8266 and server successfully, and you should see a list of previously controlled cars by you. From there, tilt the phone left for tha car moving left, right for the car moving right, and so on.
## Architecture
There are 3 main components to this project - the `esp8266`, the `api`, and the `controller`. Here, the `esp8266` is done in c++, the
`api` is done in java, and the controller is done in `c++` and/or `java`. Here, the `esp8266` is the master.

![Alt text](https://raw.githubusercontent.com/adiprerepa/Car-Rpc/master/Untitled%20Diagram.png)

## Libraries
This project uses `protobuf`, `gRPC`, `mysql-connector-jdbc` for the API, `ESP8266WiFi` and `nanopb` for the esp8266, and `gRPC for the controller`.
 
# Authors
 - Aditya Prerepa
   - Underlying architecture and idea.
   - Server Development and Code in Java.
   - gRPC service definitions and implementations on controller side and esp8266 side.
   - MySQL database schema and `jdbc` architecture implementation.
   - Helped with Test App Client.
 - Akshay Trivedi
   - Esp8266 C++ Code
   - Help with Esp8266 Protobuf definition.

#### Last Updated 
9/15/2019
