package circuito.Exceptions;

public class NonExistantKey extends Exception {
    public NonExistantKey(String errorMessage) {
        super(errorMessage);
    }
}