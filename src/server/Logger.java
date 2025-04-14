package server;

import java.io.*;
import java.time.LocalDateTime;

public class Logger {
    private PrintWriter writer;

    public Logger(String fileName) throws IOException {
        writer = new PrintWriter(new FileWriter(fileName, true), true);
    }

    public synchronized void log(String msg) {
        writer.println("[" + LocalDateTime.now() + "] " + msg);
    }
}
