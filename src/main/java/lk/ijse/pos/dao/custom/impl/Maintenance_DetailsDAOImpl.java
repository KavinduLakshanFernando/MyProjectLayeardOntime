package lk.ijse.pos.dao.custom.impl;


import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.Maintenance_DetailsDAO;
import lk.ijse.pos.entity.Maintenance_Details;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Maintenance_DetailsDAOImpl implements Maintenance_DetailsDAO {

    @Override
    public ArrayList<Maintenance_Details> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Maintenance_Details entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into maintenance_Details values (?,?)",entity.getV_id(),entity.getV_id());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Maintenance_Details entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Maintenance_Details search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getVId(String mid) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT V_id from maintenance_Details where M_id = ?",mid);

        if (resultSet.next()){
            String vid = resultSet.getString(1);
            return vid;
        }
        return null;
    }
    }
