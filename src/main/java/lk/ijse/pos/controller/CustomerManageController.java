package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.CustomerBo;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.view.tdm.CustomerTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerManageController {
    @FXML
    public TextField txtName;
    @FXML
    public TextField txtPhone;
    @FXML
    public TextField txtAddress;
    @FXML
    public TextField txtId;
    @FXML
    public TextField txtserchCustomer;
    @FXML
    public AnchorPane rootNode;
    @FXML
    private TableColumn<?, ?> addresscol;

    @FXML
    private TableColumn<?, ?> contactcol;

    @FXML
    private TableColumn<?, ?> namecol;

    @FXML
    private TableColumn<?, ?> niccol;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValues();
        loadCustomer();

    }
    CustomerBo customerBO  = (CustomerBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    private void loadCustomer() throws SQLException, ClassNotFoundException {
        tblCustomer.getItems().clear();
        try {
            ArrayList<CustomerDTO> cusList = customerBO.getAllCustomer();
            for (CustomerDTO customer : cusList) {
                tblCustomer.getItems().add(new CustomerTM(customer.getNic(),customer.getName(),customer.getAddress(), customer.getTel()));
            }

        } catch (Exception e) {
             new RuntimeException(e);
        }
    }

    private void setCellValues(){
        niccol.setCellValueFactory(new PropertyValueFactory<>("id"));
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addresscol.setCellValueFactory(new PropertyValueFactory<>("address"));
        contactcol.setCellValueFactory(new PropertyValueFactory<>("phone"));

    }

    public void SavebtnOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

            String id = txtId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String phone = txtPhone.getText();

            if (id.isEmpty() || name.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Fill In All Fields!").show();
                return;
            }
            CustomerDTO customerDTO = new CustomerDTO(id, name, address, phone);
            try {
                boolean isSaved =customerBO.addCustomer(customerDTO);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer Saved!").show();
                    loadCustomer();
                    clear();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Customer Not Saved").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


    }

    public void DeletebtnOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        String id = txtId.getText();
        try {
            boolean isDeleted =customerBO.deleteCustomer(id);
            if (isDeleted) {
                initialize();
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted!").show();
//                loadCustomer();

                clear();



//                tblCustomer.refresh();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void UpdatebtnOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

            String id = txtId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String tel = txtPhone.getText();

            boolean isUpdated = customerBO.updateCustomer(new CustomerDTO(id,name,address,tel));

            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated!").show();
                loadCustomer();
                clear();
            }

    }

    public void ClearbtnOnAction(ActionEvent actionEvent) {
        clear();
    }
    @FXML
    public void SearchCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtserchCustomer.getText();

        Customer customer = customerBO.searchCustomer(id);
        if (customer != null) {
            txtId.setText(customer.getNic());
            txtName.setText(customer.getName());
            txtAddress.setText(customer.getAddress());
            txtPhone.setText(customer.getTel());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Customer Not Found!").show();
        }
    }

    public void clear() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtPhone.clear();

        txtserchCustomer.clear();

    }
}
