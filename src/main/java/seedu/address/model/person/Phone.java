package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Stack;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {

    // Note: Error message has greater severity than warning message.
    public static final String ERROR_MESSAGE_FIST_CHARACTER = "Expected Singapore phone number "
            + "that starts with 3/6/8/9. \n";
    public static final String ERROR_MESSAGE_LOWER_LIMIT = "Expected 8 digit Singapore phone number \n";
    public static final String WARNING_MESSAGE_UPPER_LIMIT = "Phone number keyed in is more than 8 characters long \n";
    public static final String WARNING_MESSAGE_NON_NUMERIC = "This phone number contains non-numeric characters."
            + "\n";
    public static final String WARNING_MESSAGE_IMPROPER_BRACKETS = "There is an improper use of brackets. \n";
    public static final String MESSAGE_CONSTRAINTS = "1. "
            + ERROR_MESSAGE_LOWER_LIMIT
            + "2. "
            + ERROR_MESSAGE_FIST_CHARACTER;

    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;
    }

    /**
     * Removes spaces and hyphens to give raw format of number.
     *
     * @param test Phone number that potentially have spaces and hyphens.
     * @return Phone number with spaces and hyphens removed.
     */
    public static String convertRawFormat(String test) {
        return test.replaceAll("[\\s-]", "");
    }

    /**
     * Checks if the phone number given starts with 8 numerical digits.
     * Takes into account edge cases such as '98769876 (HP)',
     * where there may be non-numerical digits that follows.
     *
     * @param test Phone number to be tested.
     * @return The conditional check of whether the phone number given starts with 8 numerical digits.
     */
    public static boolean hasEightNumber(String test) {
        test = convertRawFormat(test);
        if (test.length() < 8 || test == null) {
            return false;
        }
        return test.substring(0, 8).chars().allMatch(Character::isDigit);
    }

    private static boolean containOnlyNumbers(String test) {
        return test.chars().allMatch(Character::isDigit);
    }

    private static boolean lengthGreaterThanEight(String test) {
        test = convertRawFormat(test);
        return test.length() > 8;
    }

    private static boolean validFirstDigit(String test) {
        test = convertRawFormat(test);
        if (test.length() == 0) {
            return false;
        }
        int firstDigit = test.charAt(0);
        return firstDigit == '3' || firstDigit == '6' || firstDigit == '8' || firstDigit == '9';
    }

    /**
     * Creates error message depending on the issue(s) of phone number provided.
     *
     * @param test Phone number provided.
     * @return A description of the issue(s) of the phone number provided.
     */
    public static String createErrorMessage(String test) {
        test = convertRawFormat(test);
        String errorMessage = String.format("Phone number %s is invalid\n", test);
        int counter = 1;
        if (!hasEightNumber(test)) {
            errorMessage += counter + ". " + ERROR_MESSAGE_LOWER_LIMIT;
            counter += 1;
        }
        if (!validFirstDigit(test)) {
            errorMessage += counter + ". " + ERROR_MESSAGE_FIST_CHARACTER;
        }
        return errorMessage;
    }

    /**
     * Returns true if phone number needs to have warning.
     *
     * @param test Phone number provided.
     * @return The conditional check of whether the phone number has warnings.
     */
    public static boolean hasWarning(String test) {
        test = convertRawFormat(test);
        if (!containOnlyNumbers(test) || lengthGreaterThanEight(test)) {
            return true;
        }
        return false;
    }

    private static boolean hasBracket(String input) {
        return input.contains("(")
                || input.contains(")")
                || input.contains("[")
                || input.contains("]")
                || input.contains("{")
                || input.contains("}");
    }

    private static boolean isValidUseOfBracket(String input) {
        if (!hasBracket(input)) {
            // vacuously true
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (char character : input.toCharArray()) {
            if (isOpeningBracket(character)) {
                stack.push(character);
            }
            if (!isClosingBracket(character)) {
                continue;
            }
            if (stack.isEmpty()) {
                return false;
            }
            char previousBracket = stack.pop();
            if (!isValidBracketMatching(previousBracket, character)) {
                return false;
            }
        }

        return stack.isEmpty();
    }

    private static boolean isOpeningBracket(char character) {
        return character == '(' || character == '{' || character == '[';
    }

    private static boolean isClosingBracket(char character) {
        return character == ')' || character == '}' || character == ']';
    }

    private static boolean isValidBracketMatching(char previousBracket, char nextBracket) {
        if (nextBracket == ')' && previousBracket == '(') {
            return true;
        }
        if (nextBracket == ']' && previousBracket == '[') {
            return true;
        }
        if (nextBracket == '}' && previousBracket == '{') {
            return true;
        }
        return false;
    }

    /**
     * Creates warning message depending on the issue(s) of phone number provided.
     *
     * @param test Phone number provided.
     * @return A compilation of the warnings pertaining to the phone number provided.
     */
    public static String createWarningMessage(String test) {
        test = convertRawFormat(test);
        String warningMessage = String.format("Note (Phone number %s may have issues):\n", test);

        int counter = 1;
        if (lengthGreaterThanEight(test)) {
            warningMessage += counter + ". " + WARNING_MESSAGE_UPPER_LIMIT;
            counter += 1;
        }
        if (!containOnlyNumbers(test)) {
            warningMessage += counter + ". " + WARNING_MESSAGE_NON_NUMERIC;
            counter += 1;
        }
        if (!isValidUseOfBracket(test)) {
            warningMessage += counter + ". " + WARNING_MESSAGE_IMPROPER_BRACKETS;
        }
        return warningMessage;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        test = convertRawFormat(test);
        if (test.length() == 0) {
            return false;
        }
        if (!hasEightNumber(test)) {
            return false;
        }
        if (!validFirstDigit(test)) {
            return false;
        }
        return true;
    }



    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Phone)) {
            return false;
        }

        Phone otherPhone = (Phone) other;
        return value.equals(otherPhone.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
