package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.SuperDao;

import java.sql.SQLException;

public interface DashboardDAO extends SuperDao {
    int getVehicleCount() throws SQLException, ClassNotFoundException;

    int getCustomerCount() throws SQLException, ClassNotFoundException;

    int getDriversCount() throws SQLException, ClassNotFoundException;

    int getResCount() throws SQLException, ClassNotFoundException;
}
