package lk.ijse.dto.tm;

public class CustomerTm {
    private String id;
    private String address;
    private String name;
    private String tel;

    public CustomerTm() {
    }

    public CustomerTm(String id, String address, String name, String tel) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.tel = tel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
