package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDao;
import lk.ijse.pos.entity.Service;

import java.sql.SQLException;
import java.util.List;

public interface ServiceDAO extends CrudDao<Service> {
    List<String> getNameList() throws SQLException, ClassNotFoundException;

    String getId(String servicename) throws SQLException, ClassNotFoundException;
}
