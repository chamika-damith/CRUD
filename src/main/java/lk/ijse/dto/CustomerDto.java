package lk.ijse.dto;

public class CustomerDto {
    private String customer_id;
    private String name;
    private String address;
    private String  tel;

    public CustomerDto(String customer_id, String name, String address, String tel) {
        this.customer_id = customer_id;
        this.name = name;
        this.address = address;
        this.tel = tel;
    }

    public CustomerDto() {
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "customer_id='" + customer_id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
