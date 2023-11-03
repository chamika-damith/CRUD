package lk.ijse.dto;

public class ItemDto {
    private String code;
    private String description;
    private String price;
    private String qty;

    public ItemDto(String code, String description, String price,String qty ) {
        this.code = code;
        this.description = description;
        this.qty = qty;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getUnitPrice() {
        return price;
    }

    public void setUnitPrice(String unitPrice) {
        this.price = unitPrice;
    }

    public ItemDto() {
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice='" + price + '\'' +
                ", qty='" + qty + '\'' +
                '}';
    }
}
