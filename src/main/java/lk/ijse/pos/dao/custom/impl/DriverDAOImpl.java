package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.DriverDAO;
import lk.ijse.pos.entity.Driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverDAOImpl implements DriverDAO {
    @Override
    public ArrayList<Driver> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM driver where status = 'active' ");
        ArrayList<Driver> DriverList = new ArrayList<>();

        while (resultSet.next()){
            String id= resultSet.getString(1);
            String name=resultSet.getString(2);
            String address=resultSet.getString(3);
            String tel=resultSet.getString(4);
            String vnumber=resultSet.getString(5);
            String status= resultSet.getString(6);

            Driver driver = new Driver(id, name, address, tel, vnumber,status);
            DriverList.add(driver);
        }
        return DriverList;
    }

    @Override
    public boolean add(Driver entity) throws SQLException, ClassNotFoundException {
      return SQLUtil.execute("insert into driver values(?,?,?,?,?,?)",entity.getId(),entity.getName(),entity.getAddress(),entity.getContact(),entity.getVnumber(),entity.getStatus());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("update driver set status = 'deactivated' where D_id = ?",id);
    }

    @Override
    public boolean update(Driver entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE driver SET Name=?, Address=?, Tel=?, V_id=? WHERE D_id=?",entity.getName(), entity.getAddress(), entity.getContact(), entity.getVnumber(), entity.getId());
    }

    @Override
    public Driver search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * from driver where D_id = ? and status = 'active'",id);
        if (resultSet.next()) {
            String idd = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);
            String vid = resultSet.getString(5);
            String status = resultSet.getString(6);

            Driver driver = new Driver(idd, name, address, tel, vid, status);
            return driver;
        }
        return null;
    }

    @Override
    public List<String> getid() {
        return List.of();
    }
}
