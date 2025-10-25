package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Consolidate all the student's phone number.
 */
public class ConsolidateCommand extends Command {

    public static final String COMMAND_WORD = "consolidate";

    public static final String MESSAGE_SUCCESS = "Consolidate all distinct students' data: \n";

    /**
     * Lists the categories whose data will be consolidated under ConsolidateCommand.
     */
    public enum ConsolidateCategory {
        NAME, PHONE, EMAIL, ADDRESS
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        ObservableList<Person> persons = model.getAddressBook().getPersonList();
        List<String> categoryCompilation = new ArrayList<>();

        if (model.isEmptyAddressBook()) {
            throw new CommandException(Messages.specifyEmptyUserListMessage(COMMAND_WORD));
        }

        ConsolidateCategory[] categories = ConsolidateCategory.values();
        for (ConsolidateCategory category : categories) {
            categoryCompilation.add(this.consolidateData(category, persons));
        }
        String fullCompilation = String.join("\n\n", categoryCompilation);

        return new CommandResult(MESSAGE_SUCCESS + fullCompilation);
    }

    /**
     * Extract the data of the specified category of the specified person.
     *
     * @param category Category of the data to be extracted.
     * @param person Person whose data to be extracted.
     * @return Value of data under specified category of specified person.
     */
    public static String getData(ConsolidateCategory category, Person person) {
        if (category == ConsolidateCategory.NAME) {
            return person.getName().toString();
        } else if (category == ConsolidateCategory.PHONE) {
            return person.getPhone().toString();
        } else if (category == ConsolidateCategory.EMAIL) {
            return person.getEmail().toString();
        } else if (category == ConsolidateCategory.ADDRESS) {
            return person.getAddress().toString();
        } else {
            return "No such category.";
        }
    }

    /**
     * Returns the header that specify what category the consolidated data fall under.
     *
     * @param category Category of the consolidated data.
     * @return Header.
     */
    public static String formatCategoryHeader(ConsolidateCategory category) {
        String categoryName = "";
        if (category == ConsolidateCategory.NAME) {
            categoryName = "name";
        } else if (category == ConsolidateCategory.PHONE) {
            categoryName = "phone";
        } else if (category == ConsolidateCategory.EMAIL) {
            categoryName = "email";
        } else if (category == ConsolidateCategory.ADDRESS) {
            categoryName = "address";
        } else {
            // do nothing
        }
        assert !categoryName.isEmpty() : "categoryName cannot be empty";
        return "- List of students' " + categoryName + ": ";
    }

    private String consolidateData(ConsolidateCategory category, ObservableList<Person> persons) {
        assert !persons.isEmpty() : "Only when there are persons data stored, "
                + "do we call consolidateData function";

        HashSet<String> container = new HashSet<>();
        for (Person person : persons) {
            container.add(this.getData(category, person));
        }

        ArrayList<String> list = new ArrayList<>(container);
        Collections.sort(list);
        String compilation = "";

        for (String s : list) {
            compilation += "\n" + s;
        }

        return formatCategoryHeader(category) + compilation;
    }
}
