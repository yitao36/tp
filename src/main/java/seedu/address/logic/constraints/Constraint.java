package seedu.address.logic.constraints;

import java.util.function.Predicate;

/**
 * A class representing a constraint on some user input String.
 * If the constraint is violated, the error errorMessage associated with
 */
public class Constraint {
    private final Predicate<String> constraint;
    private final String errorMessage;
    private final boolean isHardConstraint;
    private String val;

    /**
     * Creates a new constraint that has to be satisfied for the user String input.
     */
    public Constraint(Predicate<String> constraint, String errorMessage, boolean isHardConstraint) {
        this.constraint = constraint;
        this.errorMessage = errorMessage;
        this.isHardConstraint = isHardConstraint;
    }

    /**
     * Returns true if the given object fits the constraint, i.e. no violation.
     */
    public boolean test(String input) {
        val = input;
        return constraint.test(input);
    }

    public String getErrorMessage() {
        assert val != null : "test must have occured";

        return String.format(errorMessage, val);
    }

    public boolean isHardConstraint() {
        return isHardConstraint;
    }
}
