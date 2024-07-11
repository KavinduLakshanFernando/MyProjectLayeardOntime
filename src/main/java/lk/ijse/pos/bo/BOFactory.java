package lk.ijse.pos.bo;

import lk.ijse.pos.bo.custom.ReservationBO;
import lk.ijse.pos.bo.custom.impl.*;
import lk.ijse.pos.dao.custom.impl.Maintenance_DetailsDAOImpl;
import lk.ijse.pos.view.tdm.ReservationTM;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,VEHICAL,INSURANCE,DRIVER,MAINTENNCE,MAINTENANCEDETAIL,RESERVATION,SERVICE,DASHBOARD
    }

    //Object creation logic for BO objects
    public SuperBo getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBOImpl();
            case VEHICAL:
                return new VehicalBOImpl();
            case INSURANCE:
                return new InsuranceBOImpl();
            case DRIVER:
                return new DriverBOImpl();
            case MAINTENNCE:
                return new MaintenanceBOImpl();
            case MAINTENANCEDETAIL:
                return new Maintenance_DetailsBOImpl();
            case RESERVATION:
                return new ReservationBOImpl();
            case SERVICE:
                return new ServiceBOImpl();
            case DASHBOARD:
                return new DashboardBOImpl();
            default:
                return null;

        }
    }
}
