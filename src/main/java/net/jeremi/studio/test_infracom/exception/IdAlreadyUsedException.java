package net.jeremi.studio.test_infracom.exception;

public class IdAlreadyUsedException extends RuntimeException {

    public IdAlreadyUsedException(String message) {
        super(message);
    }
}
