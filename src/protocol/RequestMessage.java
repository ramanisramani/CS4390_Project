package protocol;

public class RequestMessage extends Message {
    private String clientName;
    private String expression;

    public RequestMessage(String clientName, String expression) {
        this.clientName = clientName;
        this.expression = expression;
    }

    public String getClientName() { return clientName; }
    public String getExpression() { return expression; }

    @Override
    public String toProtocolString() {
        return "REQUEST:" + clientName + ":" + expression;
    }

    public static RequestMessage parse(String msg) {
        String[] parts = msg.split(":");
        return new RequestMessage(parts[1], parts[2]);
    }
}
