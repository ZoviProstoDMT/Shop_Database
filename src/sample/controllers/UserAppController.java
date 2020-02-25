package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class UserAppController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label userRoleTextLabel;

    @FXML
    private Button userCustomers;

    @FXML
    private Button userProducts;

    @FXML
    private Label userRoleTextLabel1;

    @FXML
    private Button userExitButton;

    @FXML
    void initialize() {
        userExitButton.setOnAction(event -> openNewScene("/sample/view/loginPage.fxml"));
    }
    public void openNewScene(String window) {
        userExitButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setResizable(false);
        newStage.show();
    }
}
