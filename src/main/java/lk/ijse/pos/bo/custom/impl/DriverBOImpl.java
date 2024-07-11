package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.DriverBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.DriverDAO;
import lk.ijse.pos.dao.custom.impl.DriverDAOImpl;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.DriverDTO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Driver;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverBOImpl implements DriverBO {

    //DriverDAOImpl driverDAO = new DriverDAOImpl();
    DriverDAO driverDAO = (DriverDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DRIVER);
    public boolean saveDriver(DriverDTO driverDTO) throws SQLException, ClassNotFoundException {
        return driverDAO.add(new Driver(driverDTO.getId(),driverDTO.getName(), driverDTO.getAddress(), driverDTO.getContact(), driverDTO.getVnumber(), driverDTO.getStatus()));
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return driverDAO.delete(id);
    }

    public boolean update(DriverDTO driverDTO) throws SQLException, ClassNotFoundException {
        return driverDAO.update(new Driver(driverDTO.getId(),driverDTO.getName(),driverDTO.getAddress(),driverDTO.getContact(),driverDTO.getVnumber(),driverDTO.getStatus()));
    }

    public Driver searchData(String id) throws SQLException, ClassNotFoundException {
      return driverDAO.search(id);
    }

    @Override
    public List<DriverDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Driver> list = driverDAO.getAll();
        ArrayList<DriverDTO> list2 = new ArrayList<>();
        for (Driver driver : list) {
            list2.add(new DriverDTO(driver.getId(), driver.getName(), driver.getAddress(), driver.getContact(), driver.getVnumber(), driver.getStatus()));
        }
        return list2;
    }

    @Override
    public List<String> getIds() {
        return driverDAO.getid();
    }
}
