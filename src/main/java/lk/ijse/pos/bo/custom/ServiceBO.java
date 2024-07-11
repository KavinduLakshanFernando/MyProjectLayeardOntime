package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBo;

import java.sql.SQLException;
import java.util.List;

public interface ServiceBO extends SuperBo {
    List<String> getNamelist() throws SQLException, ClassNotFoundException;
}
