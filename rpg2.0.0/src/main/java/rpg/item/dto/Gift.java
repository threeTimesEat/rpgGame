package rpg.item.dto;

public class Gift extends Item {

    public Gift() {
    }

    public Gift(String name, int price, int charm) {
        super(name, price, charm);
    }

    @Override
    public String toString() {
        return "선물 : " +
                this.getName() + " / " +
                this.getPrice() + " / " +
                "NPC 호감도 + " + this.getCharm();
    }
}
