package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.ReservationDAO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.entity.Reservation;

import java.sql.*;
import java.util.ArrayList;

public class ReservasionDAOImpl implements ReservationDAO {
    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT Re_id FROM reservation ORDER BY Re_id DESC LIMIT 1");
        if(resultSet.next()) {
            String resId = resultSet.getString(1);
            return resId;
        }
        return null;
    }


    @Override
    public Reservation search( String Rid) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * from reservation");
        if(resultSet.next()){
            String Reid = resultSet.getString(1);
            String pid = resultSet.getString(2);
            String Sid = resultSet.getString(3);
            String cuid = resultSet.getString(4);

            Reservation reservation = new Reservation(Reid, pid, Sid, cuid);
            return reservation;
        }
            return null;
    }

    @Override
    public boolean delete(String rid) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from reservation where Re_id = ?", rid);
    }


    @Override
    public boolean add(Reservation reservation) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into reservation values (?,?,?,?) ",reservation.getRe_id(),reservation.getP_id(),reservation.getS_id(),reservation.getCu_id());
    }





    @Override
    public ArrayList<Reservation> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Reservation entity) throws SQLException, ClassNotFoundException {
        return false;
    }
}
