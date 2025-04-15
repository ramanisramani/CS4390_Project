package client;

import protocol.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class MathClient {
    public static void main(String[] args) throws IOException {
        // Step 1: Setup connection to MathServer on localhost port 9090
        Socket socket = new Socket("localhost", 9090);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner scanner = new Scanner(System.in);

        // Step 2: Show welcome header and ask for user name
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║      Welcome to MathClient Terminal    ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        // Step 3: Send CONNECT message to server
        out.println(new ConnectionMessage(ConnectionMessage.Type.CONNECT, name).toProtocolString());

        // Step 4: Wait for server's ACK before proceeding
        if ("ACK".equals(in.readLine())) {
            System.out.println("\n✅ Connected to MathServer as '" + name + "'");
            System.out.println("─────────────────────────────────────────────");
            System.out.println("Enter math expressions (e.g., 5+3, 10 /2, 6*4)");
            System.out.println("Type 'exit' to disconnect and view session summary");
            System.out.println("─────────────────────────────────────────────\n");

            // Step 5: Store history and track expression count
            List<String> history = new ArrayList<>();
            int exprCount = 1;

            // Step 6: Input loop - continue until user types "exit"
            while (true) {
                System.out.print("Expression " + exprCount + ": ");
                String expr = scanner.nextLine().trim();

                // Step 7: Handle exit command
                if (expr.equalsIgnoreCase("exit")) {
                    break;
                }

                // Step 8: Validate non-empty input
                if (expr.isEmpty()) {
                    System.out.println("⚠️  Expression cannot be empty.");
                    continue;
                }

                // Step 9: Normalize formatting - space around operators
                expr = expr.replaceAll("(?<=[0-9])(?=[+\\-*/])", " ")
                           .replaceAll("(?<=[+\\-*/])(?=[0-9])", " ")
                           .replaceAll("\\s+", " ")
                           .trim();

                // Step 10: Send REQUEST to server
                out.println(new RequestMessage(name, expr).toProtocolString());

                // Step 11: Read and handle server response
                String response = in.readLine();
                if (response == null || response.startsWith("ERROR")) {
                    System.out.println("❌ Server says: " + response);
                    history.add(exprCount + ". " + expr + " → [ERROR]");
                } else {
                    System.out.println(" " + response);
                    history.add(exprCount + ". " + response);
                }

                exprCount++; // Increment expression number for next round
            }

            // Step 12: Gracefully disconnect from server
            out.println(new ConnectionMessage(ConnectionMessage.Type.DISCONNECT, name).toProtocolString());
            socket.close();
            System.out.println("\n📴 Disconnected from server.");

            // Step 13: Print session history
            System.out.println("\n📜 Your Session Summary:");
            System.out.println("─────────────────────────────────────────────");
            for (String entry : history) {
                System.out.println("• " + entry);
            }
            System.out.println("─────────────────────────────────────────────");
            System.out.println("👋 Goodbye, " + name + "!");

        } else {
            // If connection was unsuccessful
            System.out.println("❌ Failed to connect to the server.");
        }
    }
}
