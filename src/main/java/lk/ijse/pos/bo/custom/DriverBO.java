package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBo;
import lk.ijse.pos.dao.custom.DriverDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.DriverDTO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Driver;
import lk.ijse.pos.entity.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DriverBO extends SuperBo {
    public boolean saveDriver(DriverDTO driverDTO) throws SQLException, ClassNotFoundException;

    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public boolean update(DriverDTO driverDTO) throws SQLException, ClassNotFoundException;

    public Driver searchData(String id) throws SQLException, ClassNotFoundException;

    List<DriverDTO> getAll() throws SQLException, ClassNotFoundException;

    List<String> getIds();
}
