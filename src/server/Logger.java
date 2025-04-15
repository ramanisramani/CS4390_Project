package server;

import java.io.*;
import java.time.LocalDateTime;

/**
 * Logger class used by the server to log events such as client connections,
 * disconnections, math requests, and errors. Thread-safe for concurrent access.
 */
public class Logger {
    private PrintWriter writer; // Output stream to write logs to a file

    /**
     * Constructs a Logger that appends to the given log file.
     * @param fileName The name of the log file (e.g., "server_log.txt")
     * @throws IOException If the file cannot be created or opened
     */
    public Logger(String fileName) throws IOException {
        // 'true' enables auto-flushing and appending to existing file content
        writer = new PrintWriter(new FileWriter(fileName, true), true);
    }

    /**
     * Writes a log entry with the current timestamp. Thread-safe to allow
     * multiple clients to log messages concurrently without interleaving.
     * @param msg The message to log
     */
    public synchronized void log(String msg) {
        writer.println("[" + LocalDateTime.now() + "] " + msg);
    }
}
