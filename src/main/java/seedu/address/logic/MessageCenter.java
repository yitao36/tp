package seedu.address.logic;

import seedu.address.ui.ResultDisplay;

/**
 * A static class that handles displaying detailed feedback to the user.
 */
public class MessageCenter {
    private static ResultDisplay resultDisplay;
    private static StringBuilder stringBuilder = new StringBuilder();

    /** An internal state is used since resultDisplay is not initialized while initializing model. */
    private static boolean isError = false;

    /**
     * Initializes MessageCenter with the ResultDisplay Ui.
     */
    public static void init(ResultDisplay resultDisplay) {
        assert MessageCenter.resultDisplay == null : "Already initialized";
        MessageCenter.resultDisplay = resultDisplay;
        appendFront("CCAmper initialized.");
    }

    /**
     * Appends the string to the front of the currently built message.
     * Used for showing the main success / error message.
     */
    public static void appendFront(String s) {
        if (s.isEmpty()) {
            return;
        }
        stringBuilder.insert(0, "\n").insert(0, s);
    }

    /**
     * Appends the string to the end of the currently built message.
     * Typically used for showing additional info, such as style warnings.
     */
    public static void appendEnd(String s) {
        if (s.isEmpty()) {
            return;
        }
        stringBuilder.append(s).append("\n");
    }

    /**
     * This method is called when something has gone wrong during launch of program or execution of command.
     * Sets the style of the result display to error.
     */
    public static void error() {
        isError = true;
    }

    /**
     * Sets the style of the result display ui to success.
     */
    public static void success() {
        isError = false;
    }

    /**
     * Shows the currently built message into the text area Ui.
     */
    public static void showFeedback() {
        if (isError) {
            resultDisplay.setStyleError();
        } else {
            resultDisplay.setStyleSuccess();
        }
        resultDisplay.setText(stringBuilder.toString());
        stringBuilder.setLength(0);
        isError = false;
    }
}
