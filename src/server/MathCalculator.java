package server;

public class MathCalculator {
    public static int evaluate(String expr) throws Exception {
        String[] tokens = expr.trim().split(" ");
        if (tokens.length != 3)
            throw new IllegalArgumentException("Invalid expression format. Use format: A + B");

        int a = Integer.parseInt(tokens[0]);
        int b = Integer.parseInt(tokens[2]);
        String op = tokens[1];

        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (b == 0) throw new ArithmeticException("Divide by zero");
                yield a / b;
            }
            default -> throw new Exception("Unknown operator: " + op);
        };
    }
}
