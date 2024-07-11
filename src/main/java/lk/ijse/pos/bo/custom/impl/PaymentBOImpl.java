package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.PaymentBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.PaymentDAO;
import lk.ijse.pos.dto.PaymentDTO;
import lk.ijse.pos.entity.Payment;

import java.sql.SQLException;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public boolean add(PaymentDTO payment) throws SQLException, ClassNotFoundException {
        return paymentDAO.add(new Payment(payment.getP_id(), payment.getAmount(), payment.getDate(), payment.getPayment_method()));
    }

    @Override
    public Payment search(String pId) throws SQLException, ClassNotFoundException {
        return paymentDAO.search(pId);
    }

    @Override
    public boolean delete(String pid) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(pid);
    }

}
