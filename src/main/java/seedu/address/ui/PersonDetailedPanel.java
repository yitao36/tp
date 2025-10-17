package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel showing detailed information of the person selected.
 */
public class PersonDetailedPanel extends UiPart<Region> {
    private static final String FXML = "PersonDetailedPanel.fxml";
    private static final Image pinImage = new Image("/images/pin.png");

    private final Logger logger = LogsCenter.getLogger(PersonDetailedPanel.class);


    @FXML
    private Label name;
    @FXML
    private ImageView pin;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private VBox emergencyContact;

    public PersonDetailedPanel() {
        super(FXML);
    }

    /**
     * Populates the panel with details of the selected person.
     */
    public void updateDetails(Person selected) {
        name.setText(selected.getName().fullName);
        pin.setImage(pinImage);
        pin.setVisible(selected.getPin().value);
        phone.setText(selected.getPhone().value);
        email.setText(selected.getEmail().value);
    }
}
