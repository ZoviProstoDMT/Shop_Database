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
    private Button customersButton;

    @FXML
    private Button ordersButton;

    @FXML
    private Button productsButton;

    @FXML
    private Button usersButton;

    @FXML
    private Label superuserRoleTextLabel11;

    @FXML
    private Button ExitButton;

    @FXML
    void initialize() {
        ExitButton.setOnAction(event -> openNewScene("/sample/view/loginPage.fxml"));
        usersButton.setOnAction(event -> openNewScene("/sample/view/usersTablePage.fxml"));
        productsButton.setOnAction(event -> openNewScene("/sample/view/productsTablePage.fxml"));
        customersButton.setOnAction(event -> openNewScene("/sample/view/customersTablePage.fxml"));
        ordersButton.setOnAction(event -> openNewScene("/sample/view/ordersTablePage.fxml"));
    }

    public void openNewScene(String window) {
        ExitButton.getScene().getWindow().hide();
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
