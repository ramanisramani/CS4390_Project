package server;

/**
 * A simple utility class that evaluates basic arithmetic expressions in the form "A op B".
 * Supports addition, subtraction, multiplication, and division.
 */
public class MathCalculator {

    /**
     * Evaluates a math expression string formatted as "A op B", where:
     * - A and B are integers
     * - op is one of: +, -, *, /
     *
     * @param expr The expression to evaluate (e.g., "10 + 5")
     * @return The result of the evaluated expression
     * @throws Exception If the format is invalid or an unsupported operation is given
     */
    public static int evaluate(String expr) throws Exception {
        // Step 1: Split the expression by spaces
        String[] tokens = expr.trim().split(" ");

        // Step 2: Validate the expression format
        if (tokens.length != 3)
            throw new IllegalArgumentException("Invalid expression format. Use format: A + B");

        // Step 3: Parse the operands and operator
        int a = Integer.parseInt(tokens[0]); // Left operand
        int b = Integer.parseInt(tokens[2]); // Right operand
        String op = tokens[1];               // Operator

        // Step 4: Evaluate the expression using a switch expression (Java 14+)
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                // Handle divide-by-zero error
                if (b == 0) throw new ArithmeticException("Divide by zero");
                yield a / b;
            }
            case "%" -> a % b;
            // If an unsupported operator is used, throw an error
            default -> throw new Exception("Unknown operator: " + op);
        };
    }
}
