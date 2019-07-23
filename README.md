# Car-Rpc
Control Your Toy (RC) car with a chip and a go server. Lets dive into go.

# Goal
The goal of this project is to make a self-driving toy car that can be ovveridden by a server when needed. The toy car is any
RC controlled car, by a remote with a frequency above 42 Hz. These toy cars can be found almost anywhere. The server and the esp8266 will communicate via protocol buffers, or gRPC.
This project can be broken up into 3 major steps. Here they are.

## Step 1 - Manual Controls From the server
The server in go will recieve manual commands from stdin (or possibly some fancy interface), being up/down/left/right/speed, and 
the service will send (over wifi) to the esp8266 these commands. The command will probably be an enum, sent with some other info. The esp8266 will then send via an IR led a series of flashes to convey to the RC reciever on the car. This is the end of step one.

## Step 2 - A car that tries not to bump into stuff
Here, the server does not have to be used, unless stats are be viewed from an app or web app. Here, some ultrasonic sensors are attached to the I/O pins of the ESP8266, and a combination of these sensors and a collision algorithm will be used to try to steer the car in the right direction. Although direction here is obsolete and has no meaning, it would try not to bump into obstacles around it. The direction and speed that is being calculated for the esp on the chip itself can be overriden by the server when needed (2 threads).

## Step 3 - A car that has an objective to get to another point through gps coordinates
This is the big mama of steps. Here, we combine step one and two by having a server and sensors on the car, but here we have a gps objective. We can try to use graph theory can a combination of inputs from the sensors to try to effectively go from point A to point B without having much server input (unless necesary). It might be necessary to attach a gps chip to the esp8266, and it needs to be very accurate as well. This is where the possibilties get interesting, as there are applications for places where humans cannot go, for a cheap robot to go safely. The IR signal here would have to be pretty strong, and the sensor input would have to be very accurate. The sensor would be an HCSR04(ultrasonic).

### Side Note : How the IR Connection would work
TV remotes and 

# Inital 7/22/19
 - Got a go server working after the java one didnt, recieving a number from the esp on grpc.
