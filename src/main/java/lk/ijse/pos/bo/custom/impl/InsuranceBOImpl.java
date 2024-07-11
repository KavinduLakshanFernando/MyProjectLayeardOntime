package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.InsuranceBo;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.InsuranceDAO;
import lk.ijse.pos.dao.custom.impl.InsuranceDAOImpl;
import lk.ijse.pos.dto.InsurenceDTO;
import lk.ijse.pos.entity.Insurence;

import java.sql.SQLException;

public class InsuranceBOImpl implements InsuranceBo {
    InsuranceDAO insuranceDAO = (InsuranceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INSURENCE);


    //InsuranceDAOImpl insuranceDAO = new InsuranceDAOImpl();
    public InsurenceDTO seachdata(String iId) throws SQLException, ClassNotFoundException {
        Insurence insurence = insuranceDAO.search(iId);
        return new InsurenceDTO(insurence.getI_id(),insurence.getStdate(),insurence.getEnddate());

    }
}
