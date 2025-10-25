package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.address.logic.MessageCenter;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {
    public static final String STYLE_CLASS_SUCCESS = "success";
    public static final String STYLE_CLASS_ERROR = "error";
    private static final String FXML = "ResultDisplay.fxml";

    @FXML
    private TextArea resultDisplay;

    /**
     * Constructs a new ResultDisplay Ui, and bind it to MessageCenter to receive messages.
     */
    public ResultDisplay() {
        super(FXML);
        MessageCenter.init(this);
    }

    public void setText(String s) {
        resultDisplay.setText(s);
        setStyleSuccess();
    }

    public void setStyleError() {
        this.getRoot().getStyleClass().clear();
        this.getRoot().getStyleClass().add(STYLE_CLASS_ERROR);
    }

    public void setStyleSuccess() {
        this.getRoot().getStyleClass().clear();
        this.getRoot().getStyleClass().add(STYLE_CLASS_SUCCESS);
    }
}
