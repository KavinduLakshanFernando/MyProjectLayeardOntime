package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDao;
import lk.ijse.pos.dao.SuperDao;
import lk.ijse.pos.entity.Driver;

import java.util.List;

public interface DriverDAO extends CrudDao<Driver> {
    List<String> getid();
}
