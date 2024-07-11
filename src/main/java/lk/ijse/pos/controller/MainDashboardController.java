package lk.ijse.pos.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.DashboardBO;
import lk.ijse.pos.bo.custom.impl.DashboardBOImpl;
import lk.ijse.pos.entity.Dashboard;

import java.sql.SQLException;
import java.time.LocalDate;

public class MainDashboardController {
    DashboardBO dashboardBO = (DashboardBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DASHBOARD);

    @FXML
    private Label customercount;

    @FXML
    private Label driverscount;

    @FXML
    private Label lblDate;

    @FXML
    private Label reservationcount;

    @FXML
    private Label vehiclecount;

        public AnchorPane dashID;



        public void initialize() throws SQLException, ClassNotFoundException {
            setDate();
            setVehicleCount();
            setCustomerCount();
            setDriverscount();
            setReservationCount();
        }


        private void setReservationCount() throws ClassNotFoundException {
            try {
                int resCount = dashboardBO.getRCount();
                reservationcount.setText(String.valueOf(resCount));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        public void setDate(){
            LocalDate now = LocalDate.now();
            lblDate.setText(String.valueOf(now));
        }



        public void setVehicleCount() throws SQLException, ClassNotFoundException {
            try {
                int vehicleCount = dashboardBO.getVehicleCount();
                vehiclecount.setText(String.valueOf(vehicleCount));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public void setCustomerCount() throws SQLException, ClassNotFoundException {
            try {
                int customerCount = dashboardBO.getCustomerCount();
                customercount.setText(String.valueOf(customerCount));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public void setDriverscount() throws ClassNotFoundException {
            try {
                int driverCount = dashboardBO.getDriversCount();
                driverscount.setText(String.valueOf(driverCount));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

