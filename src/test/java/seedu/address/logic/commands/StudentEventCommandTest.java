package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.executeCommandOnEmptyModel;
import static seedu.address.logic.commands.StudentEventCommand.COMMAND_WORD;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code StudentEventCommand}.
 */
public class StudentEventCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        StudentEventCommand studentEventCommand = new StudentEventCommand(INDEX_FIRST_PERSON);

        // First person attends all events.
        String expectedMessage = String.format(StudentEventCommand.MESSAGE_LIST_EVENT_SUCCESS,
                model.getFilteredEventList().size());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        NameContainsKeywordsPredicate predicate =
                new NameContainsKeywordsPredicate(Arrays.asList(firstPerson.getName().toString().split("\\s+")));
        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(studentEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        StudentEventCommand studentEventCommand = new StudentEventCommand(outOfBoundIndex);

        assertCommandFailure(studentEventCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_emptyAddressBook_throwsCommandException() {
        try {
            executeCommandOnEmptyModel(new StudentEventCommand(INDEX_FIRST_PERSON));
            fail(); // test should not reach this line
        } catch (CommandException e) {
            assertEquals(Messages.specifyEmptyUserListMessage(COMMAND_WORD), e.getMessage());
        }
    }

    @Test
    public void equals() {
        StudentEventCommand studentEventFirstCommand = new StudentEventCommand(INDEX_FIRST_PERSON);
        StudentEventCommand studentEventSecondCommand = new StudentEventCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(studentEventFirstCommand.equals(studentEventFirstCommand));

        // same values -> returns true
        StudentEventCommand studentEventFirstCommandCopy = new StudentEventCommand(INDEX_FIRST_PERSON);
        assertTrue(studentEventFirstCommand.equals(studentEventFirstCommandCopy));

        // different types -> returns false
        assertFalse(studentEventFirstCommand.equals(1));

        // null -> returns false
        assertFalse(studentEventFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(studentEventFirstCommand.equals(studentEventSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        StudentEventCommand studentEventCommand = new StudentEventCommand(targetIndex);
        String expected = StudentEventCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, studentEventCommand.toString());
    }
}
