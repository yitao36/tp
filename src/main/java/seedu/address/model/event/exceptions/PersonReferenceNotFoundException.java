package seedu.address.model.event.exceptions;

/**
 * This is thrown when attempting to remove a person reference which does not exist in {@code Attendance}.
 */
public class PersonReferenceNotFoundException extends RuntimeException {
    public PersonReferenceNotFoundException() {
        super("Tried to remove a person reference which does not exist.");
    }
}
