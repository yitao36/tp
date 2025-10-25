package seedu.address.logic.constraints;

import java.util.List;

/**
 * Contains a list of constraints for a {@code Person}'s name and a checker method for the constraints.
 */
public class NameConstraints {
    private static final Constraint CONSTRAINT_NAME_SIZE = new Constraint((
            name -> name.length() < 50),
            "Name: Must be strictly less than 50 characters!",
            true);

    private static final Constraint CONSTRAINT_NAME_INVALID_CHARACTERS = new Constraint((
            name -> true), // TODO
            "Name contains unexpected characters.",
            false);

    private static final Constraint CONSTRAINT_NAME_BALANCED_BRACKET = new Constraint((
            name -> true), // TODO
            "Name: Opening bracket should be matched with a closing bracket!",
            false);

    private static final List<Constraint> CONSTRAINTS_NAME_ALL = List.of(
            CONSTRAINT_NAME_SIZE, CONSTRAINT_NAME_INVALID_CHARACTERS, CONSTRAINT_NAME_BALANCED_BRACKET);

    /**
     * Checks the given String with the constraints defined in this class.
     */
    public static List<Constraint> check(String name) {
        return CONSTRAINTS_NAME_ALL.stream()
                .filter(c -> !c.test(name))
                .toList();
    }
}
