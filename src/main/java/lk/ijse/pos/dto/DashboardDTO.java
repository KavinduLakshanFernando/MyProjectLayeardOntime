package lk.ijse.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class DashboardDTO {
    private String CustomerCount;
    private String ReservationCount;
    private String DriverCount;
    private String VehicleCount;

}
