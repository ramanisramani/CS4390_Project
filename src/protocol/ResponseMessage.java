package protocol;

public class ResponseMessage extends Message {
    private String clientName;
    private String response;

    public ResponseMessage(String clientName, String response) {
        this.clientName = clientName;
        this.response = response;
    }

    @Override
    public String toProtocolString() {
        return "RESPONSE:" + clientName + ":" + response;
    }

    public static ResponseMessage parse(String msg) {
        String[] parts = msg.split(":", 3);
        return new ResponseMessage(parts[1], parts[2]);
    }
}
