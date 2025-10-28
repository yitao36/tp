package seedu.address.ui;

import java.util.logging.Logger;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;


/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     * Binds the selected person of the {@code ListView} with the selected person in {@code Model}
     */
    public PersonListPanel(ObservableList<Person> filteredList, ObjectProperty<Person> selectedPerson) {
        super(FXML);
        personListView.setItems(filteredList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        personListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        selectedPerson.addListener((obs, old, newPerson) -> {
            personListView.getSelectionModel().select(newPerson);
        });
        personListView.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, old, newPerson) -> {
                    selectedPerson.setValue(newPerson);
                });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                // Updates the pseudo-class "pin" if pin status changes.
                this.pseudoClassStateChanged(PseudoClass.getPseudoClass("pin"), person.getPin().value);
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());

                personListView.setFixedCellSize(-1);
            }
        }
    }
}
