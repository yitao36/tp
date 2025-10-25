package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.MessageCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Attendance;
import seedu.address.model.event.PersonReference;
import seedu.address.model.person.Name;
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

    public static final String MESSAGE_SUCCESS = "Attendance of Persons added: %1$s";

    private final Index event;
    private final Set<Index> attendees;

    /**
     * Constructs a new AttendCommand with an event index and a list of person indexes.
     */
    public AttendCommand(Index event, Set<Index> attendees) {
        requireNonNull(event);
        requireNonNull(attendees);
        assert !attendees.isEmpty() : "attendees should not be empty.";

        this.event = event;
        this.attendees = attendees;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Attendance attendance = model.getFilteredEventList().get(event.getZeroBased()).getAttendance();
        Set<PersonReference> persons = attendees
                .stream()
                .map(i -> {
                    Person p = model.getFilteredPersonList().get(i.getZeroBased());
                    PersonReference pr = new PersonReference(p);
                    if (attendance.hasPerson(pr)) {
                        MessageCenter.appendEnd(
                                String.format("Warning: %s is already in the event's attendance list. "
                                        + "Person not added.",
                                        pr.getName().fullName));
                        return null;
                    } else {
                        attendance.addPerson(pr);
                        return pr;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toUnmodifiableSet());

        // Convert to names to display text result of names added.
        Set<Name> names = persons.stream()
                .map(PersonReference::getName)
                .collect(Collectors.toUnmodifiableSet());

        return new CommandResult(String.format(MESSAGE_SUCCESS, names));
    }
}
