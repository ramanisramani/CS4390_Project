package protocol;

/**
 * Abstract base class for all types of messages used in the client-server protocol.
 * Subclasses must implement the method to convert the message to a protocol-compliant string.
 */
public abstract class Message {

    /**
     * Converts the message object into a string format suitable for transmission over the network.
     * This method must be implemented by any subclass (e.g., RequestMessage, ResponseMessage).
     * 
     * @return A protocol-compliant string representation of the message.
     */
    public abstract String toProtocolString();
}
