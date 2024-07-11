package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBo;
import lk.ijse.pos.dto.PaymentDTO;
import lk.ijse.pos.entity.Payment;

import java.sql.SQLException;

public interface PaymentBO extends SuperBo {
    boolean add(PaymentDTO payment) throws SQLException, ClassNotFoundException;

    Payment search(String pId) throws SQLException, ClassNotFoundException;

    boolean delete(String pid) throws SQLException, ClassNotFoundException;
}
