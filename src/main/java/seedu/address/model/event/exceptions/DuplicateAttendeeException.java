package seedu.address.model.event.exceptions;

/**
 * Signals that the operation will result in duplicate Person being added to attendance list of an event.
 */
public class DuplicateAttendeeException extends RuntimeException {
    public DuplicateAttendeeException() {
        super("Operation would result in duplicate attendees in the same event");
    }
}
