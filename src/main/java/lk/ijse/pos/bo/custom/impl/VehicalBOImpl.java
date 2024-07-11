package lk.ijse.pos.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.pos.bo.custom.VehicleBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.InsuranceDAO;
import lk.ijse.pos.dao.custom.VehicleDAO;
import lk.ijse.pos.dao.custom.impl.InsuranceDAOImpl;
import lk.ijse.pos.dao.custom.impl.VehicleDAOImpl;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.InsurenceDTO;
import lk.ijse.pos.dto.VehicleDTO;
import lk.ijse.pos.entity.Insurence;
import lk.ijse.pos.entity.Vehicle;
import lk.ijse.pos.view.tdm.VehicleTMDetails;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicalBOImpl implements VehicleBO {

    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);
    InsuranceDAO insuranceDAO = (InsuranceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INSURENCE);
   // InsuranceDAOImpl insuranceDAO = new InsuranceDAOImpl();
   // VehicleDAOImpl vehicleDAO = new VehicleDAOImpl();
    public boolean regiVehicle(InsurenceDTO insurence, VehicleDTO vehicle) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getDbConnection().getConnection();
        connection.setAutoCommit(false);
        try {
            boolean isSaved = insuranceDAO.add(new Insurence(insurence.getI_id(),insurence.getStdate(),insurence.getEnddate()));
            if (isSaved) {
                boolean issaved2 = vehicleDAO.add(new Vehicle(vehicle.getId(), vehicle.getModel(), vehicle.getColur(), vehicle.getIId(), vehicle.getStatus()));
                if (issaved2) {
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        }catch (Exception e){
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public VehicleDTO searchVehicle(String id) throws SQLException, ClassNotFoundException {
        Vehicle vehicle = vehicleDAO.search(id);
        return new VehicleDTO(vehicle.getId(), vehicle.getModel(), vehicle.getColur(), vehicle.getIId(), vehicle.getStatus());

    }

    public boolean delete(String id, String iid) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getDbConnection().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isdelete = vehicleDAO.delete(id);
            if (isdelete){
                boolean isdelete2 = insuranceDAO.delete(iid);
                if (isdelete2){
                    connection.commit();
                    return true;
                }
            }else {
                connection.rollback();
                return false;
            }
        } catch (SQLException e) {
            return false;
        }finally {
            connection.setAutoCommit(true);
        }
        return false;
    }

    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return vehicleDAO.getIdlist();
    }


}
