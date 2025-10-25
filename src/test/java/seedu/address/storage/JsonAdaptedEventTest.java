package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedEvent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.MEETING;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Description;
import seedu.address.model.event.Duration;
import seedu.address.model.event.EventName;

public class JsonAdaptedEventTest {
    private static final String INVALID_NAME = "";
    private static final String INVALID_DURATION = "1/13/2025";
    private static final String VALID_NAME = MEETING.getName().toString();
    private static final String VALID_DURATION = MEETING.getDuration().toString();
    private static final String VALID_DESCRIPTION = MEETING.getDescription().toString();

    @Test
    public void toModelType_validEventDetails_returnsEvent() throws Exception {
        JsonAdaptedEvent event = new JsonAdaptedEvent(MEETING);
        assertEquals(MEETING, event.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(INVALID_NAME, VALID_DURATION, VALID_DURATION);
        String expectedMessage = EventName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(null, VALID_DURATION, VALID_DURATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidDuration_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, INVALID_DURATION, VALID_DESCRIPTION);
        String expectedMessage = Duration.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullDuration_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, null, VALID_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Duration.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, VALID_DURATION, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }
}
