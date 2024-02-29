package rpg.user.store;

import rpg.item.dto.Item;

import java.util.*;

/**
 * 유저가 소지한 Item을 저장하기 위한 Inventory
 * 해당 클래스는 타입 안전 이종 컨테이너 패턴의 변형체이다.
 * Item Class를 상속받는 모든 Item에 대응하는 List를 저장할 수 있다.
 */
public class InventoryStore {
    private final Map<Class<? extends Item>, List<? extends Item>> userItemStore;

    public InventoryStore() {
        userItemStore = new HashMap<>();
    }

    public <T extends Item> boolean save(T item) {

        @SuppressWarnings("unchecked")
        List<T> itemList = (List<T>) this.userItemStore.get(item.getClass());

        if (itemList == null) {
            itemList = new ArrayList<>();
            itemList.add(item);
            return this.userItemStore.put(item.getClass(), itemList) != null;
        }

        return itemList.add(item);

    }

    public <T extends Item> boolean remove(T item) {

        List<? extends Item> itemList = this.userItemStore.get(item.getClass());
        return itemList != null && itemList.remove(item);
    }

    public <T extends Item> List<T> get(Class<T> clazz) {

        @SuppressWarnings("unchecked")
        List<T> itemList = (List<T>) this.userItemStore.get(clazz);

        return itemList;
    }

    public List<Item> getAll() {
        List<Item> allItemList = new ArrayList<>();
        Collection<List<? extends Item>> itemValues = this.userItemStore.values();

        itemValues.forEach(allItemList::addAll);

        return allItemList;
    }

}
