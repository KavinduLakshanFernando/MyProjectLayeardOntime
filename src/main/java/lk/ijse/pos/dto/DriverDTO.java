package lk.ijse.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class DriverDTO {
    private String id;
    private String name;
    private String address;
    private String contact;
    private String vnumber;
    private String status;

}
