package tn.esprit.ms.utilisateurmanagement;

public class LoginMessage {
    private String message;
    private boolean success;

    public LoginMessage(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
