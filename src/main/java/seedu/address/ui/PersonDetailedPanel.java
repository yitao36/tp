package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.EmergencyContact;
import seedu.address.model.person.Person;

/**
 * Panel showing detailed information of the person selected.
 */
public class PersonDetailedPanel extends UiPart<Region> {
    private static final String FXML = "PersonDetailedPanel.fxml";
    private static final Image pinImage = new Image("/images/pin.png");
    private static final Image phoneImage = new Image("/images/telephone.png");
    private static final Image emailImage = new Image("/images/email.png");

    private final Logger logger = LogsCenter.getLogger(PersonDetailedPanel.class);


    @FXML
    private Label name;
    @FXML
    private ImageView pin;
    @FXML
    private ImageView phoneIcon;
    @FXML
    private Label phone;
    @FXML
    private ImageView emailIcon;
    @FXML
    private Label email;
    @FXML
    private Label emergencyName;
    @FXML
    private Label emergencyPhone;
    @FXML
    private Label emergencyEmail;


    public PersonDetailedPanel() {
        super(FXML);
    }

    /**
     * Populates the panel with details of the selected person.
     */
    public void updateDetails(Person selected) {
        if (selected == null) {
            this.getRoot().setVisible(false);
            return;
        }
        this.getRoot().setVisible(true);
        name.setText(selected.getName().fullName);
        pin.setImage(pinImage);
        pin.setVisible(selected.getPin().value);
        name.setGraphic(pin);
        name.setContentDisplay(ContentDisplay.LEFT);

        phoneIcon.setImage(phoneImage);
        phone.setText(selected.getPhone().value);
        phone.setGraphic(phoneIcon);
        phone.setContentDisplay(ContentDisplay.LEFT);

        emailIcon.setImage(emailImage);
        email.setText(selected.getEmail().value);
        email.setGraphic(emailIcon);
        email.setContentDisplay(ContentDisplay.LEFT);

        if (selected.getEmergencyContact().isPresent()) {
            EmergencyContact ec = selected.getEmergencyContact().get();
            emergencyName.setText(ec.name.fullName);
            emergencyPhone.setText(ec.phone.value);
        } else {
            emergencyName.setText("No Emergency Contact detail provided.\n"
                    + "Add one using `edit INDEX ecn/NAME ecp/PHONE`");
            emergencyPhone.setText("");
        }
    }
}
