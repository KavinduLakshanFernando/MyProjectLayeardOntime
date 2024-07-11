package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDao;
import lk.ijse.pos.entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDao<Customer> {

    String nicSearch(String nic) throws SQLException, ClassNotFoundException;
}
