package seedu.address.model.event;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.event.exceptions.DuplicateAttendeeException;
import seedu.address.model.event.exceptions.PersonReferenceNotFoundException;

/**
 * Represents attendance of a set of {@code PersonReference} in the address book for an event.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Attendance {
    public static final String MESSAGE_CONSTRAINTS = "The indexes ";
    private final Set<PersonReference> persons = new HashSet<>();

    public Attendance() {}

    public Attendance(Set<PersonReference> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Adds a person to the current attendance set.
     * @throws DuplicateAttendeeException if the person is already in the set.
     */
    public void addPerson(PersonReference p) {
        if (persons.contains(p)) {
            throw new DuplicateAttendeeException();
        }
        persons.add(p);
    }

    public boolean hasPerson(PersonReference p) {
        return persons.contains(p);
    }

    /**
     * Removes a person from the current attendance set.
     * @throws PersonReferenceNotFoundException if the person does not exist in the set.
     */
    public void removePerson(PersonReference p) {
        if (!persons.contains(p)) {
            throw new PersonReferenceNotFoundException();
        }
        this.persons.remove(p);
    }

    public boolean contains(PersonReference p) {
        return persons.contains(p);
    }

    public Set<PersonReference> asUnmodifiableList() {
        return Collections.unmodifiableSet(persons);
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
