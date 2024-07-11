package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.InsuranceDAO;
import lk.ijse.pos.dto.InsurenceDTO;
import lk.ijse.pos.entity.Insurence;

import java.sql.*;
import java.util.ArrayList;

public class InsuranceDAOImpl implements InsuranceDAO {
    @Override
    public ArrayList<Insurence> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Insurence entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into insurance values (?,?,?)", entity.getI_id(), entity.getStdate(), entity.getEnddate());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from insurance where I_id = ?",id);
    }

    @Override
    public boolean update(Insurence entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Insurence search(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet= SQLUtil.execute("SELECT * from insurance where I_id = ?",id);

        if (resultSet.next()){
            String iid = resultSet.getString(1);
            Date stdate = resultSet.getDate(2);
            Date enddate = resultSet.getDate(3);
            Insurence insurence =new Insurence(iid,stdate,enddate);
            return insurence;
        }
        return null;
    }

}
