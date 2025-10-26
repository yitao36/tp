package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.PersonReference;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * Jackson-friendly version of {@link PersonReference}.
 */
public class JsonAdaptedPersonReference {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "PersonReference's %s field is missing!";

    private final String name;
    private final String phone;


    /**
     * Constructs a {@code JsonAdaptedPersonReference} with the given event details.
     */
    @JsonCreator
    public JsonAdaptedPersonReference(@JsonProperty("name") String name, @JsonProperty("phone") String phone) {
        this.name = name;
        this.phone = phone;
    }

    /**
     * Converts a given {@code PersonReference} into this class for Jackson use.
     */
    public JsonAdaptedPersonReference(PersonReference source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code PersonReference} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted event.
     */
    public PersonReference toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(
                   MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);
        return new PersonReference(modelName, modelPhone);
    }
}
