package lk.gedaratama.backendserver.response;

/**
 * @author Sashini Tharuka on 5/31/2020.
 */
public class MessageResponse {

    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

