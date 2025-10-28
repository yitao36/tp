package seedu.address.testutil;

import static seedu.address.testutil.TypicalEvents.getTypicalEvents;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import seedu.address.model.AddressBook;
import seedu.address.model.event.Event;
import seedu.address.model.event.PersonReference;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} and {@code Event} objects to be used in tests.
 */
public class TypicalAddressBook {

    private TypicalAddressBook() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons and events.
     * The first person attends all events.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        for (Event event : getTypicalEvents()) {
            ab.addEvent(new Event(event));
        }

        // First person attends all events.
        Person firstPerson = ab.getPersonList().get(0);
        for (Event event : ab.getEventList()) {
            event.getAttendance().addPerson(new PersonReference(firstPerson));
        }

        // First event is attended by all people.
        Event firstEvent = ab.getEventList().get(0);
        for (Person person : ab.getPersonList()) {
            if (!person.equals(firstPerson)) {
                firstEvent.getAttendance().addPerson(new PersonReference(person));
            }
        }

        return ab;
    }
}
