package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDao;
import lk.ijse.pos.entity.Maintenance;

import java.sql.SQLException;

public interface MaintenanceDAO extends CrudDao<Maintenance> {
    String getCurrentId() throws SQLException, ClassNotFoundException;
}
