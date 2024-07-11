package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {

        ArrayList<Customer> allCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer");
        while (rst.next()) {
            Customer customer = new Customer(rst.getString("nic"), rst.getString("Name"),rst.getString("Address"),rst.getString("Tel") );
            allCustomers.add(customer);
        }
        return allCustomers;
    }

    @Override
    public boolean add(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT into customer values(?,?,?,?)",entity.getNic(),entity.getName(),entity.getAddress(),entity.getTel());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM customer WHERE nic = ?",id);
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customer SET Name = ?, Address = ?, Tel = ? WHERE nic = ?",entity.getNic(),entity.getName(),entity.getAddress(),entity.getTel());
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE nic = ?",id);
        if (resultSet.next()) {
            String cus_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);

            Customer customer = new Customer(cus_id, name, address, tel);

            return customer;
        }

        return null;
    }

    @Override
    public String nicSearch(String nic) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT Name FROM customer WHERE nic = ?",nic);
        if(resultSet.next()) {
            String cusName = resultSet.getString(1);

            return cusName;
        }
        return null;
    }
}

