package seedu.address.logic.constraints;

import java.util.ArrayList;
import java.util.List;

/**
 * Global class, handles the logic for checking constraints.
 */
public class ConstraintManager {
    private static final List<Constraint> constraints = new ArrayList<>();

    private ConstraintManager() {}

    public static void addConstraints(List<Constraint> constraints) {
        ConstraintManager.constraints.addAll(constraints);
    }

    public static boolean isSevere() {
        return ConstraintUtil.isSevere(constraints);
    }

    public static String getMessage() {
        return ConstraintUtil.getMessage(constraints);
    }

    public static void clear() {
        constraints.clear();
    }
}
