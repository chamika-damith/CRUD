package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ItemFormController {
    public TextField code;
    public TextField description;
    public TextField unitPrice;
    public TextField qty;
    public TableView tblItem;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colAction;
    public AnchorPane itemRoot;

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFeald();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent= FXMLLoader.load(this.getClass().getResource("/view/DashboardForm.fxml"));

        Scene scene=new Scene(parent);

        Stage stage= (Stage) itemRoot.getScene().getWindow();

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard");

    }

    public void clearFeald(){
        code.clear();
        description.clear();
        unitPrice.clear();
        qty.clear();
    }


}
