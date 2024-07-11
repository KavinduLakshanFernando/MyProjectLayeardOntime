package lk.ijse.pos.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.VehicleDAO;
import lk.ijse.pos.entity.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl implements VehicleDAO {
    @Override
    public ArrayList<Vehicle> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Vehicle entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into vehicle values (?,?,?,?,?)",entity.getId(),entity.getModel(),entity.getColur(),entity.getIId(),entity.getStatus());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("delete from Vehicle WHERE V_id = ?",id);
    }

    @Override
    public boolean update(Vehicle entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Vehicle search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * from vehicle where V_id = ? AND status = 'active'",id);

        if (resultSet.next()) {
            String v_id = resultSet.getString(1);
            String model = resultSet.getString(2);
            String colur = resultSet.getString(3);
            String iid = resultSet.getString(4);
            String status = resultSet.getString(5);

            Vehicle vehicle = new Vehicle(v_id, model, colur, iid,status);
            return vehicle;
        }
        return null;

    }

    @Override
    public List<String> getIdlist() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT V_id from vehicle");

        List<String> codeList = new ArrayList<>();
        while (resultSet.next()){
            String id = resultSet.getString(1);
            codeList.add(id);
        }
        return codeList;
    }
}
