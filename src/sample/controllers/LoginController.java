package sample.controllers;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
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
    private Button ExitButton;

    @FXML
    void initialize() {
        loginSignUpButton.setOnAction(event -> Main.openNewScene("/sample/view/signUpPage.fxml"));
        loginSignInButton.setOnAction(event -> {
            String logintext = loginField.getText().trim();
            String passtext = loginPassField.getText().trim();
            loginUser(logintext, passtext);
        });
    }

    private void loginUser(String logintext, String passtext) {
        DataBaseHandler dbHandler = new DataBaseHandler();
        User user = new User();
        user.setUsername(logintext);
        user.setPassword(passtext);
        ResultSet result = dbHandler.getUser(user);
        ResultSet resultRole = dbHandler.getUser(user);
        int counter = 0;
        try {
            while (result.next()) {
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (counter != 0) {
            String userRole = null;
            try {
                if (resultRole.next())
                    userRole = resultRole.getString("role");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Роль авторизованного пользователя: " + userRole);
            if (userRole.equals("Superuser")) {
                User.currentUserRole = "Superuser";
                Main.openNewScene("/sample/view/superuserPage.fxml");
            } else if (userRole.equals("Low")) {
                User.currentUserRole = "Low";
                Main.openNewScene("/sample/view/userPage.fxml");
            } else {
                Shake loginFieldAnim = new Shake(loginField);
                Shake loginPassFieldAnim = new Shake(loginPassField);
                loginFieldAnim.playAnim();
                loginPassFieldAnim.playAnim();
            }
        }
    }
}