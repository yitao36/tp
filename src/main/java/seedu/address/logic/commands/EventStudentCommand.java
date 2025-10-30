package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ZoomIn;
import seedu.address.model.ZoomInType;
import seedu.address.model.event.Event;
import seedu.address.model.event.PersonReference;
import seedu.address.model.person.Person;

/**
 * Lists all students attending the given event.
 */
public class EventStudentCommand extends Command {

    public static final String COMMAND_WORD = "event:student";
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Lists all students attending the event at the given index.\n"
            + "Parameters: INDEX_OF_EVENT_LIST (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_LIST_STUDENTS_SUCCESS = "Listed %1$s students attending.";
    public static final String MESSAGE_NO_STUDENTS_WARNING = "%1$s has no students attending.";


    private final Index targetIndex;

    public EventStudentCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.isEventEmptyAddressBook()) {
            throw new CommandException(Messages.specifyEmptyEventListMessage(COMMAND_WORD));
        }

        List<Event> lastShownList = model.getFilteredEventList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event targetEvent = lastShownList.get(targetIndex.getZeroBased());
        model.updateFilteredPersonAndEventList(makeAttendingPredicate(targetEvent),
                makeTargetEventPredicate(targetEvent),
                new ZoomIn(ZoomInType.EVENT, null, targetEvent));

        String message;
        if (model.getFilteredEventList().isEmpty()) {
            message = String.format(MESSAGE_NO_STUDENTS_WARNING, targetEvent.getName().toString());
        } else {
            message = String.format(MESSAGE_LIST_STUDENTS_SUCCESS, model.getFilteredPersonList().size());
        }
        return new CommandResult(message);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventStudentCommand)) {
            return false;
        }

        EventStudentCommand otherCommand = (EventStudentCommand) other;
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
    private Predicate<Event> makeTargetEventPredicate(Event targetEvent) {
        return new Predicate<Event>() {
            @Override
            public boolean test(Event event) {
                return targetEvent.isSameEvent(event);
            }
        };
    }

    /**
     * Creates a predicate that returns true if and only if the event is attended by the given attendee.
     */
    private Predicate<Person> makeAttendingPredicate(Event event) {
        return new Predicate<Person>() {
            @Override
            public boolean test(Person person) {
                return event.getAttendance().contains(new PersonReference(person));
            }
        };
    }
}
