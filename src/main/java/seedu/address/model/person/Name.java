package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS = "Names must be less than 50 characters long, should not be blank, "
            + "and names should only contain alphanumeric characters, spaces, and the following characters: "
            + ".,-'()/";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}.,\\-'()][\\p{Alnum}.,\\-'()/ ]*";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        final boolean lengthLessThan50 = test.length() <= 50;
        final boolean hasValidCharacters = test.matches(VALIDATION_REGEX);
        return lengthLessThan50 & hasValidCharacters;
    }

    /**
     * Returns a message containing details about not following the recommend style format.
     * Does not throw an error.
     */
    public static String getStyleWarningMessage(String test) {
        StringBuilder styleWarning = new StringBuilder();

        if (hasConsecutiveSpaces(test)) {
            styleWarning.append(
                    String.format("Style warning: Name `%s` contains multiple consecutive spaces.", test));
        }
        if (!hasBalancedBrackets(test)) {
            styleWarning.append(
                    String.format("Style warning: Name `%s` opening bracket "
                            + "does not have a matching closing bracket.", test));
        }
        if (!isCapitalizedWithLetters(test)) {
            styleWarning.append(
                    String.format("Style warning: Name `%s` does not have proper capitalization or alphabetical name.",
                            test));
        }
        return styleWarning.toString();
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Name)) {
            return false;
        }

        Name otherName = (Name) other;
        return fullName.equals(otherName.fullName);
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

    /**
     * Returns true if the given string has a balanced bracket matching.
     */
    private static boolean hasBalancedBrackets(String s) {
        int openBracketCount = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                openBracketCount++;
            } else if (c == ')') {
                openBracketCount--;
            }
            if (openBracketCount < 0) {
                return false;
            }
        }
        return openBracketCount == 0;
    }

    /**
     * Tests if for each word, the first letter character that appears is capitalized,
     * with following letters that are not capitalized.
     */
    private static boolean isCapitalizedWithLetters(String test) {
        final String regex = "[^a-zA-Z]*[A-Z][^A-Z]*";
        String[] words = test.split(" ");
        return Arrays.stream(words).allMatch(w -> w.matches(regex));
    }

    /**
     * Tests if the string contains two or more consecutive spaces.
     */
    private static boolean hasConsecutiveSpaces(String test) {
        final String regexDoubleSpace = ".* {2}.*";
        return test.matches(regexDoubleSpace);
    }
}
