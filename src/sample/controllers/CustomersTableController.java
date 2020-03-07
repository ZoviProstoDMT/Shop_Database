package sample.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Main;
import sample.dataBase.Const;
import sample.dataBase.DataBaseHandler;
import sample.dataBase.User;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class CustomersTableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<ObservableList> tableUsers;

    @FXML
    private Label superuserRoleTextLabel;

    @FXML
    private TableColumn<?, ?> one;

    @FXML
    private TableColumn<?, ?> two;

    @FXML
    private TableColumn<?, ?> three;

    @FXML
    private TableColumn<?, ?> four;

    @FXML
    private TableColumn<?, ?> five;

    @FXML
    private TableColumn<?, ?> six;

    @FXML
    private TableColumn<?, ?> seven;

    @FXML
    private TableColumn<?, ?> eight;

    @FXML
    private Button ExitButton;

    @FXML
    private ImageView BackIcon;

    @FXML
    void initialize() {
        buildData();
        ExitButton.setOnAction(event -> Main.openNewScene("/sample/view/loginPage.fxml"));
        BackIcon.setOnMouseClicked(event -> Main.openNewScene("/sample/view/superuserPage.fxml"));
    }
    public void buildData() {
        ObservableList<ObservableList> data;
        DataBaseHandler dbHandler = new DataBaseHandler();
        data = FXCollections.observableArrayList();
        try{
            String SQL = "SELECT * from " + Const.CUSTOMERS_TABLE;
            ResultSet rs = dbHandler.getDbconnection().createStatement().executeQuery(SQL);

            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableUsers.getColumns().addAll(col);
            }
            while(rs.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            tableUsers.setItems(data);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

}

