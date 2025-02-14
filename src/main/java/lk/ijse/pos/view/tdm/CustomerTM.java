package lk.ijse.pos.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerTM {
    private String id;
    private String name;
    private String address;
    private String phone;
}
