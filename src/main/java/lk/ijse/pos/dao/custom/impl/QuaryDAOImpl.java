package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.QuaryDAO;
import lk.ijse.pos.view.tdm.ReservationTM;
import lk.ijse.pos.view.tdm.VehicleTMDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuaryDAOImpl implements QuaryDAO {

    public static List<VehicleTMDetails> getAlll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT vehicle.V_id, vehicle.I_id AS Insurance_ID, vehicle.Color, vehicle.Model, insurance.Start_date, insurance.End_date FROM vehicle JOIN insurance ON vehicle.I_id = insurance.I_id");
        List<VehicleTMDetails> VehicleList = new ArrayList<>();
//        +------+----------------+-------+------+-----------+
//                | V_id | Model          | Color | I_id | Status    |

        while (resultSet.next()){
            String vid = resultSet.getString(1);
            String model = resultSet.getString(2);
            String color = resultSet.getString(3);
            String i_id = resultSet.getString(4);
            Date startDate = resultSet.getDate(5);
            Date endDate = resultSet.getDate(6);


            VehicleTMDetails details = new VehicleTMDetails(vid, model, color, i_id, startDate, endDate);
            VehicleList.add(details);
        }
        return VehicleList;
    }

    @Override
    public List<ReservationTM> getAllReservation() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT reservation.Re_id, reservation.P_id, reservation_Details.V_id, service.S_type, reservation.nic, customer.Name AS customer_name, payment.Amount, payment.Payment_method, reservation_Details.Date AS reservation_date FROM reservation JOIN reservation_Details ON reservation.Re_id = reservation_Details.Re_id JOIN customer ON reservation.nic = customer.nic JOIN payment ON reservation.P_id = payment.P_id JOIN vehicle ON reservation_Details.V_id = vehicle.V_id JOIN service ON reservation.S_id = service.S_id");
        List<ReservationTM> resList = new ArrayList<>();

        while (resultSet.next()) {
            String reId = resultSet.getString(1);
            String paymentId = resultSet.getString(2);
            String vehicleId = resultSet.getString(3);
            String serviceType = resultSet.getString(4);
            String nic = resultSet.getString(5);
            String customerName = resultSet.getString(6);
            double ammount = resultSet.getDouble(7);
            String payType = resultSet.getString(8);
            String date = String.valueOf(resultSet.getDate(9));

            ReservationTM reservationTm = new ReservationTM(reId, paymentId, vehicleId, serviceType, nic, customerName, ammount, payType, date);
            resList.add(reservationTm);
        }
        return resList;
    }
    }
