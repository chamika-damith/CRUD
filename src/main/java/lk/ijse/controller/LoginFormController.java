package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.db.DbConnection;

import javax.security.auth.login.Configuration;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {

    public TextField txtUsername;
    public PasswordField txtPassword;
    public AnchorPane loginRoot;

    public void txtUsernameOnAction(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void txtPasswordOnAction(ActionEvent actionEvent) throws SQLException, IOException {
        login();
    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws SQLException, IOException {
        login();
    }

    public void login() throws SQLException, IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        Connection connection= DbConnection.getInstance().getConnection();

        String sql="SELECT * FROM login_detail WHERE username=? AND password=?";

        PreparedStatement preparedStatement=connection.prepareStatement(sql);

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Parent parent= FXMLLoader.load(this.getClass().getResource("/view/DashboardForm.fxml"));
            Scene scene = new Scene(parent);
            Stage stage= (Stage) loginRoot.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle("Dashboard");

        }else {
            new Alert(Alert.AlertType.ERROR,"Incorrect Username or password").show();
            txtPassword.clear();
            txtUsername.clear();
            txtUsername.requestFocus();
        }
    }

    public void btnShowPasswordOnAction(ActionEvent actionEvent) {
        txtPassword.setVisible(true);
    }
}
