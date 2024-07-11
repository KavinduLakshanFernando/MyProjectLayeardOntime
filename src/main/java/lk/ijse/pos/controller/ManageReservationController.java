package lk.ijse.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.DriverBO;
import lk.ijse.pos.bo.custom.ReservationBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.*;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.PaymentDTO;
import lk.ijse.pos.dto.ReservationDTO;
import lk.ijse.pos.dto.Reservation_DetailsDTO;
import lk.ijse.pos.entity.Payment;
import lk.ijse.pos.entity.Reservation;
import lk.ijse.pos.entity.Reservation_Details;
import lk.ijse.pos.view.tdm.ReservationTM;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ManageReservationController {

    public TextField reservationid;
    public TextField txtpaymentid;
    public TextField customerNic;
    public ComboBox ServiceCmb;
    public TextField txtSearchReservation;
    public ComboBox vehicleNumbercmb;
    public ComboBox paymentMethodcmb;
    public TextField PaymentAmount;
    public DatePicker PaymentDate;
    public DatePicker ReservationDate;
    public Rectangle ReservationType;
    public Text lblCustomerName;

    @FXML
    private TableView<ReservationTM> tblReservation;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colCustomerNic;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colPaymentType;

    @FXML
    private TableColumn<?, ?> colReservationId;

    @FXML
    private TableColumn<?, ?> colServiceType;

    @FXML
    private TableColumn<?, ?> colVehicleId;


    ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION);
    QuaryDAO quaryDAO = (QuaryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ServiceDAO serviceDAO = (ServiceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SERVICE);
    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);


    public void initialize() throws ClassNotFoundException {
        getVehicleCmbValues();
        setPaymentType();
        setServiceCmb();
        getCurrentReservationId();
        setCellValueFactory();
        loadAllReservation();
    }

    private void loadAllReservation() {
        ObservableList<ReservationTM> obList = FXCollections.observableArrayList();

        try {
            List<ReservationTM> resList = quaryDAO.getAllReservation();
            for (ReservationTM res : resList) {
                ReservationTM tm = new ReservationTM(
                        res.getRe_id(),
                        res.getP_id(),
                        res.getVehicleId(),
                        res.getServiceType(),
                        res.getCusNic(),
                        res.getName(),
                        res.getAmount(),
                        res.getPaymentType(),
                        res.getDate()
                );
                obList.add(tm);
            }

            tblReservation.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory(){
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("P_id"));
        colReservationId.setCellValueFactory(new PropertyValueFactory<>("Re_id"));
        colServiceType.setCellValueFactory(new PropertyValueFactory<>("serviceType"));
        colCustomerNic.setCellValueFactory(new PropertyValueFactory<>("cusNic"));
        colPaymentType.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
        colVehicleId.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
    }


    private void getCurrentReservationId() {
        try {
            String currentId = reservationBO.getCurrentId();

            String nextReservationId = generateNextReservationId(currentId);
            reservationid.setText(nextReservationId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextReservationId(String currentId) {
        if(currentId != null && !currentId.isEmpty()) {
            String[] split = currentId.split("R");
            if (split.length > 1) {
                int idNum = Integer.parseInt(split[1]);
                return "R0" + String.format("%02d", ++idNum);
            }
        }
        return "R001"; // Default starting ID
    }

    public void btnCusNicSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String nic = customerNic.getText();

            String  name = customerDAO.nicSearch(nic);
            if (name != null) {
                lblCustomerName.setText(name);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
            }
    }


    private void setServiceCmb() throws ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = serviceDAO.getNameList();
            for(String id : idList) {
                obList.add(id);
            }
            ServiceCmb.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void getVehicleCmbValues() throws ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = vehicleDAO.getIdlist();
            for(String id : idList) {
                obList.add(id);
            }
            vehicleNumbercmb.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setPaymentType() {
        ObservableList<String> colurlist = FXCollections.observableArrayList(
                "Cash", "Credit", "Debit", "Check"
        );
        paymentMethodcmb.setItems(colurlist);
    }

    public void AddreservatuinOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String rid = reservationid.getText();
        String pid = txtpaymentid.getText();
        String cid = customerNic.getText();
        String servicename = (String) ServiceCmb.getValue();
        String vid = (String) vehicleNumbercmb.getValue();
        String ptype = (String) paymentMethodcmb.getValue();
        String amount= PaymentAmount.getText();
        String pdate = String.valueOf(PaymentDate.getValue());
        String rdate = String.valueOf(ReservationDate.getValue());

        PaymentDTO paymentDTO = new PaymentDTO(pid, amount,pdate, ptype);
        String sid = serviceDAO.getId(servicename);
        ReservationDTO reservationDTO = new ReservationDTO(rid, pid, sid, cid);
        Reservation_DetailsDTO reservationDetailsDTO = new Reservation_DetailsDTO(rid, vid, rdate);



        boolean placeReservation = reservationBO.placeReservation(paymentDTO, reservationDTO, reservationDetailsDTO, rdate);
        if (placeReservation) {
            new Alert(Alert.AlertType.CONFIRMATION,"Data Saved").show();
            clear();
            initialize();
        } else {
            new Alert(Alert.AlertType.ERROR,"data saved failed").show();
        }


    }

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    public void DeletereservatuinOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String rid = reservationid.getText();
        String pid = txtpaymentid.getText();

        Connection connection = DBConnection.getDbConnection().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isdelete = reservationBO.delete(rid);
            if(isdelete){
                boolean isdelete2 = paymentDAO.delete(pid);
                if(isdelete2){
                    connection.commit();
                    new Alert(Alert.AlertType.CONFIRMATION,"Reservation deleted").show();
                    loadAllReservation();
                }
            }else {
                connection.rollback();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,"Reservation Not deleted").show();
        }finally {
            connection.setAutoCommit(true);
        }
    }

    public void searchOnActionReservation(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String Rid = reservationid.getText();
        Reservation reservation = reservationBO.search(Rid);

        reservationid.setText(reservation.getRe_id());
        customerNic.setText(reservation.getCu_id());
        txtpaymentid.setText(reservation.getP_id());

        Payment payment = paymentDAO.search(reservation.getP_id());
        PaymentAmount.setText(payment.getAmount());
        PaymentDate.setValue(LocalDate.parse(payment.getDate()));
        paymentMethodcmb.setValue(payment.getPayment_method());

    }

    public void clearReservationOnAction(ActionEvent actionEvent) {
        clear();
    }

    private void clear() {
        reservationid.clear();
        txtpaymentid.clear();
        customerNic.clear();
        txtSearchReservation.clear();
        PaymentAmount.clear();

        ServiceCmb.setValue(null);
        vehicleNumbercmb.setValue(null);
        ReservationDate.setValue(null);
        paymentMethodcmb.setValue(null);
        PaymentDate.setValue(null);
        lblCustomerName.setText(null);

    }

    public void btnBillOnAction(ActionEvent actionEvent) throws SQLException, JRException, ClassNotFoundException {
        printBill();
    }

    private void printBill() throws JRException, SQLException, ClassNotFoundException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/report/reservationKavindu1.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String, Object> data = new HashMap<>();
        data.put("Re_id", reservationid.getText());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, DBConnection.getDbConnection().getConnection());
        JasperViewer.viewReport(jasperPrint, false);
    }
}
