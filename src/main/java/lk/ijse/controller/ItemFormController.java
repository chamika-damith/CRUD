package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.ItemDto;
import lk.ijse.dto.tm.ItemTm;
import lk.ijse.model.CustomerModel;
import lk.ijse.model.ItemModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ItemFormController {
    public TextField code;
    public TextField description;
    public TextField unitPrice;
    public TextField qty;
    public TableView<ItemTm> tblItem;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colAction;
    public AnchorPane itemRoot;
    public TableColumn colPrice;

    ItemModel itModel=new ItemModel();

    public void initialize(){
        setCelValueFactory();
        loadAllItems();
    }

    private void loadAllItems() {

        ObservableList<ItemTm> obList= FXCollections.observableArrayList();

        try{
            List<ItemDto> dtoItems=itModel.getAllItems();

            for (ItemDto dto : dtoItems ) {
                obList.add(new ItemTm(
                        dto.getCode(),
                        dto.getDescription(),
                        dto.getUnitPrice(),
                        dto.getQty()
                ));
            }
            tblItem.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCelValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));
    }

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


    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException {
        String textCode = code.getText();

        ItemDto dto=itModel.SearchItem(textCode);

        if (dto!=null) {
            code.setText(dto.getCode());
            description.setText(dto.getDescription());
            unitPrice.setText(dto.getUnitPrice());
            qty.setText(dto.getQty());
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Item not found").show();
        }
    }
}
