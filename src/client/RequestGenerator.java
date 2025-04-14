package client;

import java.util.Random;

public class RequestGenerator {
    private static final String[] ops = {"+", "-", "*", "/"};
    private static final Random rand = new Random();

    public static String generate() {
        int a = rand.nextInt(100);
        int b = rand.nextInt(1, 100);
        String op = ops[rand.nextInt(ops.length)];
        return a + " " + op + " " + b;
    }
}
