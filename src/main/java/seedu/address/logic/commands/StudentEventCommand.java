package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.PersonReference;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose selected field matches any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class StudentEventCommand extends Command {

    public static final String COMMAND_WORD = "student:event";
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Lists all events attended by the student at the given index.\n"
            + "Parameters: INDEX_OF_STUDENT_LIST (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_LIST_EVENT_SUCCESS = "Listed %1$s events attended";
    public static final String MESSAGE_NO_EVENTS_WARNING = "%1$s has not attended any events";


    private final Index targetIndex;

    public StudentEventCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.isEmptyAddressBook()) {
            throw new CommandException(Messages.specifyEmptyUserListMessage(COMMAND_WORD));
        }

        List<Person> lastShownList = model.getFilteredPersonList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person targetPerson = lastShownList.get(targetIndex.getZeroBased());
        model.updateFilteredPersonAndEventList(makeTargetPersonPredicate(targetPerson),
                makeAttendedByPredicate(targetPerson));

        String message;
        if (model.getFilteredEventList().isEmpty()) {
            message = String.format(MESSAGE_NO_EVENTS_WARNING, targetPerson.getName().toString());
        } else {
            message = String.format(MESSAGE_LIST_EVENT_SUCCESS, model.getFilteredEventList().size());
        }
        return new CommandResult(message);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentEventCommand)) {
            return false;
        }

        StudentEventCommand otherCommand = (StudentEventCommand) other;
        return targetIndex.equals(otherCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }

    /**
     * Creates a predicate that returns true if and only if the person is the same as our given target person.
     */
    private Predicate<Person> makeTargetPersonPredicate(Person targetPerson) {
        return new Predicate<Person>() {
            @Override
            public boolean test(Person person) {
                return targetPerson.isSamePerson(person);
            }
        };
    }

    /**
     * Creates a predicate that returns true if and only if the event is attended by the given attendee.
     */
    private Predicate<Event> makeAttendedByPredicate(Person attendee) {
        return new Predicate<Event>() {
            @Override
            public boolean test(Event event) {
                return event.getAttendance().contains(new PersonReference(attendee));
            }
        };
    }
}
