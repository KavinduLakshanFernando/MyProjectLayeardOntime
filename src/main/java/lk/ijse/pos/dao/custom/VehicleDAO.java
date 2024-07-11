package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDao;
import lk.ijse.pos.entity.Vehicle;

import java.sql.SQLException;
import java.util.List;

public interface VehicleDAO extends CrudDao<Vehicle> {
    List<String> getIdlist() throws SQLException, ClassNotFoundException;
}
