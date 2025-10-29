package seedu.address.model.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.event.exceptions.DuplicateAttendeeException;
import seedu.address.model.event.exceptions.PersonReferenceNotFoundException;

/**
 * Represents attendance of a set of {@code PersonReference} in the address book for an event.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Attendance {
    public static final String MESSAGE_CONSTRAINTS = "The indexes ";
    private final List<PersonReference> persons = new ArrayList<>();

    public Attendance() {}

    public Attendance(List<PersonReference> persons) {
        this.persons.addAll(persons);
    }

    /** Copy constructor. */
    public Attendance(Attendance attendance) {
        this.persons.addAll(attendance.persons);
    }

    /**
     * Adds a person to the current attendance set.
     * @throws DuplicateAttendeeException if the person is already in the set.
     */
    public void add(PersonReference p) {
        if (persons.contains(p)) {
            throw new DuplicateAttendeeException();
        }
        persons.add(p);
    }

    public void addAll(Collection<PersonReference> p) {
        persons.addAll(p);
    }

    public boolean hasPerson(PersonReference p) {
        return persons.contains(p);
    }

    /**
     * Removes a person from the current attendance set.
     * @throws PersonReferenceNotFoundException if the person does not exist in the set.
     */
    public void remove(PersonReference p) {
        if (!persons.contains(p)) {
            throw new PersonReferenceNotFoundException();
        }
        this.persons.remove(p);
    }

    /**
     * Removes a person from the current attendance set at the given index.
     */
    public void remove(int index) {
        persons.remove(index);
    }

    public void removeAll(Collection<PersonReference> personsToRemove) {
        persons.removeAll(personsToRemove);
    }

    /**
     * Removes everyone from the current attendance set.
     */
    public void clear() {
        persons.clear();
    }

    public boolean contains(PersonReference p) {
        return persons.contains(p);
    }

    public List<PersonReference> asUnmodifiableList() {
        return Collections.unmodifiableList(persons);
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
