package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.SuperDao;
import lk.ijse.pos.entity.Reservation_Details;

import java.sql.SQLException;

public interface Reservation_DetailsDAO extends SuperDao {
    boolean add(Reservation_Details reservationDetails) throws SQLException, ClassNotFoundException;
}
