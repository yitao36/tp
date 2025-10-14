package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments, prefix field, and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * with exactly one prefix specified, and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);
        if (!argMultimap.getPreamble().isEmpty()
            || !existsSingularPrefix(argMultimap, PREFIX_NAME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        // Iterates through which prefix is selected.
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String name = argMultimap.getValue(PREFIX_NAME).get();
            String[] nameKeywords = name.split("\\s+");

            // Throws an error if invalid name is supplied
            for (String s : nameKeywords) {
                ParserUtil.parseName(s);
            }

            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else {
            assert false : "unknown prefix";
        }
        return null;
    }

    /**
     * Returns true if there is exactly one prefix specified, and its value is not empty.
     */
    private static boolean existsSingularPrefix(ArgumentMultimap argMultimap, Prefix... prefixes) {
        List<String> listPrefixes = Arrays.stream(prefixes)
                .map(argMultimap::getAllValues)
                .flatMap(Collection::stream)
                .toList();

        return (listPrefixes.size() == 1 && !listPrefixes.get(0).isEmpty());
    }
}
