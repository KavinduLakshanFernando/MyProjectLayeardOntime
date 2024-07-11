package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBo;
import lk.ijse.pos.dto.MaintenanceDTO;
import lk.ijse.pos.dto.Maintenance_DetailsDTO;

import java.sql.SQLException;
import java.util.List;

public interface MaintenanceBO extends SuperBo {
    List<MaintenanceDTO> getAll() throws SQLException, ClassNotFoundException;

    String getCurrentId() throws SQLException, ClassNotFoundException;

    Boolean saveMaintenance(MaintenanceDTO maintenance, Maintenance_DetailsDTO maintenanceDetails) throws SQLException, ClassNotFoundException;

    boolean delete(String mid) throws SQLException, ClassNotFoundException;

    MaintenanceDTO searchData(String mid) throws SQLException, ClassNotFoundException;
}
