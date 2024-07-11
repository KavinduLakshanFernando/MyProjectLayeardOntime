package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.entity.Payment;
import lk.ijse.pos.dao.custom.PaymentDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {



    @Override
    public boolean add(Payment payment) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into Payment values (?,?,?,?)", payment.getP_id(), payment.getAmount(), payment.getDate(), payment.getPayment_method());
    }

    @Override
    public Payment search(String pId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * from payment where P_id = ?", pId);

        if (resultSet.next()){
            String pid = resultSet.getString(1);
            String amount = resultSet.getString(2);
            String date = resultSet.getString(3);
            String pmethod = resultSet.getString(4);

            return new Payment(pid, amount, date, pmethod);
        }
        return null;
    }

    @Override
    public boolean delete(String pid) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(" delete from payment where P_id = ?", pid);
    }






    @Override
    public boolean update(Payment entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }


}
