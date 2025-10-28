package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.executeCommandOnEmptyModel;
import static seedu.address.logic.commands.EventStudentCommand.COMMAND_WORD;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code EventStudentCommand}.
 */
public class EventStudentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Event firstEvent = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        EventStudentCommand eventStudentCommand = new EventStudentCommand(INDEX_FIRST_EVENT);

        // First event is attended by all students.
        String expectedMessage = String.format(EventStudentCommand.MESSAGE_LIST_STUDENTS_SUCCESS,
                model.getFilteredPersonList().size());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredEventList((event) -> event.equals(firstEvent));

        assertCommandSuccess(eventStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        EventStudentCommand eventStudentCommand = new EventStudentCommand(outOfBoundIndex);

        assertCommandFailure(eventStudentCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_emptyAddressBook_throwsCommandException() {
        try {
            executeCommandOnEmptyModel(new EventStudentCommand(INDEX_FIRST_EVENT));
            fail(); // test should not reach this line
        } catch (CommandException e) {
            assertEquals(Messages.specifyEmptyEventListMessage(COMMAND_WORD), e.getMessage());
        }
    }

    @Test
    public void equals() {
        EventStudentCommand eventStudentFirstCommand = new EventStudentCommand(INDEX_FIRST_EVENT);
        EventStudentCommand eventStudentSecondCommand = new EventStudentCommand(INDEX_SECOND_EVENT);

        // same object -> returns true
        assertTrue(eventStudentFirstCommand.equals(eventStudentFirstCommand));

        // same values -> returns true
        EventStudentCommand eventStudentFirstCommandCopy = new EventStudentCommand(INDEX_FIRST_EVENT);
        assertTrue(eventStudentFirstCommand.equals(eventStudentFirstCommandCopy));

        // different types -> returns false
        assertFalse(eventStudentFirstCommand.equals(1));

        // null -> returns false
        assertFalse(eventStudentFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(eventStudentFirstCommand.equals(eventStudentSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        EventStudentCommand eventStudentCommand = new EventStudentCommand(targetIndex);
        String expected = EventStudentCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, eventStudentCommand.toString());
    }
}
