package seedu.address.logic;

import java.util.function.Predicate;

/**
 * An abstract class representing a constraint on some user input String.
 */
public class Constraint {
    private final Predicate<String> constraint;
    private final String message;
    private final boolean isHardConstraint;
    private String val;

    /**
     * TODO
     */
    public Constraint(Predicate<String> constraint, String message, boolean isHardConstraint) {
        this.constraint = constraint;
        this.message = message;
        this.isHardConstraint = isHardConstraint;
    }

    /**
     * Returns true if the given object fits the constraint, i.e. no violation.
     */
    public boolean test(String input) {
        val = input;
        return constraint.test(input);
    }

    public String getMessage() {
        assert val != null : "test must have occured";

        return String.format(message, val);
    }

    public boolean isHardConstraint() {
        return isHardConstraint;
    }
}
