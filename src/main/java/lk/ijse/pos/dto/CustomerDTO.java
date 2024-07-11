package lk.ijse.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    private String nic;
    private String Name;
    private String Address;
    private String Tel;
}
