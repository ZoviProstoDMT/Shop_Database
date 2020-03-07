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
import sample.Main;

public class UserAppController {

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
    private Label superuserRoleTextLabel11;

    @FXML
    private Button customersButton;

    @FXML
    private Button ordersButton;

    @FXML
    private Button productsButton;

    @FXML
    private Button ExitButton;

    @FXML
    void initialize() {
        ExitButton.setOnAction(event -> Main.openNewScene("/sample/view/loginPage.fxml"));
        productsButton.setOnAction(event -> Main.openNewScene("/sample/view/productsTablePage.fxml"));
        customersButton.setOnAction(event -> Main.openNewScene("/sample/view/customersTablePage.fxml"));
        ordersButton.setOnAction(event -> Main.openNewScene("/sample/view/ordersTablePage.fxml"));
    }
}
