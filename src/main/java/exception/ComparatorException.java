package exception;

public class ComparatorException extends Exception{

    private final String message;

    public ComparatorException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
