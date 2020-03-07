package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Main;
import sample.dataBase.DataBaseHandler;
import sample.dataBase.User;

public class SignUpController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField signUpFirstname;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField signUpLastname;

    @FXML
    private TextField signUpUsername;

    @FXML
    private TextField signUpPassword;

    @FXML
    private ComboBox<String> signUpCountry;

    @FXML
    private RadioButton signUpMale;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton signUpFemale;

    @FXML
    private ImageView signUpBackIcon;

    ObservableList<String> counties = FXCollections.observableArrayList("Россия", "Не Россия", "Уганда");

    @FXML
    void initialize() {
        signUpButton.setOnAction(event -> {
            signUpNewUser();
            System.out.println("SUCCESS REGISTRATION");
            Main.openNewScene("/sample/view/loginPage.fxml");
        });
        signUpBackIcon.setOnMouseClicked(event -> Main.openNewScene("/sample/view/loginPage.fxml"));
        signUpCountry.setValue("Россия");
        signUpCountry.setItems(counties);
    }

    private void signUpNewUser() {
        DataBaseHandler dbhandler = new DataBaseHandler();
        String firstname = signUpFirstname.getText();
        String lastname = signUpLastname.getText();
        String username = signUpUsername.getText();
        String password = signUpPassword.getText();
        String location = String.valueOf(signUpCountry.getValue());
        String gender = "";
        if (signUpMale.isSelected()) {
            gender = "Мужской";
        }
        else {
            gender = "Женский";
        }
        User user = new User(firstname, lastname, username, password, location, gender);
        dbhandler.signUpUser(user);
    }
}
