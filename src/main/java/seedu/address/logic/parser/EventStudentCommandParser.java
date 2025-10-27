package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EventStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EventStudentCommand object
 */
public class EventStudentCommandParser implements Parser<EventStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EventStudentCommand
     * and returns a EventStudentCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public EventStudentCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new EventStudentCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EventStudentCommand.MESSAGE_USAGE), pe);
        }
    }

}
