package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.PersonBuilder;

public class ZoomInTest {
    @Test
    public void constructor() {
        assertThrows(AssertionError.class, () -> new ZoomIn(ZoomInType.NONE, new PersonBuilder().build(), null));
        assertThrows(AssertionError.class, () -> new ZoomIn(ZoomInType.NONE, null, new EventBuilder().build()));
        assertThrows(AssertionError.class, () -> new ZoomIn(ZoomInType.NONE, new PersonBuilder().build(),
                new EventBuilder().build()));
        assertDoesNotThrow(() -> new ZoomIn(ZoomInType.NONE, null, null));

        assertThrows(AssertionError.class, () -> new ZoomIn(ZoomInType.EVENT, new PersonBuilder().build(),
                null));
        assertDoesNotThrow(() -> new ZoomIn(ZoomInType.EVENT, null, new EventBuilder().build()));
        assertThrows(AssertionError.class, () -> new ZoomIn(ZoomInType.EVENT, new PersonBuilder().build(),
                new EventBuilder().build()));
        assertThrows(AssertionError.class, () -> new ZoomIn(ZoomInType.EVENT, null, null));

        assertDoesNotThrow(() -> new ZoomIn(ZoomInType.PERSON, new PersonBuilder().build(), null));
        assertThrows(AssertionError.class, () -> new ZoomIn(ZoomInType.PERSON, null, new EventBuilder().build()));
        assertThrows(AssertionError.class, () -> new ZoomIn(ZoomInType.PERSON, new PersonBuilder().build(),
                new EventBuilder().build()));
        assertThrows(AssertionError.class, () -> new ZoomIn(ZoomInType.PERSON, null, null));
    }
}
