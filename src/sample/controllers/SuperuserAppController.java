package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SuperuserAppController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PieChart suPieChart;

    @FXML
    private Label superuserRoleTextLabel;

    @FXML
    private Label superuserRoleTextLabel1;

    @FXML
    private Button suOrdersAdd;

    @FXML
    private Button suOrdersDell;

    @FXML
    private Button suOrdersFind;

    @FXML
    private Button suProductsAdd;

    @FXML
    private Button suProductsDell;

    @FXML
    private Button suProductsFind;

    @FXML
    private Button suUsersFind;

    @FXML
    private Button suUsersDell;

    @FXML
    private Button suUsersAdd;

    @FXML
    private Label superuserRoleTextLabel11;

    @FXML
    private Button superuserExitButton;

    @FXML
    void initialize() {
        superuserExitButton.setOnAction(event -> openNewScene("/sample/view/loginPage.fxml"));
    }

    public void openNewScene(String window) {
        superuserExitButton.getScene().getWindow().hide();
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
