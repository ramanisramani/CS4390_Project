# Network Math Server Project

## Overview
This project implements a client-server network application that provides basic math calculation services. The server can handle multiple client connections simultaneously, process math calculation requests, and maintain logs of client activities. Clients can connect to the server, send math calculation requests, and disconnect when finished.

---

## Features

- Centralized math server with multi-client support  
- Tracking of user connections and activities  
- Request queuing and processing in order of receipt  
- Basic math operations (addition, subtraction, multiplication, division)  
- Clean connection establishment and termination protocol  
- Comprehensive logging of all activities  

---

## Prerequisites

- Java Development Kit (JDK) 11 or higher  
- Network connectivity between server and client machines  

---

## How to Run?

- Open a new terminal
  - Run this command to compile everything: make
  - Run this to start the server: make run-server
- Open a different terminal
  - Run this command to start the client: make run-client
  - Enter any string (i.e a name)
- Check the new generated log txt file to see the client-server connection process details


---

## Project Structure

```plaintext
MathServer/
├── src/
│   ├── server/
│   │   ├── MathServer.java         # Main server implementation
│   │   ├── ClientHandler.java      # Handles individual client connections
│   │   ├── MathCalculator.java     # Processes math operations
│   │   └── Logger.java             # Handles server-side logging
│   ├── client/
│   │   ├── MathClient.java         # Main client implementation
│   │   └── RequestGenerator.java   # Generates random math requests
│   └── protocol/
│       ├── Message.java            # Message format definition
│       ├── RequestMessage.java     # Math calculation request
│       ├── ResponseMessage.java    # Math calculation response
│       └── ConnectionMessage.java  # Connection and disconnection messages
├── Makefile                        # For compilation and execution
└── README.md                       # This file

