package lk.ijse.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.DriverBO;
import lk.ijse.pos.bo.custom.impl.DriverBOImpl;
import lk.ijse.pos.bo.custom.impl.VehicalBOImpl;
import lk.ijse.pos.dao.custom.DriverDAO;
import lk.ijse.pos.dto.DriverDTO;
import lk.ijse.pos.entity.Driver;
import lk.ijse.pos.view.tdm.DriverTM;

import java.sql.SQLException;
import java.util.List;

public class ManageDriverController {

    //DriverBOImpl driverBO = new DriverBOImpl();
    DriverBO driverBO = (DriverBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DRIVER);
    VehicalBOImpl vehicalBO = new VehicalBOImpl();
    public TextField Driverid;
    public TextField DriverName;
    public TextField DriverPhone;
    public TextField DriverAddress;
    public ComboBox VehicleNumCmb;
    public TextField txtserchDriver;

    @FXML
    private TableView<DriverTM> DriverTable;
    @FXML
    private TableColumn<?, ?> DriverNicCol;
    @FXML
    private TableColumn<?, ?> DriverNameCol;
    @FXML
    private TableColumn<?, ?> DriverAddressCol;
    @FXML
    private TableColumn<?, ?> DriverContactCol;
    @FXML
    private TableColumn<?, ?> VehicleNumberCol;

    private void loadAllDrivers() throws ClassNotFoundException {
        ObservableList<DriverTM> obList = FXCollections.observableArrayList();

        try {
            List<DriverDTO> driverList = driverBO.getAll();
            for (DriverDTO driver : driverList) {
                DriverTM tm = new DriverTM(
                        driver.getId(),
                        driver.getName(),
                        driver.getAddress(),
                        driver.getContact(),
                        driver.getVnumber()
                );
                obList.add(tm);
            }
            DriverTable.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() throws ClassNotFoundException, SQLException {
        setCellValueFactory();
        loadAllDrivers();
        getVehicleIds();
    }

    private void setCellValueFactory() {
        DriverNicCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        DriverNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        DriverAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        DriverContactCol.setCellValueFactory(new PropertyValueFactory<>("tel"));
        VehicleNumberCol.setCellValueFactory(new PropertyValueFactory<>("Vnumber"));
    }


    private void getVehicleIds() throws SQLException, ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList();

        List<String> idList = vehicalBO.getIds();

        for(String id : idList) {
            obList.add(id);
        }

        VehicleNumCmb.setItems(obList);

    }

    public void SaveDriver(ActionEvent actionEvent) {
        String id = Driverid.getText();
        String name = DriverName.getText();
        String address = DriverAddress.getText();
        String contact = DriverPhone.getText();
        String vnumber = (String) VehicleNumCmb.getValue();
        String status = "active";

        DriverDTO driverDTO = new DriverDTO(id, name, address, contact, vnumber,status);
        try{
            boolean issaved = driverBO.saveDriver(driverDTO);
            if (issaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Driver Is Saved").show();
                loadAllDrivers();
            }else {
                new Alert(Alert.AlertType.ERROR,"Driver Not Saved").show();
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    public void DeleteDriver(ActionEvent actionEvent) {
        String id = txtserchDriver.getText();
        try {
            boolean isdeleted = driverBO.delete(id);
            if (isdeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Driver Is Deleted").show();
                loadAllDrivers();
            }else{
                new Alert(Alert.AlertType.ERROR,"Driver Not Delete").show();
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Cant Delete This Driver").show();
        }
    }

    public void UpdateDriver(ActionEvent actionEvent) {
        String id = Driverid.getText();
        String name = DriverName.getText();
        String address = DriverAddress.getText();
        String contact = DriverPhone.getText();
        String vnumber = (String) VehicleNumCmb.getValue();
        String status = "active";

        DriverDTO driverDTO = new DriverDTO(id, name, address, contact, vnumber,status);
        try{
            boolean issaved = driverBO.update(driverDTO);
            if (issaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Driver Is Update").show();
                loadAllDrivers();
            }else {
                new Alert(Alert.AlertType.ERROR,"Driver Not Update").show();
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    public void ClearDriver(ActionEvent actionEvent) {
        Driverid.setText(null);
        DriverName.setText(null);
        DriverPhone.setText(null);
        DriverAddress.setText(null);
        VehicleNumCmb.setValue(null);
        txtserchDriver.setText(null);

    }

    public void SearchDriverOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtserchDriver.getText();

        Driver driver = driverBO.searchData(id);

        if (driver != null){
            Driverid.setText(driver.getId());
            DriverName.setText(driver.getName());
            DriverPhone.setText(driver.getContact());
            DriverAddress.setText(driver.getAddress());
            VehicleNumCmb.setValue(driver.getVnumber());

        }
    }
}
