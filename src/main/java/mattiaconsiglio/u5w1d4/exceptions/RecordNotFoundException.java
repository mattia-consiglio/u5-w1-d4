package mattiaconsiglio.u5w1d4.exceptions;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String message) {
        super("Record not found: " + message);
    }
}
