package rpg.dto;

public class GiftDTO extends ItemDTO {

    public GiftDTO() {
    }

    public GiftDTO(int code, String name, int price, int charm) {
        super(code, name, price, charm);
    }

    @Override
    public String toString() {
        return "선물 : " +
                this.getName() + " / " +
                this.getPrice() + " / " +
                "NPC 호감도 + " + this.getCharm();
    }
}
