package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

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

        Predicate<Person> predicate = null;

        // Iterates through which prefix is selected.
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String name = argMultimap.getValue(PREFIX_NAME).get();
            String[] nameKeywords = name.split("\\s+");

            // Throws an error if invalid name is supplied
            for (String s : nameKeywords) {
                ParserUtil.parseName(s);
            }

            predicate = new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords));
        }
        assert predicate != null : "unknown prefix specified";

        return new FindCommand(predicate);
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
