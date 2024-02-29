package rpg.item.dto;

public class Clothes extends Item {

    public Clothes() {
    }

    public Clothes(String name, int price, int charm) {
        super(name, price, charm);
    }

    @Override
    public String toString() {
        return "옷 : " +
                this.getName() + " / " +
                this.getPrice() + " / " +
                "매력도 + " + this.getCharm();
    }
}
