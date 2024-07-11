package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBo;

import java.sql.SQLException;

public interface Maintenance_DetailsBO extends SuperBo {
    String getVid(String mid) throws SQLException, ClassNotFoundException;
}
