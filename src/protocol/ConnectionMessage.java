package protocol;

/**
 * Represents a message related to connecting or disconnecting from the server.
 * Used by the client to indicate JOIN or LEAVE, and by the server to parse such actions.
 */
public class ConnectionMessage extends Message {

    // Enum to represent the type of connection action
    public enum Type {
        CONNECT,   // Client is connecting
        DISCONNECT // Client is disconnecting
    }

    // The type of message: CONNECT or DISCONNECT
    private Type type;

    // The name of the client sending the message
    private String clientName;

    /**
     * Constructor for ConnectionMessage
     * @param type Type of message (CONNECT or DISCONNECT)
     * @param clientName Name of the client
     */
    public ConnectionMessage(Type type, String clientName) {
        this.type = type;
        this.clientName = clientName;
    }

    // Getter for type
    public Type getType() {
        return type;
    }

    // Getter for clientName
    public String getClientName() {
        return clientName;
    }

    /**
     * Converts the message into a string format to send over the network.
     * Format: "CONNECT:Shreya" or "DISCONNECT:Shreya"
     */
    @Override
    public String toProtocolString() {
        return type.name() + ":" + clientName;
    }

    /**
     * Parses a received string and returns a ConnectionMessage object.
     * Assumes the format is valid (e.g., "CONNECT:Shreya").
     * @param msg The received message string
     * @return A new ConnectionMessage object
     */
    public static ConnectionMessage parse(String msg) {
        String[] parts = msg.split(":");
        return new ConnectionMessage(Type.valueOf(parts[0]), parts[1]);
    }
}
