package lk.ijse.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class MaintenanceDTO {
    private String M_id;
    private String V_id;
    private String Description;
    private String cost;
    private String date;
}
