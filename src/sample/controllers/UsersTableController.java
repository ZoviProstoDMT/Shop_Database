package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Main;
import sample.dataBase.Const;
import sample.dataBase.DataBaseHandler;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class UsersTableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label TextWithChanges;

    @FXML
    private TableView<ObservableList> tableUsers;

    @FXML
    private ImageView AddUser;

    @FXML
    private ImageView DeleteUser;

    @FXML
    private Label superuserRoleTextLabel1;

    @FXML
    private Label superuserRoleTextLabel11;

    @FXML
    private Label superuserRoleTextLabel111;

    @FXML
    private ImageView UpRole;

    @FXML
    private ImageView FindUser;

    @FXML
    private ImageView DownRole;

    @FXML
    private TextField IdField;

    @FXML
    private Label superuserRoleTextLabel1111;

    @FXML
    private TextField FirstnameField;

    @FXML
    private TextField UsernameField;

    @FXML
    private Button ExitButton;

    @FXML
    private ImageView BackIcon;

    @FXML
    void initialize() {
        String SQL = "SELECT * from " + Const.USER_TABLE;
        buildData(SQL);
        ExitButton.setOnAction(event -> Main.openNewScene("/sample/view/loginPage.fxml"));
        BackIcon.setOnMouseClicked(event -> Main.openNewScene("/sample/view/superuserPage.fxml"));
        AddUser.setOnMouseClicked(event -> Main.openNewScene("/sample/view/addUser.fxml"));
        FindUser.setOnMouseClicked(event -> {
            String idfield = IdField.getText().trim();
            String namedield = FirstnameField.getText().trim();
            String usernamefield = UsernameField.getText().trim();
            findUser(idfield,namedield,usernamefield);
        });
        UpRole.setOnMouseClicked(event -> {
                DataBaseHandler dbhandler = new DataBaseHandler();
                ObservableList<ObservableList> list = buildData(SQL);
                String name = list.get(tableUsers.getSelectionModel().getSelectedIndex()).get(3).toString();
                System.out.println("Повышение роли для пользователя с юзернеймом: " + name);
                dbhandler.changeUserRole(name, "Superuser");
                TextWithChanges.setText("Роль пользователя " + name + " успешно повышена");
                Main.openNewScene("/sample/view/usersTablePage.fxml");
        });
        DownRole.setOnMouseClicked(event -> {
            DataBaseHandler dbhandler = new DataBaseHandler();
            ObservableList<ObservableList> list = buildData(SQL);
            String name = list.get(tableUsers.getSelectionModel().getSelectedIndex()).get(3).toString();
            System.out.println("Понижение роли для пользователя с юзернеймом: " + name);
            dbhandler.changeUserRole(name, "Low");
            Main.openNewScene("/sample/view/usersTablePage.fxml");
            TextWithChanges.setText("Роль пользователя " + name + " успешно понижена");
        });
        DeleteUser.setOnMouseClicked(event -> {
                DataBaseHandler dbhandler = new DataBaseHandler();
                ObservableList<ObservableList> list = buildData(SQL);
                String name = list.get(tableUsers.getSelectionModel().getSelectedIndex()).get(3).toString();
            if (tableUsers.getSelectionModel().getSelectedIndex() >= 0) {
                System.out.println("Удалён пользователь с юзернеймом: " + name);
                dbhandler.deleteUser(name);
                TextWithChanges.setText("Пользователь " + name + " успешно удалён");
                Main.openNewScene("/sample/view/usersTablePage.fxml");
            }
        });
    }

    public ObservableList<ObservableList> buildData(String SQL) {
        ObservableList<ObservableList> data;
        DataBaseHandler dbHandler = new DataBaseHandler();
        data = FXCollections.observableArrayList();
            try {
                ResultSet rs = dbHandler.getDbconnection().createStatement().executeQuery(SQL);

                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                    col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }
                    });

                    tableUsers.getColumns().addAll(col);
                }
                while (rs.next()) {
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        row.add(rs.getString(i));
                    }
                    data.add(row);
                }
                tableUsers.setItems(data);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error on Building Data");
            }
            return data;
    }
    private void findUser(String idfield, String namefield, String usernamefield) {
        if (!idfield.isEmpty()) {
            String SQL = "SELECT * from " + Const.USER_TABLE + " WHERE " + Const.USER_ID + " = '" + idfield + "';";
            buildData(SQL);
        }
        else if (!namefield.isEmpty()) {
            String SQL = "SELECT * from " + Const.USER_TABLE + " WHERE " + Const.USER_FIRSTNAME + " = '" + namefield + "';";
            buildData(SQL);
        } else if (!usernamefield.isEmpty()) {
            String SQL = "SELECT * from " + Const.USER_TABLE + " WHERE " + Const.USER_USERNAME + " = '" + usernamefield + "';";
            buildData(SQL);
        }
        if (idfield.isEmpty() && namefield.isEmpty() && usernamefield.isEmpty()) {
            Main.openNewScene("/sample/view/usersTablePage.fxml");
        }
    }
}

