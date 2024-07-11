package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.MaintenanceBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.MaintenanceDAO;
import lk.ijse.pos.dao.custom.Maintenance_DetailsDAO;
import lk.ijse.pos.dao.custom.impl.MaintenanceDAOImpl;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.MaintenanceDTO;
import lk.ijse.pos.dto.Maintenance_DetailsDTO;
import lk.ijse.pos.entity.Maintenance;
import lk.ijse.pos.entity.Maintenance_Details;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceBOImpl implements MaintenanceBO {

MaintenanceDAO maintenanceDAO = (MaintenanceDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MAINTENANCE);
    Maintenance_DetailsDAO maintenanceDetailsDAO = (Maintenance_DetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MAINTENANCEDETAILS);


    @Override
    public List<MaintenanceDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Maintenance> list = maintenanceDAO.getAll();
        ArrayList<MaintenanceDTO> list2 = new ArrayList<>();
        for (Maintenance m : list){
            list2.add(new MaintenanceDTO(m.getM_id(), m.getV_id(), m.getDescription(), m.getCost(), m.getDate()));

        }
        return list2;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return maintenanceDAO.getCurrentId();
    }

    @Override
    public Boolean saveMaintenance(MaintenanceDTO maintenance, Maintenance_DetailsDTO maintenanceDetails) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        connection.setAutoCommit(false);
        try {
            boolean issaved = maintenanceDAO.add(new Maintenance(maintenance.getM_id(), maintenance.getV_id(), maintenance.getDescription(), maintenance.getCost(), maintenance.getDate()));
            if (issaved){
               boolean issaved2 = maintenanceDetailsDAO.add(new Maintenance_Details(maintenanceDetails.getV_id(), maintenance.getM_id()));
                if (issaved2){
                    connection.commit();
                    return true;

                }else {

                    connection.rollback();
                    return false;
                }
            }else {
                connection.rollback();
                return false;
            }
        }catch (Exception e){
            connection.rollback();
            return false;
        }finally{
            connection.setAutoCommit(true);
        }
   }

    @Override
    public boolean delete(String mid) throws SQLException, ClassNotFoundException {
        return maintenanceDAO.delete(mid);
    }

    @Override
    public MaintenanceDTO searchData(String mid) throws SQLException, ClassNotFoundException {
        Maintenance entity = maintenanceDAO.search(mid);
        return new MaintenanceDTO(entity.getM_id(),entity.getV_id(),entity.getDescription(),entity.getCost(),entity.getDate());

    }
}
