package seedu.address.testutil;

import seedu.address.model.event.Description;
import seedu.address.model.event.Duration;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {

    public static final String DEFAULT_NAME = "Meeting";
    public static final String DEFAULT_DURATION = "1/10/2025";
    public static final String DEFAULT_DESCRIPTION = "routine meeting";

    private EventName name;
    private Duration duration;
    private Description description;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        name = new EventName(DEFAULT_NAME);
        duration = new Duration(DEFAULT_DURATION);
        description = new Description(DEFAULT_DESCRIPTION);
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        name = eventToCopy.getName();
        duration = eventToCopy.getDuration();
        description = eventToCopy.getDescription();
    }

    /**
     * Sets the {@code EventName} of the {@code Event} that we are building.
     */
    public EventBuilder withName(String name) {
        this.name = new EventName(name);
        return this;
    }

    /**
     * Sets the {@code Duration} of the {@code Event} that we are building.
     */
    public EventBuilder withDuration(String address) {
        this.duration = new Duration(address);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Event} that we are building.
     */
    public EventBuilder withDescription(String phone) {
        this.description = new Description(phone);
        return this;
    }

    public Event build() {
        return new Event(name, duration, description);
    }

}
