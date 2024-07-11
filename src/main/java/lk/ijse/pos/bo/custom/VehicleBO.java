package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBo;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.DriverDTO;
import lk.ijse.pos.dto.VehicleDTO;
import lk.ijse.pos.entity.Driver;
import lk.ijse.pos.view.tdm.VehicleTMDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VehicleBO extends SuperBo {


     List<String> getIds() throws SQLException, ClassNotFoundException;

}
