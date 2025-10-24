package seedu.address.model.person;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Person's emergency contact in the address book.
 * Guarantees: immutable; fields name, phone are valid as declared in {@link Name#isValidName},
 * {@link Phone#isValidPhone} respectively.
 */
public class EmergencyContact {
    public static final String MESSAGE_CONSTRAINTS = "The emergency contact fields (`ecn` and `ecp`) must be either "
            + "both provided or not at all.";

    public final Name name;
    public final Phone phone;

    /**
     * Constructs a {@code EmergencyContact}.
     *
     * @param name  A valid name.
     * @param phone A valid phone number.
     */
    public EmergencyContact(String name, String phone) {
        this.name = new Name(name);
        this.phone = new Phone(phone);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("name", name).add("phone", phone).toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EmergencyContact)) {
            return false;
        }

        EmergencyContact otherContact = (EmergencyContact) other;
        return name.equals(otherContact.name) && phone.equals(otherContact.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone);
    }

}
