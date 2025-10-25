package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.MessageCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Attendance;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

/**
 * Adds the list of persons to the attendance list of an event.
 */
public class AttendCommand extends Command {
    public static final String COMMAND_WORD = "attend";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the list of person at the given indexes to the attendance list of an event.\n"
            + "Parameters: "
            + PREFIX_EVENT + "EVENT_INDEX "
            + PREFIX_PHONE + "PERSON_INDEX [MORE_INDEXES]...\n"
            + "Example: attend e/2 p/1 4 5 6";

    private final Index eventIndex;
    private final Set<Index> attendeesIndex;

    /**
     * Constructs a new AttendCommand with an event index and a list of person indexes.
     */
    public AttendCommand(Index eventIndex, Set<Index> attendeesIndex) {
        requireNonNull(eventIndex);
        requireNonNull(attendeesIndex);
        assert !attendeesIndex.isEmpty() : "attendees should not be empty.";

        this.eventIndex = eventIndex;
        this.attendeesIndex = attendeesIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Event event = model.getFilteredEventList().get(eventIndex.getZeroBased());
        Attendance attendance = event.getAttendance();

        for (Index i : attendeesIndex) {
            Person p = model.getFilteredPersonList().get(i.getZeroBased());
            if (attendance.hasPerson(p)) {
                MessageCenter.appendEnd(
                        String.format("Warning: %s is already in the event's attendance list. Person not added.",
                                p.getName().fullName));
            } else {
                attendance.addPerson(p);
            }
        }


        return null;
    }
}
