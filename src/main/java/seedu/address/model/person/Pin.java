package seedu.address.model.person;

import java.util.Objects;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable;
 */
public class Pin {
    public final boolean value;

    /**
     * Constructs an {@code Pin}.
     */
    public Pin(boolean isPinned) {
        value = isPinned;
    }

    @Override
    public String toString() {
        return value ? "true" : "false";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Pin)) {
            return false;
        }

        Pin otherPin = (Pin) other;
        return value == otherPin.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
