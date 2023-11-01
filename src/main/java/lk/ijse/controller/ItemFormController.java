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
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.ItemDto;
import lk.ijse.model.CustomerModel;
import lk.ijse.model.ItemModel;

import java.io.IOException;
import java.sql.SQLException;

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

    ItemModel itModel=new ItemModel();

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFeald();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String codeText = code.getText();
        String descriptionText = description.getText();
        String priceText = unitPrice.getText();
        String qtyText = qty.getText();

        var dto=new ItemDto(codeText,descriptionText,qtyText,priceText);

        try{
            boolean isSaved=itModel.saveItem(dto);
            System.out.println("Item saved");
        } catch (SQLException e) {
            System.out.println("Error item saving");
            throw new RuntimeException(e);
        }

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
