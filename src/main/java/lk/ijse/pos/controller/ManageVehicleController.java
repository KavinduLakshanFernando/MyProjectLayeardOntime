package lk.ijse.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.pos.bo.custom.InsuranceBo;
import lk.ijse.pos.bo.custom.VehicleBO;
import lk.ijse.pos.bo.custom.impl.InsuranceBOImpl;
import lk.ijse.pos.bo.custom.impl.VehicalBOImpl;
import lk.ijse.pos.dao.custom.QuaryDAO;
import lk.ijse.pos.dao.custom.VehicleDAO;
import lk.ijse.pos.dao.custom.impl.InsuranceDAOImpl;
import lk.ijse.pos.dao.custom.impl.QuaryDAOImpl;
import lk.ijse.pos.dao.custom.impl.VehicleDAOImpl;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.InsurenceDTO;
import lk.ijse.pos.dto.VehicleDTO;
import lk.ijse.pos.entity.Vehicle;
import lk.ijse.pos.view.tdm.CustomerTM;
import lk.ijse.pos.view.tdm.VehicleTMDetails;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageVehicleController {

    VehicalBOImpl vehicleBO = new VehicalBOImpl();
    InsuranceBOImpl insuranceBo = new InsuranceBOImpl();

    public DatePicker txtinsuranceendDate;
    public ComboBox<String> cmbmodel;
    public ComboBox <String> cmbcolor;
    public TextField txtInshurance;
    public TextField txtserchVehicle;
    public DatePicker txtinsuranceStartDate;
    public TextField txtVehicleNumber;

    @FXML
    private TableColumn<?, ?> EndDateCol;

    @FXML
    private TableColumn<?, ?> InshuranceNumberCol;

    @FXML
    private TableColumn<?, ?> StartDateCol;

    @FXML
    private TableColumn<?, ?> VehicleColorCol;

    @FXML
    private TableColumn<?, ?> VehicleModelCol;

    @FXML
    private TableColumn<?, ?> VehicleNumberCol;

    @FXML
    private TableView<VehicleTMDetails> VehicleTable;
    
    


    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValues();
        loadAllVehicles();
        setCmbmodel();
        setCmbcolur();
        showSelectedUserDetails();
    }

    private void showSelectedUserDetails() {
        VehicleTMDetails selectedUser = VehicleTable.getSelectionModel().getSelectedItem();
        VehicleTable.setOnMouseClicked(event -> showSelectedUserDetails());
        if (selectedUser != null) {
            txtVehicleNumber.setText(selectedUser.getVNumber());
            txtInshurance.setText(selectedUser.getINumber());
            cmbcolor.setValue(selectedUser.getColor());
            cmbmodel.setValue(selectedUser.getModel());
            txtinsuranceStartDate.setValue(selectedUser.getStartDate().toLocalDate());
            txtinsuranceendDate.setValue(selectedUser.getEndDate().toLocalDate());
        }
    }

    public void loadAllVehicles() throws SQLException, ClassNotFoundException {
        ObservableList<VehicleTMDetails> obList = FXCollections.observableArrayList();

        try {
            List<VehicleTMDetails> vehicleList = QuaryDAOImpl.getAlll();

            for (VehicleTMDetails details : vehicleList){
                obList.add(details);
            }
            VehicleTable.setItems(obList);
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void setCellValues(){
        VehicleNumberCol.setCellValueFactory(new PropertyValueFactory<>("VNumber"));
        InshuranceNumberCol.setCellValueFactory(new PropertyValueFactory<>("INumber"));
        VehicleColorCol.setCellValueFactory(new PropertyValueFactory<>("Color"));
        VehicleModelCol.setCellValueFactory(new PropertyValueFactory<>("Model"));
        StartDateCol.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
        EndDateCol.setCellValueFactory(new PropertyValueFactory<>("EndDate"));
    }

    public void AddVehicleOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        String vid = txtVehicleNumber.getText();
        String iId = txtInshurance.getText();
        String model = cmbmodel.getValue();
        String colur = cmbcolor.getValue();
        Date stsate = Date.valueOf(txtinsuranceStartDate.getValue());
        Date enddate = Date.valueOf(txtinsuranceendDate.getValue());
        String status = "active";

        InsurenceDTO insurence = new InsurenceDTO(iId, stsate, enddate);
        VehicleDTO vehicle = new VehicleDTO(vid, model, colur, iId,status);

       try {
           boolean issaved = vehicleBO.regiVehicle(insurence,vehicle);
          if(issaved){
             new Alert(Alert.AlertType.CONFIRMATION,"Vehicle Saved").show();
             loadAllVehicles();
             clear();
          }else{
              new Alert(Alert.AlertType.ERROR,"Vehicle Not Saved").show();
          }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
       }
   }

    private void setCmbmodel() {
        ObservableList<String> modellist = FXCollections.observableArrayList(
                "Suzuki Alto", "Wagon R", "Suzuki Spacia", "Toyota Vitz","Toyota Aqua","Toyota Priys"
                ,"Toyota Axio","Toyot Premio","Honda Vezel","Toyota KDH"
        );

        cmbmodel.setItems(modellist);
    }

    private void setCmbcolur() {
        ObservableList<String> colurlist = FXCollections.observableArrayList(
                "White", "Red", "Black", "Blue","Green"
        );

        cmbcolor.setItems(colurlist);
    }

    public void DeleteVehicleOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtVehicleNumber.getText();
        String iid = txtInshurance.getText();

        boolean isdelete = vehicleBO.delete(id,iid);

        if(isdelete){
            new Alert(Alert.AlertType.CONFIRMATION,"Vehicle is deleted").show();
            loadAllVehicles();
        }else {
            new Alert(Alert.AlertType.ERROR,"Vehicle is not deleted").show();
        }
    }


    public void SearchvehicleOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String searchid = txtserchVehicle.getText();

        VehicleDTO vehicle = vehicleBO.searchVehicle(searchid);
        if(vehicle!=null) {

            try {
                assert vehicle != null;
                InsurenceDTO insurence =insuranceBo.seachdata(vehicle.getIId());
                txtVehicleNumber.setText(vehicle.getId());
                assert insurence != null;
                txtInshurance.setText(insurence.getI_id());
                cmbmodel.setValue(vehicle.getModel());
                cmbcolor.setValue(vehicle.getColur());
                txtinsuranceStartDate.setValue(insurence.getStdate().toLocalDate());
                txtinsuranceendDate.setValue(insurence.getEnddate().toLocalDate());

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Cant Find This Vehicle").show();
        }
    }

    public void clear(){
        txtVehicleNumber.clear();
        txtInshurance.clear();
        txtinsuranceStartDate.setValue(null);
        txtinsuranceendDate.setValue(null);
        cmbcolor.setValue(null);
        cmbmodel.setValue(null);
    }

    public void CelarVehicleDetails(ActionEvent actionEvent) {
        clear();
    }
}
