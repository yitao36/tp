package seedu.address.model.event;

import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.event.exceptions.DuplicateAttendeeException;
import seedu.address.model.person.Person;

/**
 * Represents attendance of a list of {@code Person} in the address book for an event.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Attendance {
    public static final String MESSAGE_CONSTRAINTS = "The indexes ";
    private final Set<Person> persons;

    public Attendance(Set<Person> persons) {
        this.persons = persons;
    }

    /**
     * Adds a person to the current attendance set.
     * @throws DuplicateAttendeeException if the person is already in the set.
     */
    public void addPerson(Person p) {
        if (persons.contains(p)) {
            throw new DuplicateAttendeeException();
        }
        persons.add(p);
    }

    public boolean hasPerson(Person p) {
        return persons.contains(p);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Attendance)) {
            return false;
        }

        Attendance otherAttendance = (Attendance) other;
        return this.persons.equals(otherAttendance.persons);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(persons);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .toString();
    }
}
