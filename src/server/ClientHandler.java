package server;

import protocol.*;

import java.io.*;
import java.net.*;
import java.time.*;

/**
 * Handles communication between the server and a single connected client.
 * This class is run in its own thread so multiple clients can be handled concurrently.
 */
public class ClientHandler implements Runnable {
    private Socket socket;           // Socket connected to the client
    private Logger logger;           // Logger for writing server activity logs
    private BufferedReader in;       // To read data from the client
    private PrintWriter out;         // To send data to the client
    private String clientName;       // Name of the connected client
    private Instant startTime;       // Time when the client connected

    /**
     * Constructor to initialize the client handler with the client's socket and logger.
     * @param socket Socket connected to the client
     * @param logger Logger for recording actions
     */
    public ClientHandler(Socket socket, Logger logger) {
        this.socket = socket;
        this.logger = logger;
    }

    /**
     * Main execution method for the client handler thread.
     * Listens for incoming messages from the client and handles them.
     */
    public void run() {
        try {
            // Setup input and output streams for communication
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Main loop to read and handle messages from the client
            while (true) {
                String line = in.readLine();
                if (line == null) break; // Client disconnected unexpectedly

                // Handle connection request
                if (line.startsWith("CONNECT")) {
                    ConnectionMessage msg = ConnectionMessage.parse(line);
                    clientName = msg.getClientName(); // Extract client name
                    startTime = Instant.now();        // Record session start time
                    logger.log(clientName + " connected.");
                    out.println("ACK");               // Send acknowledgement

                // Handle math request from client
                } else if (line.startsWith("REQUEST")) {
                    RequestMessage req = RequestMessage.parse(line);
                    try {
                        int result = MathCalculator.evaluate(req.getExpression()); // Evaluate expression
                        String response = req.getExpression() + " = " + result;
                        out.println(new ResponseMessage(clientName, response).toProtocolString());
                        logger.log(clientName + " requested: " + req.getExpression() + " → " + result);
                    } catch (Exception e) {
                        // Send error back to client if evaluation fails
                        out.println("ERROR: " + e.getMessage());
                        logger.log("Error from " + clientName + ": " + e.getMessage());
                    }

                // Handle disconnect request
                } else if (line.startsWith("DISCONNECT")) {
                    ConnectionMessage msg = ConnectionMessage.parse(line);
                    Duration duration = Duration.between(startTime, Instant.now());
                    logger.log(clientName + " disconnected. Session: " + duration.toSeconds() + "s.");
                    break; // Exit loop and close connection
                }
            }

        } catch (Exception e) {
            // Log any fatal errors with this client
            logger.log("Fatal error with client " + clientName + ": " + e.getMessage());

        } finally {
            // Ensure the socket is closed when done
            try {
                socket.close();
            } catch (IOException e) {
                // Ignore cleanup exceptions
            }
        }
    }
}
