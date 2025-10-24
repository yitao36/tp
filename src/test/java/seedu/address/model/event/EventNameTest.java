package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EventNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventName(null));
    }

    @Test
    public void constructor_invalidEventName_throwsIllegalArgumentException() {
        String invalidEventName = " ";
        assertThrows(IllegalArgumentException.class, () -> new EventName(invalidEventName));
    }

    @Test
    public void isValidName() {
        // null description
        assertThrows(NullPointerException.class, () -> EventName.isValidName(null));

        // invalid descriptions
        assertFalse(EventName.isValidName(" ")); // spaces only
        assertFalse(EventName.isValidName(" name")); // start with space

        // valid descriptions
        assertTrue(EventName.isValidName("Meeting"));
        assertTrue(EventName.isValidName("Meeting 2")); // multiple words
    }

    @Test
    public void equals() {
        EventName description = new EventName("Valid EventName");

        // same values -> returns true
        assertTrue(description.equals(new EventName("Valid EventName")));

        // same object -> returns true
        assertTrue(description.equals(description));

        // null -> returns false
        assertFalse(description.equals(null));

        // different types -> returns false
        assertFalse(description.equals(5.0f));

        // different values -> returns false
        assertFalse(description.equals(new EventName("Other Valid EventName")));
    }
}
