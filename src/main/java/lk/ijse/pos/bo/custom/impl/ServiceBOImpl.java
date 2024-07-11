package lk.ijse.pos.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.pos.bo.custom.ServiceBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ServiceDAO;
import lk.ijse.pos.entity.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceBOImpl implements ServiceBO{

    ServiceDAO serviceDAO = (ServiceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SERVICE);

    @Override
    public List<String> getNamelist() throws SQLException, ClassNotFoundException {
        return serviceDAO.getNameList();
    }
}
