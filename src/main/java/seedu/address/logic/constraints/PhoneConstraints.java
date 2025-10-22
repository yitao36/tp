package seedu.address.logic.constraints;

import java.util.List;

/**
 * Contains a list of constraints for a {@code Person}'s phone number and a checker method for the constraints.
 */
public class PhoneConstraints {
    private static final Constraint CONSTRAINT_PHONE_SIZE = new Constraint((
            phone -> phone.length() < 24),
            "Phone number must be strictly less than 24 characters!",
            true
    );

    private static final Constraint CONSTRAINT_PHONE_DEFAULT_SINGAPORE = new Constraint(
            phone -> {
                char firstDigit = phone.charAt(0);
                if (firstDigit == '3' || firstDigit == '6' || firstDigit == '8'
                        || firstDigit == '9' || firstDigit == '+') {
                    return true;
                } else {
                    return false;
                }
            },
            "Phone number should start with one of +/3/6/8/9. E.g. `+60 917 1874 132`",
            false
    );

    private static final List<Constraint> CONSTRAINT_PHONE_ALL = List.of(
            CONSTRAINT_PHONE_SIZE, CONSTRAINT_PHONE_DEFAULT_SINGAPORE
    );

    /**
     * Checks the given String with the constraints defined in this class.
     */
    public static List<Constraint> check(String phone) {
        return CONSTRAINT_PHONE_ALL.stream()
                .filter(c -> !c.test(phone))
                .toList();
    }
}
