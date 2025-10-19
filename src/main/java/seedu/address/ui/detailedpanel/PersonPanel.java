package seedu.address.ui.detailedpanel;

import javafx.fxml.FXML;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import seedu.address.model.person.EmergencyContact;
import seedu.address.model.person.Person;
import seedu.address.ui.UiPart;

/**
 * Panel showing detailed information of the person selected.
 */
public class PersonPanel extends UiPart<VBox> {
    private static final String FXML = "detailedpanel/PersonPanel.fxml";
    private static final Image pinImage = new Image("/images/pin.png");
    private static final Image contactImage = new Image("/images/contact.png");
    private static final Image phoneImage = new Image("/images/telephone.png");
    private static final Image emailImage = new Image("/images/email.png");

    @FXML
    private Label name;

    @FXML
    private ImageView pinIcon;
    @FXML
    private ImageView phoneIcon;
    @FXML
    private Label phone;
    @FXML
    private ImageView emailIcon;
    @FXML
    private Label email;
    @FXML
    private VBox emergencyTitle;

    @FXML
    private Label emergencyName;
    @FXML
    private ImageView emergencyNameIcon;
    @FXML
    private Label emergencyPhone;
    @FXML
    private ImageView emergencyPhoneIcon;
    @FXML
    private Label emergencyEmail;
    @FXML
    private ImageView emergencyEmailIcon;

    /**
     * Constructs PersonPanel and links the graphics.
     */
    public PersonPanel() {
        super(FXML);
        pinIcon.setImage(pinImage);
        phoneIcon.setImage(phoneImage);
        emailIcon.setImage(emailImage);
        emergencyNameIcon.setImage(contactImage);
        emergencyPhoneIcon.setImage(phoneImage);
        emergencyEmailIcon.setImage(emailImage);

        name.setGraphic(pinIcon);
        phone.setGraphic(phoneIcon);
        email.setGraphic(emailIcon);
        emergencyName.setGraphic(emergencyNameIcon);
        emergencyPhone.setGraphic(emergencyPhoneIcon);
        emergencyEmail.setGraphic(emergencyEmailIcon);
    }

    /**
     * Populates the panel with details of the selected person.
     */
    public void updateDetails(Person selected) {
        name.setText(selected.getName().fullName);
        pinIcon.setVisible(selected.getPin().value);
        name.setGraphic(pinIcon);
        name.setContentDisplay(ContentDisplay.LEFT);

        phone.setText(selected.getPhone().value);
        phone.setGraphic(phoneIcon);
        phone.setContentDisplay(ContentDisplay.LEFT);

        email.setText(selected.getEmail().value);
        email.setGraphic(emailIcon);
        email.setContentDisplay(ContentDisplay.LEFT);

        if (selected.getEmergencyContact().isPresent()) {
            EmergencyContact ec = selected.getEmergencyContact().get();
            emergencyName.setText(ec.name.fullName);
            emergencyPhone.setText(ec.phone.value);
            emergencyEmail.setText(ec.email.value);
        } else {
            emergencyName.setText("No Emergency Contact detail provided.\n"
                    + "Add one using `edit INDEX ecn/NAME ecp/PHONE`");
            emergencyPhone.setText("");
            emergencyEmail.setText("");
        }
    }
}
