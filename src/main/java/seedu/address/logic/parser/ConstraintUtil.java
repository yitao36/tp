package seedu.address.logic.parser;

import java.util.List;

import seedu.address.logic.Constraint;
import seedu.address.model.person.Person;

/**
 * Contains definitions and methods for the various constraints of objects.
 */
public class ConstraintUtil {
    private static final Constraint CONSTRAINT_NAME_TOO_LONG = new Constraint((
            name -> name.length() <= 100),
            "Name for a person must be strictly less than 100 characters!",
            true);

    private static final Constraint CONSTRAINT_NAME_INVALID_CHARACTERS = new Constraint((
            name -> true), // TODO
            "Name: %s contains unexpected characters.",
            false);



    private static final List<Constraint> CONSTRAINTS_NAME_ALL = List.of(
            CONSTRAINT_NAME_TOO_LONG, CONSTRAINT_NAME_INVALID_CHARACTERS);

    public static List<Constraint> getViolatedNameConstraints(String n) {
        return CONSTRAINTS_NAME_ALL.stream()
                .filter(c -> !c.test(n))
                .toList();
    }

    /**
     * Returns a list of constraints that have been violated.
     */
    public static List<Constraint> getViolatedPersonConstraints(Person p) {
        return CONSTRAINTS_NAME_ALL.stream()
                .filter(c -> !c.test(p.getName().fullName))
                .toList();
    }

    private static List<Constraint> getHardConstraints(List<Constraint> constraints) {
        return constraints.stream().filter(Constraint::isHardConstraint).toList();
    }

    private static List<Constraint> getNonHardConstraints(List<Constraint> constraints) {
        return constraints.stream().filter(c -> !c.isHardConstraint()).toList();
    }

    public static boolean isSevere(List<Constraint> constraints) {
        return !getHardConstraints(constraints).isEmpty();
    }

    public static String getMessage(List<Constraint> constraints) {
        String errorMessages = getHardConstraints(constraints).stream().reduce(
                "", (p, n) -> p + n.getMessage() + "\n", (p, n) -> p + n
        );

        String warningMessages = getNonHardConstraints(constraints).stream().reduce(
                "", (p, n) -> p + n.getMessage() + "\n", (p, n) -> p + n
        );
        return errorMessages + warningMessages;
    }
}
