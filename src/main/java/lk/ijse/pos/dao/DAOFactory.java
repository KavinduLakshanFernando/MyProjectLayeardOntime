package lk.ijse.pos.dao;

import lk.ijse.pos.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER, DRIVER, MAINTENANCE, RESERVATION, VEHICLE,INSURENCE,MAINTENANCEDETAILS,SERVICE, QUERY, PAYMENT, RESERVATIONDETAILS,DASHBOARD
    }

    public SuperDao getDAO(DAOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case VEHICLE:
                return new VehicleDAOImpl();
            case INSURENCE:
                return  new InsuranceDAOImpl();
            case DRIVER:
                return new DriverDAOImpl();
            case MAINTENANCE:
                return new MaintenanceDAOImpl();
            case MAINTENANCEDETAILS:
                return new Maintenance_DetailsDAOImpl();
            case SERVICE:
                return new ServiceDAOImpl();
            case QUERY:
                return new QuaryDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case RESERVATIONDETAILS:
                return new Reservation_DetailsDAOImpl();
            case DASHBOARD:
                return new DashboardDAOImpl();
            case RESERVATION:
                return new ReservasionDAOImpl();
            default:
                return null;
        }
    }
}
