package exception;

public class SetCardException extends RuntimeException{

    private final String message;

    public SetCardException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
