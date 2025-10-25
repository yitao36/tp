package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents an Event in the AddressBook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Event {

    // Identity fields
    private final EventName name;
    private final Duration duration;

    // Data fields
    private final Description description;
    private final Attendance attendance;

    /**
     * Creates an {@code Event} object.
     * @param name name of event.
     * @param duration duration period of event.
     * @param description description of event.
     */
    public Event(EventName name, Duration duration, Description description, Attendance attendance) {
        requireAllNonNull(name, duration, description, attendance);
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.attendance = attendance;
    }

    public EventName getName() {
        return name;
    }

    public Duration getDuration() {
        return duration;
    }

    public Description getDescription() {
        return description;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    /**
     * Returns true if both events have the same name and duration.
     * This defines a weaker notion of equality between two events.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && otherEvent.getName().equals(getName())
                && otherEvent.getDuration().equals(getDuration());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) other;
        return name.equals(otherEvent.name)
                && duration.equals(otherEvent.duration)
                && description.equals(otherEvent.description)
                && attendance.equals(otherEvent.attendance);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, duration, description, attendance);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("duration", duration)
                .add("description", description)
                .add("attendance", attendance)
                .toString();
    }
}
