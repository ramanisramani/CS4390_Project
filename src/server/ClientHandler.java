package server;

import protocol.*;

import java.io.*;
import java.net.*;
import java.time.*;

public class ClientHandler implements Runnable {
    private Socket socket;
    private Logger logger;
    private BufferedReader in;
    private PrintWriter out;
    private String clientName;
    private Instant startTime;

    public ClientHandler(Socket socket, Logger logger) {
        this.socket = socket;
        this.logger = logger;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String line = in.readLine();
                if (line == null) break;

                if (line.startsWith("CONNECT")) {
                    ConnectionMessage msg = ConnectionMessage.parse(line);
                    clientName = msg.getClientName();
                    startTime = Instant.now();
                    logger.log(clientName + " connected.");
                    out.println("ACK");

                } else if (line.startsWith("REQUEST")) {
                    RequestMessage req = RequestMessage.parse(line);
                    int result = MathCalculator.evaluate(req.getExpression());
                    String response = req.getExpression() + "=" + result;
                    out.println(new ResponseMessage(clientName, response).toProtocolString());
                    logger.log(clientName + " requested: " + req.getExpression() + " → " + result);

                } else if (line.startsWith("DISCONNECT")) {
                    ConnectionMessage msg = ConnectionMessage.parse(line);
                    Duration duration = Duration.between(startTime, Instant.now());
                    logger.log(clientName + " disconnected. Session: " + duration.toSeconds() + "s.");
                    break;
                }
            }
        } catch (Exception e) {
            logger.log("Error with client " + clientName + ": " + e.getMessage());
        } finally {
            try { socket.close(); } catch (IOException e) { /* ignore */ }
        }
    }
}
