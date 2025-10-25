package seedu.address.model.role;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Role for a contact in the address book.
 * Guarantees: immutable; role name is valid as declared in {@link #isValidRoleName(String)}
 */
public class Role {
    public static final int PERSON_MAX_ROLES = 3;
    public static final String MESSAGE_CONSTRAINTS = "Role names should be alphanumeric with spaces, "
            + "and each person can have a maximum of 3 roles.";
    public static final String FIND_MESSAGE_CONSTRAINTS = "Supplied substrings for roles should be "
            + "alphanumeric with spaces, or empty.";
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9 ]+$";

    public final String roleName;

    /**
     * Constructs a {@code Role}.
     *
     * @param roleName A valid role name.
     */
    public Role(String roleName) {
        requireNonNull(roleName);
        checkArgument(isValidRoleName(roleName), MESSAGE_CONSTRAINTS);
        this.roleName = roleName;
    }

    /**
     * Returns true if a given string is a valid role name.
     */
    public static boolean isValidRoleName(String test) {
        return test.length() <= 20 && test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid role name.
     */
    public static boolean isValidFindString(String test) {
        return test.isEmpty() || isValidRoleName(test);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Role)) {
            return false;
        }

        Role otherRole = (Role) other;
        return roleName.equals(otherRole.roleName);
    }

    @Override
    public int hashCode() {
        return roleName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return "(Role: " + roleName + ')';
    }
}
