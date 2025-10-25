package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Represents a reference to a {@code Person} in the model, where a person is guaranteed to be unique
 * as defined by {@code Person#isSamePerson}.
 * Guarantees: details are present and not null, field values are validated, immutable.
 *
 * @see Person#isSamePerson
 */
public class PersonReference {
    // Identity fields
    private final Name name;
    private final Phone phone;

    /**
     * Every field must be present and not null.
     */
    public PersonReference(Name name, Phone phone) {
        requireAllNonNull(name, phone);
        this.name = name;
        this.phone = phone;
    }

    /**
     * Constructs a reference to the given person. Every field must be present and not null.
     */
    public PersonReference(Person p) {
        requireAllNonNull(p);
        this.name = p.getName();
        this.phone = p.getPhone();
    }

    public Phone getPhone() {
        return phone;
    }

    public Name getName() {
        return name;
    }
    /**
     * Returns true if both person references have the same identity fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonReference)) {
            return false;
        }

        PersonReference otherPerson = (PersonReference) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .toString();
    }
}
