package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.DashboardBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.DashboardDAO;

import java.sql.SQLException;

public class DashboardBOImpl implements DashboardBO {
    DashboardDAO dashboardDAO = (DashboardDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DASHBOARD);

    @Override
    public int getRCount() throws SQLException, ClassNotFoundException {
        return dashboardDAO.getResCount();
    }

    @Override
    public int getVehicleCount() throws SQLException, ClassNotFoundException {
        return dashboardDAO.getVehicleCount();
    }

    @Override
    public int getCustomerCount() throws SQLException, ClassNotFoundException {
        return dashboardDAO.getCustomerCount();

    }

    @Override
    public int getDriversCount() throws SQLException, ClassNotFoundException {
        return dashboardDAO.getDriversCount();
    }
}
