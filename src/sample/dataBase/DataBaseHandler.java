package sample.dataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DataBaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbconnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName;
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void signUpUser(User user) {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_FIRSTNAME + "," + Const.USER_LASTNAME + "," + Const.USER_USERNAME
                + "," + Const.USER_PASSWORD + "," + Const.USER_LOCATION + "," + Const.USER_GENDER + ")" + "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement prSt = getDbconnection().prepareStatement(insert);
            prSt.setString(1, user.getFirstname());
            prSt.setString(2, user.getLastname());
            prSt.setString(3, user.getUsername());
            prSt.setString(4, user.getPassword());
            prSt.setString(5, user.getLocation());
            prSt.setString(6, user.getGender());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getUser(User user) {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_USERNAME + " =? AND " + Const.USER_PASSWORD + " =?";
        try {
            PreparedStatement prSt = getDbconnection().prepareStatement(select);
            prSt.setString(1,user.getUsername());
            prSt.setString(2,user.getPassword());
            resSet = prSt.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet getAnyUser() {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + Const.USER_TABLE;
        try {
            while(resultSet.next()){
                resultSet.getString(1);
                resultSet.getString(2);
                resultSet.getString(3);
                resultSet.getString(4);
                resultSet.getString(5);
                resultSet.getString(6);
                resultSet.getString(7);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getUserRole(User user) {
        ResultSet resSet = null;
        String selectRole = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_USERNAME + " =? AND " + Const.USER_PASSWORD + " =?";
        try {
            PreparedStatement prSt = getDbconnection().prepareStatement(selectRole);
            prSt.setString(1,user.getUsername());
            prSt.setString(2,user.getPassword());
            resSet = prSt.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }
}

