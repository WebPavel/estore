package exception;

public class ActivateException extends RuntimeException {
    public ActivateException() {
        super();
    }

    public ActivateException(String message) {
        super(message);
    }

    public ActivateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActivateException(Throwable cause) {
        super(cause);
    }

    protected ActivateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
