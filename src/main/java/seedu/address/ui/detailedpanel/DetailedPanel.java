package seedu.address.ui.detailedpanel;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.ui.UiPart;

/**
 * Panel showing detailed information of the person selected.
 */
public class DetailedPanel extends UiPart<StackPane> {
    private static final String FXML = "detailedpanel/DetailedPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(DetailedPanel.class);

    // Internal copy
    private final HelpPanel helpPanel = new HelpPanel();
    private final PersonPanel personPanel = new PersonPanel();
    private boolean isHelp = true;

    @FXML
    private VBox helpPanelPlaceholder;
    @FXML
    private VBox personPanelPlaceholder;

    /**
     * Constructs a container panel that initially shows the help panel.
     */
    public DetailedPanel() {
        super(FXML);
        helpPanelPlaceholder.getChildren().add(helpPanel.getRoot());
        personPanelPlaceholder.getChildren().add(personPanel.getRoot());

        // Workaround to get personPanel height to fill up the whole container
        personPanel.getRoot().prefHeightProperty().bind(personPanelPlaceholder.heightProperty());
        personPanelPlaceholder.setVisible(false);
    }

    /**
     * Populates the panel with details of the selected person.
     */
    public void updateDetails(Person selected) {
        if (selected == null) {
            showHelp();
        } else {
            closeHelp();
            personPanel.updateDetails(selected);
        }
    }

    /**
     * Shows a helpful message on initialization of AddressBook, or if the person list is empty.
     */
    public void showHelp() {
        if (isHelp) {
            return;
        }
        isHelp = true;
        personPanelPlaceholder.setVisible(false);
        helpPanelPlaceholder.setVisible(true);
    }

    /**
     * Closes the help message upon selection of person.
     */
    public void closeHelp() {
        if (!isHelp) {
            return;
        }
        isHelp = false;
        personPanelPlaceholder.setVisible(true);
        helpPanelPlaceholder.setVisible(false);
    }
}
