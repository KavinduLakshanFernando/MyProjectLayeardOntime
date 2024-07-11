package lk.ijse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Dashboard {
    private String CustomerCount;
    private String ReservationCount;
    private String DriverCount;
    private String VehicleCount;

}
