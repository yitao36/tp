package seedu.address.logic.constraints;

import java.util.List;

import seedu.address.model.person.Email;

/**
 * Contains definitions and methods for the various constraints of objects.
 */
public class ConstraintUtil {
    private static final Constraint CONSTRAINT_EMAIL_REGEX = new Constraint(
            email -> email.matches(Email.VALIDATION_REGEX),
            "Email must match the format username@mailserver.domain",
            true
    );

    private static final Constraint CONSTRAINT_EMAIL_SIZE = new Constraint(
            email -> email.length() < 50,
            "Email must be strictly less than 50 characters!",
            true
    );

    public static List<Constraint> getHardConstraints(List<Constraint> constraints) {
        return constraints.stream().filter(Constraint::isHardConstraint).toList();
    }

    public static List<Constraint> getNonHardConstraints(List<Constraint> constraints) {
        return constraints.stream().filter(c -> !c.isHardConstraint()).toList();
    }

    public static boolean isSevere(List<Constraint> constraints) {
        return !getHardConstraints(constraints).isEmpty();
    }

    public static String getMessage(List<Constraint> constraints) {
        String errorMessages = getHardConstraints(constraints).stream().reduce(
                "", (p, n) -> p + "Error: "
                        + n.getErrorMessage() + "\n", (p, n) -> p + n
        );

        String warningMessages = getNonHardConstraints(constraints).stream().reduce(
                "", (p, n) -> p + "Warning: "
                        + n.getErrorMessage() + "\n", (p, n) -> p + n
        );
        return errorMessages + warningMessages;
    }
}
