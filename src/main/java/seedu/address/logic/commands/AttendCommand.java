package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds the list of persons to the attendance list of an event.
 */
public class AttendCommand extends Command {
    public static final String COMMAND_WORD = "attend";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the list of person at the given indexes to the attendance list of an event."
            + "\nParameters: "
            + PREFIX_EMAIL + "EVENT_INDEX "
            + PREFIX_PHONE + "PERSON_INDEX [MORE_INDEXES]...";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
