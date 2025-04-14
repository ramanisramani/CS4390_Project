package client;

import protocol.*;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MathClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9090);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        out.println(new ConnectionMessage(ConnectionMessage.Type.CONNECT, name).toProtocolString());
        if ("ACK".equals(in.readLine())) {
            System.out.println("Connected!");

            for (int i = 0; i < 3; i++) {
                String expr = RequestGenerator.generate();
                out.println(new RequestMessage(name, expr).toProtocolString());
                System.out.println(in.readLine());
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            }

            out.println(new ConnectionMessage(ConnectionMessage.Type.DISCONNECT, name).toProtocolString());
        }

        socket.close();
    }
}
