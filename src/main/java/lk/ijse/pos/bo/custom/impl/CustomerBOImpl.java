package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.CustomerBo;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBo {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomer = new ArrayList<>();
        ArrayList<Customer> all = customerDAO.getAll();
        for (Customer c : all){
            allCustomer.add(new CustomerDTO(c.getNic(), c.getName(),c.getAddress(), c.getTel()));
        }
        return allCustomer;
    }

    @Override
    public boolean addCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.add(new Customer(customerDTO.getNic(),customerDTO.getName(),customerDTO.getAddress(),customerDTO.getTel()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customerDTO.getName(),customerDTO.getAddress(),customerDTO.getTel(),customerDTO.getNic()));
    }

    @Override
    public Customer searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);

    }

    @Override
    public String nicSearch(String nic) throws SQLException, ClassNotFoundException {
        return customerDAO.nicSearch(nic);
    }
}
