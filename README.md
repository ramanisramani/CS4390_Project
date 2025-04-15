# Network Math Server Project

## Overview
This project implements a client-server network application that provides basic math calculation services. The server can handle multiple client connections simultaneously, process math calculation requests, and maintain logs of client activities. Clients can connect to the server, send math calculation requests, and disconnect when finished.

---

## Features
- Multi-client support using threads (via thread pool)
- Manual user input of expressions (no input limit)
- Graceful disconnect by typing `exit`
- Local client history of expressions and results
- Server-side logging of:
  - Client connections/disconnections
  - Expression evaluations
  - Errors and session duration
- Supported operations: `+`, `-`, `*`, `/`  

---

## Prerequisites
- Java Development Kit (JDK) **11 or higher**
- Terminal access (macOS, Linux, or Windows PowerShell)
- Optional: VS Code or IntelliJ for editing 

---

## How to Run?
- Open a new terminal
  - Run this command to compile everything: make
  - Run this to start the server: make run-server
- Open a different terminal
  - Run this command to start the client: make run-client
  - Enter any string (i.e a name)
- Check the newly generated log txt file to see the client-server connection process details
- Run this command to undo: make clean


---

## Project Structure

```plaintext
CS4390_Project/
├── src/
│   ├── server/
│   │   ├── MathServer.java         # Main server implementation
│   │   ├── ClientHandler.java      # Handles individual client connections
│   │   ├── MathCalculator.java     # Processes math operations
│   │   └── Logger.java             # Handles server-side logging
│   ├── client/
│   │   ├── MathClient.java         # Main client implementation
│   └── protocol/
│       ├── Message.java            # Message format definition
│       ├── RequestMessage.java     # Math calculation request
│       ├── ResponseMessage.java    # Math calculation response
│       └── ConnectionMessage.java  # Connection and disconnection messages
├── Makefile                        # For compilation and execution
└── README.md                       # This file

