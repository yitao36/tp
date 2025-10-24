package seedu.address.logic;

import javafx.scene.control.TextArea;

/**
 * A static class that handles displaying detailed feedback to the user.
 */
public class MessageCenter {
    private static TextArea resultDisplay;
    private static StringBuilder stringBuilder = new StringBuilder();
    /**
     * Initializes MessageCenter with the ResultDisplay Ui.
     */
    public static void init(TextArea resultDisplay) {
        assert MessageCenter.resultDisplay == null : "Already initialized";
        MessageCenter.resultDisplay = resultDisplay;
    }

    /**
     * Appends the string to the front of the currently built message.
     * Used for showing the main success / error message.
     */
    public static void appendFront(String s) {
        stringBuilder.insert(0, "\n").insert(0, s);
    }

    /**
     * Appends the string to the end of the currently built message.
     * Typically used for showing additional info, such as style warnings.
     */
    public static void appendEnd(String s) {
        stringBuilder.append(s).append("\n");
    }

    /**
     * Shows the currently built message into the text area Ui.
     */
    public static void showFeedback() {
        resultDisplay.setText(stringBuilder.toString());
        stringBuilder.setLength(0);
    }
}
