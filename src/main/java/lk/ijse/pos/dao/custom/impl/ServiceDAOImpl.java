package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.ServiceDAO;
import lk.ijse.pos.entity.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAOImpl implements ServiceDAO {
    @Override
    public ArrayList<Service> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Service entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Service entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Service search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getNameList() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT S_type from service");
        List<String> nameList = new ArrayList<>();
        while(resultSet.next()){
            String type = resultSet.getString(1);
            nameList.add(type);
        }
        return nameList;
    }

    @Override
    public String getId(String servicename) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT S_id from service where S_type = ?", servicename);

        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }
}
