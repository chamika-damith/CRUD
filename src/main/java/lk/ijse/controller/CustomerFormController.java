package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.tm.CustomerTm;
import lk.ijse.model.CustomerModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerFormController {
    public TextField cId;
    public TextField cName;
    public TextField cAddress;
    public TextField cTel;
    public AnchorPane CustomerRoot;

    public CustomerModel cusModel=new CustomerModel();
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colTel;
    public TableView<CustomerTm> tblCustomer;

    public void initialize(){
        setCelValueFactory();
        loadAllCustomers();
    }

    private void setCelValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colTel.setCellValueFactory(new PropertyValueFactory("tel"));
    }

    private void loadAllCustomers() {
        var model=new CustomerModel();

        ObservableList<CustomerTm> obList= FXCollections.observableArrayList();

        try {
            List<CustomerDto> dtoList=model.getAllCustomer();

            for (CustomerDto dto : dtoList) {
                obList.add(
                        new CustomerTm(
                                dto.getCustomer_id(),
                                dto.getAddress(),
                                dto.getName(),
                                dto.getTel()
                        )
                );
            }
            tblCustomer.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent= FXMLLoader.load(this.getClass().getResource("/view/DashboardForm.fxml"));
        Scene scene=new Scene(parent);
        Stage stage = (Stage) CustomerRoot.getScene().getWindow();
        stage.setScene(scene);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = cId.getText();
        String tel = cTel.getText();
        String name = cName.getText();
        String address = cAddress.getText();

        var dto = new CustomerDto(id, name, address, tel);

        try {
            Boolean isSaved=cusModel.saveCustomer(dto);
            System.out.println("Customer saved");
            clearField();
        } catch (SQLException e) {
            System.out.println("Customer not saved");
            throw new RuntimeException(e);
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException {
        String id = cId.getText();
        String address = cAddress.getText();
        String name = cName.getText();
        String tel = cTel.getText();

        var dto= new CustomerDto(id, address, name, tel);

        boolean b = cusModel.updateCustomer(dto);

        if (b){
            System.out.println("Customer updated successfully");
        }else{
            System.out.println("Customer not updated successfully");
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException {
        String text = cId.getText();

        boolean b = cusModel.deleteCustomer(text);

        if (b) {
            System.out.println("Customer deleted successfully");
        }else {
            System.out.println("customer not deleted");
        }

    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearField();
    }

    private void clearField() {
        cId.clear();
        cAddress.clear();
        cName.clear();
        cTel.clear();
    }

    public void SearchOnAction(ActionEvent actionEvent) throws SQLException {
        String id = cId.getText();


        CustomerDto dto = cusModel.searchCustomer(id);

        if(dto!=null) {
            cId.setText(dto.getCustomer_id());
            cAddress.setText(dto.getAddress());
            cName.setText(dto.getName());
            cTel.setText(dto.getTel());
        }else {
            new Alert(Alert.AlertType.CONFIRMATION,"Customer not found").show();
        }
    }
}
