package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDao;
import lk.ijse.pos.entity.Reservation;
import lk.ijse.pos.view.tdm.ReservationTM;

import java.sql.SQLException;

public interface ReservationDAO extends CrudDao<Reservation> {
    String getCurrentId() throws SQLException, ClassNotFoundException;

}
