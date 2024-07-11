package lk.ijse.pos.dao;

import lk.ijse.pos.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDao <T> extends SuperDao{
    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

    public boolean add(T entity) throws SQLException, ClassNotFoundException ;

    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public boolean update(T entity) throws SQLException, ClassNotFoundException ;

    public T search(String id) throws SQLException, ClassNotFoundException;

}
