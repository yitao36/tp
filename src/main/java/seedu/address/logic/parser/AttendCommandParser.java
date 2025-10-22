package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AttendCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AttendCommand object.
 */
public class AttendCommandParser {
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AttendCommand
     * and returns an AttendCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public void parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PHONE, PREFIX_EMAIL);

        if (!arePrefixesPresent(argMultimap, PREFIX_PHONE, PREFIX_EMAIL)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendCommand.MESSAGE_USAGE));
        }

        Set<Index> personIndexes = parseMultiplePersons(argMultimap.getAllValues(PREFIX_PHONE));
    }

    /**
     * Takes in a list of user inputs, and parses them into integers.
     * Invalid inputs are stored in a separate set.
     * Duplicate inputs are stored in a separate set.
     * @return A new AttendCommand to handle valid, invalid, and duplicate inputs.
     */
    public Set<Index> parseMultiplePersons(List<String> personList) {
        Set<Index> personSet = new HashSet<>();
        Set<Index> duplicateSet = new HashSet<>();
        Set<String> invalidSet = new HashSet<>();
        personList.forEach(s -> {
            try {
                Index index = Index.fromZeroBased(Integer.parseInt(s));
                if (personSet.contains(index)) {
                    duplicateSet.add(index);
                } else {
                    personSet.add(index);
                }
            } catch (NumberFormatException e) {
                invalidSet.add(s);
            }
        });
        return personSet;
    }
}
