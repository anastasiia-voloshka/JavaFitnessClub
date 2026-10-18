package src;
public class InvalidAgeException extends DomainException {

    public InvalidAgeException(String message, int invalidAge) {
        super(message, invalidAge);
    }
}