package lk.ijse.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.MaintenanceBO;
import lk.ijse.pos.bo.custom.Maintenance_DetailsBO;
import lk.ijse.pos.bo.custom.VehicleBO;
import lk.ijse.pos.bo.custom.impl.MaintenanceBOImpl;
import lk.ijse.pos.dto.MaintenanceDTO;
import lk.ijse.pos.dto.Maintenance_DetailsDTO;
import lk.ijse.pos.view.tdm.MaintenanceTM;

import javax.swing.text.Element;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ManageMaintenanceController {
    MaintenanceBO maintenanceBO = (MaintenanceBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MAINTENNCE);
    VehicleBO vehicleBO = (VehicleBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.VEHICAL);
    Maintenance_DetailsBO maintenanceDetailsBO = (Maintenance_DetailsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MAINTENANCEDETAIL);
    public TextField MaintenanceCost;
    public TextField txtMaintenanceId;
    public TextField txtDesc;
    public TextField txtserchMaintenance;
    public DatePicker MaintenanceDate;
    public ComboBox Vehiclenumcmb;


    @FXML
    private TableView<MaintenanceTM> MaintenanceTable;

    @FXML
    private TableColumn<?, ?> vnumbercol;

    @FXML
    private TableColumn<?, ?> desccol;

    @FXML
    private TableColumn<?, ?> maintenancenumbercol;

    @FXML
    private TableColumn<?, ?> mcostcol;

    @FXML
    private TableColumn<?, ?> mdatecol;


    public void initialize() throws SQLException, ClassNotFoundException {
        getVehicleIds();
        setCellValueFactory();
        loadAllMaintenance();
        getCurrentMaintenanceId();

    }
    private void loadAllMaintenance() throws SQLException, ClassNotFoundException {
        ObservableList<MaintenanceTM> obList = FXCollections.observableArrayList();

        List<MaintenanceDTO> menList = maintenanceBO.getAll();
        for (MaintenanceDTO men : menList) {
            MaintenanceTM tm = new MaintenanceTM(
                    men.getV_id(),
                    men.getM_id(),
                    men.getDescription(),
                    men.getDate(),
                    men.getCost()
            );
            obList.add(tm);
        }

        MaintenanceTable.setItems(obList);
    }

    private void getCurrentMaintenanceId() throws SQLException, ClassNotFoundException {
        String currentId = maintenanceBO.getCurrentId();

        String nextMaintenanceId = generateNextMaintenanceid(currentId);
        txtMaintenanceId.setText(nextMaintenanceId);

    }

    private String generateNextMaintenanceid(String currentId) {
        if(currentId != null && !currentId.isEmpty()) {
            String[] split = currentId.split("M");
            if (split.length > 1) {
                int idNum = Integer.parseInt(split[1]);
                return "M0" + String.format("%02d", ++idNum);
            }
        }
        return "M001"; // Default starting ID
    }

    private void setCellValueFactory() {
        vnumbercol.setCellValueFactory(new PropertyValueFactory<>("V_id"));
        maintenancenumbercol .setCellValueFactory(new PropertyValueFactory<>("M_id"));
        desccol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        mdatecol.setCellValueFactory(new PropertyValueFactory<>("date"));
        mcostcol.setCellValueFactory(new PropertyValueFactory<>("cost"));
    }

    private void getVehicleIds() throws ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = vehicleBO.getIds();

            for(String id : idList) {
                obList.add(id);
            }

            Vehiclenumcmb.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void SaveMaintenance(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String vid = (String) Vehiclenumcmb.getValue();
        String cost = MaintenanceCost.getText();
        String mid = txtMaintenanceId.getText();
        String desc = txtDesc.getText();
        String mdate = String.valueOf(MaintenanceDate.getValue());

        MaintenanceDTO maintenance = new MaintenanceDTO(vid,cost,mid, desc, mdate);
        Maintenance_DetailsDTO maintenanceDetails = new Maintenance_DetailsDTO(vid, mid);

        Boolean isSaved = maintenanceBO.saveMaintenance(maintenance,maintenanceDetails);
        if(isSaved){
            new Alert(Alert.AlertType.CONFIRMATION,"Maintenance Data Saved").show();
            loadAllMaintenance();
        }else {
            new Alert(Alert.AlertType.ERROR,"Maintenance Data Not Saved ").show();
        }

    }

    public void DeletMaintenance(ActionEvent actionEvent) {
        String mid = txtMaintenanceId.getText();
        try {
            boolean isdelete = maintenanceBO.delete(mid);
           if (isdelete){
               new Alert(Alert.AlertType.CONFIRMATION,"Delete Maintenance Data").show();
               loadAllMaintenance();
           }else {
               new Alert(Alert.AlertType.ERROR,"Not Delete Maintenance Data").show();

           }
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }


    }


    public void ClearMaintenance(ActionEvent actionEvent) {
        clear();
    }

    private void clear() {
        Vehiclenumcmb.setValue(null);
        MaintenanceCost.clear();
        txtMaintenanceId.clear();
        txtDesc.clear();
        txtserchMaintenance.clear();
        MaintenanceDate.setValue(null);
    }

    public void SearchmaintenanceOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String mid = txtserchMaintenance.getText();
        String vid = maintenanceDetailsBO.getVid(mid);
        if (vid != null){
            MaintenanceDTO maintenance =maintenanceBO.searchData(mid);
            if (maintenance != null){
                txtMaintenanceId.setText(maintenance.getM_id());
                Vehiclenumcmb.setValue(vid);
                MaintenanceCost.setText(maintenance.getCost());
                txtDesc.setText(maintenance.getDescription());
                MaintenanceDate.setValue(LocalDate.parse(maintenance.getDate()));
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Cant Find This Maintenence").show();
        }


    }
}
