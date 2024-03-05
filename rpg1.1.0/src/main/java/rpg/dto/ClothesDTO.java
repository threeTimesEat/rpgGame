package rpg.dto;

public class ClothesDTO extends ItemDTO {

    public ClothesDTO() {
    }

    public ClothesDTO(int code, String name, int price, int charm) {
        super(code, name, price, charm);
    }

    @Override
    public String toString() {
        return "옷 : " +
                this.getName() + " / " +
                this.getPrice() + " / " +
                "매력도 + " + this.getCharm();
    }
}
