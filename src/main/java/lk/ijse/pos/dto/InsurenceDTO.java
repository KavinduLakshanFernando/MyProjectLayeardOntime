package lk.ijse.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InsurenceDTO {
    private String I_id;
    private Date stdate;
    private Date enddate;
}
