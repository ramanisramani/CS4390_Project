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

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        out.println(new ConnectionMessage(ConnectionMessage.Type.CONNECT, name).toProtocolString());

        if ("ACK".equals(in.readLine())) {
            System.out.println("Connected!");
            System.out.println("Enter up to 3 math expressions (e.g., 5 + 2, 6*3, 10/5):");

            int count = 0;
            while (count < 3) {
                System.out.print("Expression " + (count + 1) + ": ");
                String expr = scanner.nextLine().trim();

                if (expr.isEmpty()) {
                    System.out.println("Expression cannot be empty.");
                    continue;
                }

                // Auto-format for spacing: 5+2 -> 5 + 2
                expr = expr.replaceAll("(?<=[0-9])(?=[+\\-*/])", " ")
                           .replaceAll("(?<=[+\\-*/])(?=[0-9])", " ")
                           .replaceAll("\\s+", " ")
                           .trim();

                out.println(new RequestMessage(name, expr).toProtocolString());
                String response = in.readLine();

                if (response == null || response.startsWith("ERROR")) {
                    System.out.println("Server says: " + response);
                    continue; // don’t count invalid attempts
                }

                System.out.println(response);
                count++;
            }

            out.println(new ConnectionMessage(ConnectionMessage.Type.DISCONNECT, name).toProtocolString());
            socket.close();
            System.out.println("Disconnected.");
        } else {
            System.out.println("Failed to connect.");
        }
    }
}
