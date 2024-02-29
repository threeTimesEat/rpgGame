package rpg.item.dto;

import java.lang.reflect.Array;
import java.util.List;

/**
 * 해당 게임에서 Item으로 취급되는 모든 객체는 반드시 해당 클래스를 상속받아야 한다.
 * 또한 Item 객체는 인스턴스화를 금지하기 위해 abstract class로 정의하였다.
 */
public abstract class Item {

    private String name;
    private int price;
    private int charm;

    public Item() {}

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

    public Item(String name, int price, int charm) {
        this.name = name;
        this.price = price;
        this.charm = charm;
    }

    @Override
    public String toString() {
        return name;
    }
}
