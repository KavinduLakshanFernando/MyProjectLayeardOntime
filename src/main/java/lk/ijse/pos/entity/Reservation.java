package lk.ijse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Reservation {
    private String Re_id;
    private String P_id;
    private String S_id;
    private String Cu_id;

}
