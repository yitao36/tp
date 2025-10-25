package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.executeCommandOnEmptyModel;
import static seedu.address.logic.commands.ConsolidateCommand.COMMAND_WORD;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.testutil.TypicalPersons;

public class ConsolidateCommandTest {

    private String orderTwoValues(String firstValue, String secondValue) {
        String result = "";
        if (firstValue.compareTo(secondValue) < 0) {
            result += firstValue + "\n" + secondValue;
        } else if (firstValue.compareTo(secondValue) == 0) {
            result += firstValue;
        } else {
            result += secondValue + "\n" + firstValue;
        }
        return result;
    }

    @Test
    public void execute_validAddressBook_success() {

        Model model = new ModelManager();
        model.setAddressBook(new AddressBook());
        model.addPerson(TypicalPersons.CARL);
        model.addPerson(TypicalPersons.DANIEL);
        String commandResult = ConsolidateCommand.MESSAGE_SUCCESS;

        ConsolidateCommand.ConsolidateCategory[] categories = ConsolidateCommand.ConsolidateCategory.values();
        List<String> categoryCompilation = new ArrayList<>();

        // note: except for CARL's name, CARL's phone number, email and address is larger than DANIEL
        for (ConsolidateCommand.ConsolidateCategory category : categories) {
            String temp = ConsolidateCommand.formatCategoryHeader(category) + "\n"
                    + orderTwoValues(ConsolidateCommand.getData(category, CARL),
                    ConsolidateCommand.getData(category, DANIEL));
            categoryCompilation.add(temp);
        }
        String fullCompilation = String.join("\n\n", categoryCompilation);
        commandResult += fullCompilation;

        assertCommandSuccess(new ConsolidateCommand(), model, commandResult, model);
    }

    @Test
    public void execute_emptyAddressBook_throwsCommandException() {
        try {
            executeCommandOnEmptyModel(new ConsolidateCommand());
            fail(); // test should not reach this line
        } catch (CommandException e) {
            assertEquals(Messages.specifyEmptyUserListMessage(COMMAND_WORD), e.getMessage());
        }
    }
}
