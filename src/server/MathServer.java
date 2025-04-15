package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * Entry point for the MathServer.
 * This server listens for incoming client connections on port 9090
 * and delegates each connection to a separate thread using a thread pool.
 */
public class MathServer {

    public static void main(String[] args) throws IOException {
        // Create a server socket that listens on port 9090
        ServerSocket serverSocket = new ServerSocket(9090);

        // Initialize a logger to record events to "server_log.txt"
        Logger logger = new Logger("server_log.txt");

        // Create a thread pool that can handle multiple clients concurrently
        ExecutorService pool = Executors.newCachedThreadPool();

        System.out.println("MathServer started on port 9090");

        // Continuously accept client connections
        while (true) {
            // Accept a new client connection
            Socket socket = serverSocket.accept();

            // Assign a ClientHandler to process this client on a new thread
            pool.execute(new ClientHandler(socket, logger));
        }
    }
}
