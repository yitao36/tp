package seedu.address.model;

import javafx.beans.value.ObservableObjectValue;
import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns the selected person in the person list.
     */
    ObservableObjectValue<Person> getSelectedPerson();

    /**
     * Returns an unmodifiable view of the events list.
     * This list will not contain any duplicate events.
     */
    ObservableList<Event> getEventList();

    /**
     * Returns the selected event in the event list.
     */
    ObservableObjectValue<Event> getSelectedEvent();

    /**
     * Checks if the address book is valid.
     * It is valid if all the person references in the event attendances properly reference an existing person
     * in the person list.
     */
    void isValidAddressBook() throws DataLoadingException;
}
