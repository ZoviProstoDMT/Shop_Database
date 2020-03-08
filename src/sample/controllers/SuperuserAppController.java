package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Main;
import sample.dataBase.Const;
import sample.dataBase.DataBaseHandler;

public class SuperuserAppController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PieChart PieChart;

    @FXML
    private Label TextWithChanges;

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
        buildPieChart();
        ExitButton.setOnAction(event -> Main.openNewScene("/sample/view/loginPage.fxml"));
        usersButton.setOnAction(event -> Main.openNewScene("/sample/view/usersTablePage.fxml"));
        productsButton.setOnAction(event -> Main.openNewScene("/sample/view/productsTablePage.fxml"));
        customersButton.setOnAction(event -> Main.openNewScene("/sample/view/customersTablePage.fxml"));
        ordersButton.setOnAction(event -> Main.openNewScene("/sample/view/ordersTablePage.fxml"));
    }

    public void buildPieChart() {
        DataBaseHandler dbhandler = new DataBaseHandler();
        String query = "SELECT productname, productcount FROM products;";
        ObservableList<PieChart.Data> piechartdata;
        piechartdata = FXCollections.observableArrayList();
        ResultSet rs = null;
        try {
            rs = dbhandler.getDbconnection().createStatement().executeQuery(query);
            while (rs.next()) {
                piechartdata.add(new PieChart.Data(rs.getString("productname"), rs.getDouble("productcount")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        PieChart.setData(piechartdata);
        PieChart.setVisible(true);
    }
}