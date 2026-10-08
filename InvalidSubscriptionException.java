public class InvalidSubscriptionException extends DomainException {

    public InvalidSubscriptionException(String message, Object invalidValue) {
        super(message, invalidValue);
    }
}