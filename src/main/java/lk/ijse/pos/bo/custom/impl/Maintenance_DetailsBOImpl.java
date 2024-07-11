package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.Maintenance_DetailsBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.Maintenance_DetailsDAO;

import java.sql.SQLException;

public class Maintenance_DetailsBOImpl implements Maintenance_DetailsBO {

    Maintenance_DetailsDAO maintenanceDetailsDAO = (Maintenance_DetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MAINTENANCEDETAILS);
    @Override
    public String getVid(String mid) throws SQLException, ClassNotFoundException {
        return maintenanceDetailsDAO.getVId(mid);
    }
}
