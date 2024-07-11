package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.MaintenanceDAO;
import lk.ijse.pos.entity.Maintenance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceDAOImpl implements MaintenanceDAO {

    @Override
    public ArrayList<Maintenance> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * from maintenance");

        ArrayList<Maintenance> resList = new ArrayList<>();

        while (resultSet.next()) {
            String vid = resultSet.getString(1);
            String mid = resultSet.getString(2);
            String desc = resultSet.getString(3);
            String cost = resultSet.getString(4);
            String date = resultSet.getString(5);

            Maintenance maintenancetm = new Maintenance(vid, mid, desc, date, cost);
            resList.add(maintenancetm);
        }
        return resList;
    }

    @Override
    public boolean add(Maintenance entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into Maintenance values (?,?,?,?,?)",entity.getM_id(),entity.getV_id(),entity.getDescription(),entity.getCost(),entity.getDate());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from Maintenance where M_id = ?",id);
    }

    @Override
    public boolean update(Maintenance entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Maintenance search(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * from maintenance where M_id = ?",id);

        if (resultSet.next()){
            String idd = resultSet.getString(1);
            String vid = resultSet.getString(2);
            String desc = resultSet.getString(3);
            String cost = resultSet.getString(4);
            String date = resultSet.getString(5);
            Maintenance maintenance = new Maintenance(idd,vid, desc, cost, date);
            return  maintenance;
        }
        return null;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT M_id FROM maintenance ORDER BY M_id DESC LIMIT 1");
        if(resultSet.next()) {
            String mId = resultSet.getString(1);
            return mId;
        }
        return null;
    }
}
