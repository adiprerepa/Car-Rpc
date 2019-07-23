package main

import (
	EspEvent "./proto"
	"flag"
	"fmt"
	"github.com/golang/protobuf/proto"
	"log"
	"net"
	"os"
)

var (
	addr, network          string
)

func main() {
	network = "tcp"
	flag.StringVar(&addr, "e", ":10100", "service endpoint")
	flag.Parse()

	ln, err := net.Listen(network, addr)
	if err != nil {
		log.Println(err)
		os.Exit(1)
	}

	defer ln.Close()

	for {
		conn, err := ln.Accept()
		if err != nil {
			log.Println(err)
			_ = conn.Close()
			continue
		}
		log.Println("Connected to ", conn.RemoteAddr())
		go handleConnection(conn)
	}
}

func handleConnection(conn net.Conn) {
	defer func() {
		log.Println("INFO: closing connection")
		if err := conn.Close(); err != nil {
			log.Println("error closing connection:", err)
		}
	}()

	buf := make([]byte, 1024)

	n, err := conn.Read(buf)
	if err != nil {
		log.Println(err)
		return
	}
	if n <= 0 {
		log.Println("no data received")
		return
	}

	var e EspEvent.Esp8266Event
	if err := proto.Unmarshal(buf[:n], &e); err != nil {
		log.Println("failed to unmarshal:", err)
		return
	}

	fmt.Printf("{THING RECIEVED : %d}\n",
		e.GetStat(),
	)
}
