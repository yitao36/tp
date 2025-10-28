package seedu.address.model;

import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

/**
 * Class to store the details of the event/person we are zooming in on, if any.
 */
public class ZoomIn {
    private ZoomInType zoomInType;
    private Person targetPerson;
    private Event targetEvent;

    /**
     * Constructs a default NONE ZoomIn.
     */
    public ZoomIn() {
        this.zoomInType = ZoomInType.NONE;
        this.targetPerson = null;
        this.targetEvent = null;
    }

    /**
     * Constructs a ZoomIn with the given parameters.
     */
    public ZoomIn(ZoomInType zoomInType, Person person, Event event) {
        this.zoomInType = zoomInType;

        // Some checks to make sure the zoom in type is consistent
        switch (zoomInType) {
        case PERSON -> {
            assert (person != null);
            assert (event == null);
        }
        case EVENT -> {
            assert (event != null);
            assert (person == null);
        }
        case NONE -> {
            assert (event == null);
            assert (person == null);
        }
        default -> { }
        }

        this.targetPerson = person;
        this.targetEvent = event;
    }

    /**
     * Returns type of ZoomIn.
     */
    public ZoomInType getType() {
        return zoomInType;
    }

    /**
     * Returns target person of ZoomIn.
     */
    public Person getTargetPerson() {
        return targetPerson;
    }

    /**
     * Returns target event of ZoomIn.
     */
    public Event getTargetEvent() {
        return targetEvent;
    }
}
