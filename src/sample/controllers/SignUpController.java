package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    private TextField signUpCountry;

    @FXML
    private RadioButton signUpMale;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton signUpFemale;

    @FXML
    private ImageView signUpBackIcon;

    @FXML
    void initialize() {
        signUpButton.setOnAction(event -> {
            signUpNewUser();
            System.out.println("SUCCESS REGISTRATION");
        });
    }

    private void signUpNewUser() {
        DataBaseHandler dbhandler = new DataBaseHandler();
        String firstname = signUpFirstname.getText();
        String lastname = signUpLastname.getText();
        String username = signUpUsername.getText();
        String password = signUpPassword.getText();
        String location = signUpCountry.getText();
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
