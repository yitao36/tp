package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TRAINING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_TRAINING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TRAINING;
import static seedu.address.testutil.TypicalEvents.MEETING;
import static seedu.address.testutil.TypicalEvents.TRAINING;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;

public class EventTest {

    @Test
    public void isSameEvent() {
        // same object -> returns true
        assertTrue(MEETING.isSameEvent(MEETING));

        // null -> returns false
        assertFalse(MEETING.isSameEvent(null));

        // same name and duration, all other attributes different -> returns true
        Event editedMeeting = new EventBuilder(MEETING).withDescription(VALID_DESCRIPTION_TRAINING).build();
        assertTrue(MEETING.isSameEvent(editedMeeting));

        // different name, all other attributes same -> returns false
        editedMeeting = new EventBuilder(MEETING).withName(VALID_NAME_TRAINING).build();
        assertFalse(MEETING.isSameEvent(editedMeeting));

        // different duration, all other attributes same -> returns false
        editedMeeting = new EventBuilder(MEETING).withName(VALID_DURATION_TRAINING).build();
        assertFalse(MEETING.isSameEvent(editedMeeting));

        // name differs in case, all other attributes same -> returns false
        Event editedTraining = new EventBuilder(TRAINING).withName(VALID_NAME_TRAINING.toLowerCase()).build();
        assertFalse(TRAINING.isSameEvent(editedTraining));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_TRAINING + " ";
        editedTraining = new EventBuilder(TRAINING).withName(nameWithTrailingSpaces).build();
        assertFalse(TRAINING.isSameEvent(editedTraining));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Event meetingCopy = new EventBuilder(MEETING).build();
        assertTrue(MEETING.equals(meetingCopy));

        // same object -> returns true
        assertTrue(MEETING.equals(MEETING));

        // null -> returns false
        assertFalse(MEETING.equals(null));

        // different type -> returns false
        assertFalse(MEETING.equals(5));

        // different person -> returns false
        assertFalse(MEETING.equals(TRAINING));

        // different name -> returns false
        Event editedMeeting = new EventBuilder(MEETING).withName(VALID_NAME_TRAINING).build();
        assertFalse(MEETING.equals(editedMeeting));

        // different phone -> returns false
        editedMeeting = new EventBuilder(MEETING).withDuration(VALID_DURATION_TRAINING).build();
        assertFalse(MEETING.equals(editedMeeting));

        // different email -> returns false
        editedMeeting = new EventBuilder(MEETING).withDescription(VALID_DESCRIPTION_TRAINING).build();
        assertFalse(MEETING.equals(editedMeeting));
    }

    @Test
    public void toStringMethod() {
        String expected = Event.class.getCanonicalName() + "{name=" + MEETING.getName()
                + ", duration=" + MEETING.getDuration() + ", description=" + MEETING.getDescription() + "}";
        assertEquals(expected, MEETING.toString());
    }
}
