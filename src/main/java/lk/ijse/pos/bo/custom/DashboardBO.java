package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBo;

import java.sql.SQLException;

public interface DashboardBO extends SuperBo {
    int getRCount() throws SQLException, ClassNotFoundException;

    int getVehicleCount() throws SQLException, ClassNotFoundException;

    int getCustomerCount() throws SQLException, ClassNotFoundException;

    int getDriversCount() throws SQLException, ClassNotFoundException;
}
