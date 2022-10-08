package exception;

public class ShopException extends RuntimeException{
    public ShopException() {
        super();
    }

    public ShopException(String message) {
        super(message);
    }

    public ShopException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShopException(Throwable cause) {
        super(cause);
    }
}
