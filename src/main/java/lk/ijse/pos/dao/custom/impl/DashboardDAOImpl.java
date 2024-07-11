package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.DashboardDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardDAOImpl implements DashboardDAO {

    @Override
    public int getVehicleCount() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT count(*) as vehicleCount from vehicle");
        if (resultSet.next()){
            return resultSet.getInt("vehicleCount");
        }
        return 0;
    }
    @Override
    public int getCustomerCount() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT count(*) as CustomerCount from customer");
        if (resultSet.next()){
            return resultSet.getInt("Customercount");
        }
        return 0;
    }


    @Override
    public int getDriversCount() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT count(*) as DriversCount from driver");
        if (resultSet.next()){
            return resultSet.getInt("DriversCount");
        }
        return 0;
    }

    @Override
    public int getResCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT count(*) as ResCount from reservation");
        if (resultSet.next()){
            return resultSet.getInt("ResCount");
        }
        return 0;
    }

}
