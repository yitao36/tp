package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EmergencyContactTest {

    static final String validPhone = "98765432";
    static final String validName = "Alice";
    static final String otherValidPhone = "91234567";
    static final String otherValidName = "Bob";

    @Test
    public void constructor_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EmergencyContact(null, validPhone));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new EmergencyContact(invalidName, validPhone));
    }

    @Test
    public void constructor_nullPhone_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EmergencyContact(validName, null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new EmergencyContact(validName, invalidPhone));
    }

    @Test
    public void equals() {
        EmergencyContact emergencyContact = new EmergencyContact(validName, validPhone);

        // same values -> returns true
        assertTrue(emergencyContact.equals(new EmergencyContact(validName, validPhone)));

        // same object -> returns true
        assertTrue(emergencyContact.equals(emergencyContact));

        // null -> returns false
        assertFalse(emergencyContact.equals(null));

        // different types -> returns false
        assertFalse(emergencyContact.equals(5.0f));

        // different values -> returns false
        assertFalse(emergencyContact.equals(new EmergencyContact(otherValidName, validPhone)));
        assertFalse(emergencyContact.equals(new EmergencyContact(validName, otherValidPhone)));
        assertFalse(emergencyContact.equals(new EmergencyContact(otherValidName, otherValidPhone)));
    }

    @Test
    public void toStringMethod() {
        final EmergencyContact emergencyContact = new EmergencyContact(validName, validPhone);
        final String expected = String.format("%s{name=%s, phone=%s}", EmergencyContact.class.getCanonicalName(),
                validName, validPhone);
        assertEquals(expected, emergencyContact.toString());
    }

    @Test
    public void hashCodeMethod() {
        final EmergencyContact emergencyContact = new EmergencyContact(validName, validPhone);

        // equal objects returns same hash code
        assertEquals(emergencyContact.hashCode(), new EmergencyContact(validName, validPhone).hashCode());

        // different objects return different hash codes
        assertNotEquals(emergencyContact.hashCode(), new EmergencyContact(otherValidName, validPhone).hashCode());
        assertNotEquals(emergencyContact.hashCode(), new EmergencyContact(validName, otherValidPhone).hashCode());
        assertNotEquals(emergencyContact.hashCode(), new EmergencyContact(otherValidName, otherValidPhone).hashCode());
    }
}
