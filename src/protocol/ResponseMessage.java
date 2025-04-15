package protocol;

/**
 * Represents a message sent from the server to the client with a computed result.
 * This is used to return the result of a math expression evaluation.
 */
public class ResponseMessage extends Message {

    // The name of the client the response is associated with
    private String clientName;

    // The response content (e.g., "5 + 2=7")
    private String response;

    /**
     * Constructs a new ResponseMessage.
     * @param clientName The name of the client who submitted the request
     * @param response The result of the evaluated expression
     */
    public ResponseMessage(String clientName, String response) {
        this.clientName = clientName;
        this.response = response;
    }

    /**
     * Converts this ResponseMessage to a protocol-compliant string.
     * Format: "RESPONSE:<clientName>:<response>"
     * Example: "RESPONSE:Shreya:5 + 2=7"
     * 
     * @return String ready to be sent over the network.
     */
    @Override
    public String toProtocolString() {
        return "RESPONSE:" + clientName + ":" + response;
    }

    /**
     * Parses a response message string and returns a ResponseMessage object.
     * Assumes the format is: "RESPONSE:<clientName>:<response>"
     * 
     * @param msg The received protocol string
     * @return A ResponseMessage object
     */
    public static ResponseMessage parse(String msg) {
        String[] parts = msg.split(":", 3); // Split into 3 parts: "RESPONSE", clientName, response
        return new ResponseMessage(parts[1], parts[2]);
    }
}
