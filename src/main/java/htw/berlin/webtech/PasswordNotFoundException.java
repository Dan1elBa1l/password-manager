package htw.berlin.webtech;

public class PasswordNotFoundException extends RuntimeException {

    public PasswordNotFoundException(String message) {
        super(message);
    }
}
