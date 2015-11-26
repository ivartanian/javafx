package com.vartanian.javafx.adress.view;

import com.vartanian.javafx.MainApp;
import com.vartanian.javafx.adress.model.Person;
import com.vartanian.javafx.adress.services.repository.PersonService;
import com.vartanian.javafx.adress.services.repository.PersonServiceImpl;
import com.vartanian.javafx.adress.util.DateUtil;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

/**
 * Created by vartanian on 17.11.2015.
 */
public class PersonOverviewController {

    private PersonService personService;

    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        personService = new PersonServiceImpl();

        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(param -> param.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(param -> param.getValue().lastNameProperty());

        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(FXCollections.observableArrayList(personService.getAllPersons()));
    }

    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     *
     * @param person the person or null
     */
    private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
            personService.deletePerson(1L);
        } else {
            // Nothing selected.
            showAlert();
        }
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No Person Selected");
        alert.setContentText("Please select a person in the table.");

        alert.showAndWait();
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
            showAlert();
        }
    }

    @FXML
    public void handleMouseEntered(Event event) {
        Stop[] stops = new Stop[] {new Stop(0, Color.RED),new Stop(1, Color.YELLOW)};
        LinearGradient lg = new LinearGradient(0, 0, 0.25, 0.25, true, CycleMethod.REFLECT, stops);
        ColorInput colorInput = new ColorInput();
        colorInput.setWidth(97);
        colorInput.setHeight(32);
        colorInput.setPaint(lg);

        //---------BLEND---------
//        final Blend effect = new Blend();
//        effect.setMode(BlendMode.LIGHTEN);
//        effect.setBottomInput(colorInput);

        //---------BLOOM---------
//        final Bloom effect=new Bloom();
//        effect.setThreshold(0.3);

        //---------GLOW---------
//        Glow effect=new Glow();
//        effect.setLevel(0.9);

        //---------DROPSHADOW---------
//        final DropShadow effect=new DropShadow();
//        effect.setColor(Color.OLIVE);

        //---------SHADOW---------
//        Shadow effect=new Shadow();
//        effect.setColor(Color.web("#a0522d"));

        //---------INNERSHADOW---------
//        InnerShadow effect=new InnerShadow();
//        effect.setColor(Color.OLIVE);

        //---------BOXBLUR---------
//        BoxBlur effect = new BoxBlur();

        //---------MONITORBLUR---------
//        final MotionBlur effect = new MotionBlur();
//        effect.setRadius(15.0);
//        effect.setAngle(90.0);

        //---------LIGHTING---------
        final Light.Distant lightDistant = new Light.Distant();
        final Light.Point lightPoint = new Light.Point();
        final Light.Spot lightSpot = new Light.Spot();
        final Lighting effect = new Lighting();
        effect.setLight(lightDistant);

        Button button = (Button) event.getTarget();
        button.setEffect(effect);
    }

    @FXML
    public void handleMouseExited(Event event) {
        Button button = (Button) event.getTarget();
        button.setEffect(null);
    }
}