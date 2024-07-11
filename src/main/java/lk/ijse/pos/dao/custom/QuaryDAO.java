package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.SuperDao;
import lk.ijse.pos.view.tdm.ReservationTM;

import java.sql.SQLException;
import java.util.List;

public interface QuaryDAO extends SuperDao {


    List<ReservationTM> getAllReservation() throws SQLException, ClassNotFoundException;
}
