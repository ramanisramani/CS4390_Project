package protocol;

/**
 * Represents a request message from a client to the server.
 * Format: REQUEST:<client_name>:<expression>
 * Example: REQUEST:Shreya:5 + 2
 */
public class RequestMessage extends Message {

    private final String clientName;
    private final String expression;

    /**
     * Constructs a RequestMessage.
     *
     * @param clientName The name of the client sending the request
     * @param expression The math expression to evaluate
     */
    public RequestMessage(String clientName, String expression) {
        this.clientName = clientName;
        this.expression = expression;
    }

    /** @return The client name associated with this request */
    public String getClientName() {
        return clientName;
    }

    /** @return The math expression included in the request */
    public String getExpression() {
        return expression;
    }

    /**
     * Converts the request into a protocol-formatted string.
     * Example: REQUEST:Shreya:5 + 2
     *
     * @return A string suitable for transmission over the network
     */
    @Override
    public String toProtocolString() {
        return "REQUEST:" + clientName + ":" + expression;
    }

    /**
     * Parses a protocol string into a RequestMessage object.
     *
     * @param msg The raw string received from the network
     * @return A RequestMessage object parsed from the input
     * @throws IllegalArgumentException if the message format is invalid
     */
    public static RequestMessage parse(String msg) {
        String[] parts = msg.split(":", 3); // Split into 3 parts: REQUEST, name, expression

        if (parts.length != 3 || !parts[0].equals("REQUEST")) {
            throw new IllegalArgumentException("Invalid RequestMessage format. Expected: REQUEST:<name>:<expression>");
        }

        return new RequestMessage(parts[1], parts[2]);
    }
}
