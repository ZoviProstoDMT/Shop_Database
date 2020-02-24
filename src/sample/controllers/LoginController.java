package sample.controllers;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.animations.Shake;
import sample.dataBase.DataBaseHandler;
import sample.dataBase.User;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField loginPassField;

    @FXML
    private Button loginSignInButton;

    @FXML
    private Button loginSignUpButton;

    @FXML
    void initialize() {
        loginSignUpButton.setOnAction(event -> {
            loginSignUpButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/signUpPage.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage signUpStage = new Stage();
            signUpStage.setScene(new Scene(root));
            signUpStage.showAndWait();
        });
        loginSignInButton.setOnAction(event -> {
            String logintext = loginField.getText().trim();
            String passtext = loginPassField.getText().trim();
            if (!logintext.equals("") && !passtext.equals("")) {
                loginUser(logintext, passtext);
            }
            else System.out.println("Login and Password is empty");

        });
    }

    private void loginUser(String logintext, String passtext) {
        DataBaseHandler dbHandler = new DataBaseHandler();
        User user = new User();
        user.setUsername(logintext);
        user.setPassword(passtext);
        ResultSet result = dbHandler.getUser(user);
        int counter = 0;
        try {
            while (result.next()) {
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (counter >= 1) {
            System.out.println("Success!");
        }
        else {
            Shake loginFieldAnim = new Shake(loginField);
            Shake loginPassFieldAnim = new Shake(loginPassField);
            loginFieldAnim.playAnim();
            loginPassFieldAnim.playAnim();
        }
    }
}