package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EmergencyContactTest {

    static final String VALID_PHONE = "98765432";
    static final String VALID_NAME = "Alice";
    static final String OTHER_VALID_PHONE = "91234567";
    static final String OTHER_VALID_NAME = "Bob";

    @Test
    public void constructor_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EmergencyContact(null, VALID_PHONE));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new EmergencyContact(invalidName, VALID_PHONE));
    }

    @Test
    public void constructor_nullPhone_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EmergencyContact(VALID_NAME, null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new EmergencyContact(VALID_NAME, invalidPhone));
    }

    @Test
    public void equals() {
        EmergencyContact emergencyContact = new EmergencyContact(VALID_NAME, VALID_PHONE);

        // same values -> returns true
        assertTrue(emergencyContact.equals(new EmergencyContact(VALID_NAME, VALID_PHONE)));

        // same object -> returns true
        assertTrue(emergencyContact.equals(emergencyContact));

        // null -> returns false
        assertFalse(emergencyContact.equals(null));

        // different types -> returns false
        assertFalse(emergencyContact.equals(5.0f));

        // different values -> returns false
        assertFalse(emergencyContact.equals(new EmergencyContact(OTHER_VALID_NAME, VALID_PHONE)));
        assertFalse(emergencyContact.equals(new EmergencyContact(VALID_NAME, OTHER_VALID_PHONE)));
        assertFalse(emergencyContact.equals(new EmergencyContact(OTHER_VALID_NAME, OTHER_VALID_PHONE)));
    }

    @Test
    public void toStringMethod() {
        final EmergencyContact emergencyContact = new EmergencyContact(VALID_NAME, VALID_PHONE);
        final String expected = String.format("%s{name=%s, phone=%s}", EmergencyContact.class.getCanonicalName(),
                VALID_NAME, VALID_PHONE);
        assertEquals(expected, emergencyContact.toString());
    }

    @Test
    public void hashCodeMethod() {
        final EmergencyContact emergencyContact = new EmergencyContact(VALID_NAME, VALID_PHONE);

        // equal objects returns same hash code
        assertEquals(emergencyContact.hashCode(), new EmergencyContact(VALID_NAME, VALID_PHONE).hashCode());

        // different objects return different hash codes
        assertNotEquals(emergencyContact.hashCode(), new EmergencyContact(OTHER_VALID_NAME, VALID_PHONE).hashCode());
        assertNotEquals(emergencyContact.hashCode(), new EmergencyContact(VALID_NAME, OTHER_VALID_PHONE).hashCode());
        assertNotEquals(emergencyContact.hashCode(),
                new EmergencyContact(OTHER_VALID_NAME, OTHER_VALID_PHONE).hashCode());
    }
}
