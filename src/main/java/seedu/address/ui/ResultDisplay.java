package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.address.logic.MessageCenter;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";

    @FXML
    private TextArea resultDisplay;

    /**
     * Constructs a new ResultDisplay Ui, and bind it to MessageCenter to receive messages.
     */
    public ResultDisplay() {
        super(FXML);
        MessageCenter.init(resultDisplay);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        MessageCenter.appendFront(feedbackToUser);
        MessageCenter.showFeedback();
    }

}
