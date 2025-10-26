package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.StudentEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new StudentEventCommand object
 */
public class StudentEventCommandParser implements Parser<StudentEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the StudentEventCommand
     * and returns a StudentEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public StudentEventCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new StudentEventCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, StudentEventCommand.MESSAGE_USAGE), pe);
        }
    }

}
