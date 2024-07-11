package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDao;
import lk.ijse.pos.entity.Maintenance_Details;

import java.sql.SQLException;

public interface Maintenance_DetailsDAO extends CrudDao<Maintenance_Details> {
    String getVId(String mid) throws SQLException, ClassNotFoundException;
}
