package lk.ijse.pos.bo.custom.impl;
import javafx.scene.control.Alert;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.ReservationBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.PaymentDAO;
import lk.ijse.pos.dao.custom.ReservationDAO;
import lk.ijse.pos.dao.custom.Reservation_DetailsDAO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.PaymentDTO;
import lk.ijse.pos.dto.ReservationDTO;
import lk.ijse.pos.dto.Reservation_DetailsDTO;
import lk.ijse.pos.entity.Payment;
import lk.ijse.pos.entity.Reservation;
import lk.ijse.pos.entity.Reservation_Details;

import java.sql.Connection;
import java.sql.SQLException;

public class ReservationBOImpl implements ReservationBO {

    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
   // ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    Reservation_DetailsDAO reservationDetailsDAO = (Reservation_DetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATIONDETAILS);



    @Override
    public boolean placeReservation(PaymentDTO paymentDTO, ReservationDTO reservationDTO, Reservation_DetailsDTO reservationDetailsDTO, String rdate) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getDbConnection().getConnection();
        connection.setAutoCommit(false);

        try{
            boolean issaved = paymentDAO.add(new Payment(paymentDTO.getP_id(), paymentDTO.getAmount(), paymentDTO.getDate(), paymentDTO.getPayment_method()));
            if (issaved){
                boolean issaved2 = reservationDAO.add(new Reservation(reservationDTO.getRe_id(), reservationDTO.getP_id(), reservationDTO.getS_id(), reservationDTO.getCu_id()));
                if (issaved2){
                    boolean issaved3 = reservationDetailsDAO.add(new Reservation_Details(reservationDetailsDTO.getRe_id(), reservationDetailsDTO.getV_id(), rdate));
                    if (issaved3){
                        connection.commit();
                        return true;
                    }else {
                        connection.rollback();
                        return false;
                    }
                }else {
                    connection.rollback();
                    return false;
                }
            }else {
                connection.rollback();
                return false;
            }
        }catch (Exception e ){
            connection.rollback();
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }finally{
            connection.setAutoCommit(true);
        }

        return false;
    }

    @Override
    public boolean add(ReservationDTO reservation) throws SQLException, ClassNotFoundException {
        return reservationDAO.add(new Reservation(reservation.getRe_id(), reservation.getP_id(), reservation.getS_id(), reservation.getCu_id()));
    }


    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return reservationDAO.getCurrentId();
    }

    @Override
    public boolean delete(String rid) throws SQLException, ClassNotFoundException {
        return reservationDAO.delete(rid);
    }

    @Override
    public Reservation search(String rId) throws SQLException, ClassNotFoundException {
        return reservationDAO.search(rId);
    }

}
