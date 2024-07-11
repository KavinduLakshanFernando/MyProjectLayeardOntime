package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBo;
import lk.ijse.pos.dto.PaymentDTO;
import lk.ijse.pos.dto.ReservationDTO;
import lk.ijse.pos.dto.Reservation_DetailsDTO;
import lk.ijse.pos.entity.Reservation;
import lk.ijse.pos.view.tdm.ReservationTM;

import java.sql.SQLException;
import java.util.List;

public interface ReservationBO extends SuperBo {
    boolean placeReservation(PaymentDTO paymentDTO, ReservationDTO reservationDTO, Reservation_DetailsDTO reservationDetailsDTO, String rdate) throws SQLException, ClassNotFoundException;

    boolean add(ReservationDTO reservation) throws SQLException, ClassNotFoundException;

    String getCurrentId() throws SQLException, ClassNotFoundException;

    Reservation search(String Rid) throws SQLException, ClassNotFoundException;

    boolean delete(String rid) throws SQLException, ClassNotFoundException;
}
