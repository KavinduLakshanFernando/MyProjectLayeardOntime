package lk.ijse.pos.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class VehicleDTO {
    private String id ;
    private String model;
    private String colur;
    private String iId;
    private String status;
}
