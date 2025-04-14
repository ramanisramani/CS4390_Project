package protocol;

public class ConnectionMessage extends Message {
    public enum Type { CONNECT, DISCONNECT }

    private Type type;
    private String clientName;

    public ConnectionMessage(Type type, String clientName) {
        this.type = type;
        this.clientName = clientName;
    }

    public Type getType() { return type; }
    public String getClientName() { return clientName; }

    @Override
    public String toProtocolString() {
        return type.name() + ":" + clientName;
    }

    public static ConnectionMessage parse(String msg) {
        String[] parts = msg.split(":");
        return new ConnectionMessage(Type.valueOf(parts[0]), parts[1]);
    }
}
