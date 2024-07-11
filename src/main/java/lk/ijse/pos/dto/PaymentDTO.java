package lk.ijse.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PaymentDTO {
     private String P_id;
     private String Amount;
     private String Date;
     private String Payment_method;

}
