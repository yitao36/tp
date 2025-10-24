package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DurationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Duration(null));
    }

    @Test
    public void constructor_invalidDuration_throwsIllegalArgumentException() {
        String invalidDuration = "1/13/2025";
        assertThrows(IllegalArgumentException.class, () -> new Duration(invalidDuration));
    }

    @Test
    public void isValidDuration() {
        // null duration
        assertThrows(NullPointerException.class, () -> Duration.isValidDuration(null));

        // invalid durations
        assertFalse(Duration.isValidDuration("")); // spaces only
        assertFalse(Duration.isValidDuration("1/13/2025")); // invalid date
        assertFalse(Duration.isValidDuration("1/10/2025-1/13/2025")); // invalid date
        assertFalse(Duration.isValidDuration("51/10/2025-1/11/2025")); // invalid date
        assertFalse(Duration.isValidDuration("1/10/2025-2/10/2025-3/10/2025")); // invalid date range
        assertFalse(Duration.isValidDuration("2/10/2025-1/10/2025")); // invalid date range

        // valid durations
        assertTrue(Duration.isValidDuration("1/10/2025"));
        assertTrue(Duration.isValidDuration("1/10/2025-1/10/2025")); // same date range
        assertTrue(Duration.isValidDuration("1/10/2025-2/10/2025")); // date range
        assertTrue(Duration.isValidDuration("1/10/2025 - 2/10/2025")); // date range
    }

    @Test
    public void equals() {
        Duration duration = new Duration("1/10/2025");

        // same values -> returns true
        assertTrue(duration.equals(new Duration("1/10/2025")));
        assertTrue(duration.equals(new Duration("1/10/2025-1/10/2025")));

        // same object -> returns true
        assertTrue(duration.equals(duration));

        // null -> returns false
        assertFalse(duration.equals(null));

        // different types -> returns false
        assertFalse(duration.equals(5.0f));

        // different values -> returns false
        assertFalse(duration.equals(new Duration("1/10/2025-2/10/2025")));
    }
}
