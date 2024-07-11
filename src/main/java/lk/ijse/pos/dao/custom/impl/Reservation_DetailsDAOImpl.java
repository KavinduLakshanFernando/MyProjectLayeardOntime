package lk.ijse.pos.dao.custom.impl;



import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.Reservation_DetailsDAO;
import lk.ijse.pos.entity.Reservation_Details;

import java.sql.SQLException;

public class Reservation_DetailsDAOImpl implements Reservation_DetailsDAO {



    @Override
    public boolean add(Reservation_Details reservationDetails) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into reservation_details values (?,?,?)", reservationDetails.getRe_id(), reservationDetails.getV_id(), reservationDetails.getData());
    }
}
