package lk.ijse.pos.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
@EqualsAndHashCode

public class MaintenanceTM {
    private String V_id;
    private String M_id;
    private String desc;
    private String date;
    private String cost;
}
