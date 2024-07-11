package lk.ijse.pos.entity;

public class Customer {
    private String nic;
    private String Name;
    private String Address;
    private String Tel;

    public Customer() {
    }

    public Customer(String nic, String name, String address, String tel) {
        this.nic = nic;
        Name = name;
        Address = address;
        Tel = tel;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "nic='" + nic + '\'' +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", Tel='" + Tel + '\'' +
                '}';
    }
}
