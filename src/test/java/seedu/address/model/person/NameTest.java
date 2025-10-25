package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
        assertTrue(Name.isValidName("Dr. Bob Tan-Ray, the 2nd (Bobby')")); // uses all types of valid characters
    }

    @Test
    public void getStyleWarningMessages() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.getStyleWarningMessage(null));

        // warning message
        assertFalse(Name.getStyleWarningMessage("Muscle-Man").isEmpty()); // capitalized in middle
        assertFalse(Name.getStyleWarningMessage("pEter jack").isEmpty()); // non-capitalized name
        assertFalse(Name.getStyleWarningMessage("peter ()").isEmpty()); // word with no alphabets
        assertFalse(Name.getStyleWarningMessage("Peter (Dean").isEmpty()); // unbalanced brackets
        assertFalse(Name.getStyleWarningMessage("Too many  spaces").isEmpty()); // consecutive spaces

        // stylish names
        assertTrue(Name.getStyleWarningMessage("Peter Jack (Dean)").isEmpty());
        assertTrue(Name.getStyleWarningMessage("Frank Kurt's Big Muscle-man").isEmpty());
    }

    @Test
    public void equals() {
        Name name = new Name("Valid Name");

        // same values -> returns true
        assertTrue(name.equals(new Name("Valid Name")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new Name("Other Valid Name")));
    }
}
