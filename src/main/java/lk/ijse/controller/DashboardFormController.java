package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {
    public AnchorPane root;

    public void CustomerOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent= FXMLLoader.load(this.getClass().getResource("/view/CustomerFormController.fxml"));
        Scene scene=new Scene(parent);
        Stage stage= (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Customer");
    }

    public void ItemOnAction(ActionEvent actionEvent) {
    }

    public void OrderOnAction(ActionEvent actionEvent) {
    }
}
