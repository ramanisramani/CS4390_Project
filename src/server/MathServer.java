package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class MathServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9090);
        Logger logger = new Logger("server_log.txt");
        ExecutorService pool = Executors.newCachedThreadPool();

        System.out.println("MathServer started on port 9090");

        while (true) {
            Socket socket = serverSocket.accept();
            pool.execute(new ClientHandler(socket, logger));
        }
    }
}
