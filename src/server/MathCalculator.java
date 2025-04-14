package server;

public class MathCalculator {
    public static int evaluate(String expr) throws Exception {
        String[] tokens = expr.trim().split(" ");
        int a = Integer.parseInt(tokens[0]);
        int b = Integer.parseInt(tokens[2]);
        String op = tokens[1];

        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) throw new ArithmeticException("Divide by zero");
                return a / b;
            default:
                throw new Exception("Invalid operator: " + op);
        }
    }
}
