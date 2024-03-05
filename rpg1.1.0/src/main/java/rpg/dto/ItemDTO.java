package rpg.dto;

public abstract class ItemDTO {

    private int code;
    private String name;
    private int price;
    private int charm;

    public ItemDTO() {}

    public ItemDTO(int code, String name, int price, int charm) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.charm = charm;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCharm() {
        return charm;
    }

    public void setCharm(int charm) {
        this.charm = charm;
    }



    @Override
    public String toString() {
        return name;
    }
}
