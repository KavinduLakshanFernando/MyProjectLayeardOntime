package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBo;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBo extends SuperBo {
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;
    public boolean addCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException ;
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException ;

    public Customer searchCustomer(String id) throws SQLException, ClassNotFoundException;

    String nicSearch(String nic) throws SQLException, ClassNotFoundException;
}
