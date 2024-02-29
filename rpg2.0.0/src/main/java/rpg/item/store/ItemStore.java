package rpg.item.store;

import rpg.item.dto.Item;
import rpg.util.TypeReference;

import java.lang.reflect.Type;
import java.util.*;

/**
 * 해당 Class는 원래 List<Item> 객체를 저장하기 위해 정의하였다.
 * 타입 안전 이종 컨테이너 패턴을 Super Type Token을 사용하여 구현했다.
 * 그러나 해당 클래스는 List를 저장하거나 가져올 때, 반드시 Super Type Token을 전달해야 한다.
 * 현재 user.dto 패키지에 List<Item>을 담는 특화 Class InventoryStore가 있기 때문에
 * 해당 Class와 InventoryStore Class 중 용도에 맞는 것을 신중히 결정하는 것이 좋다.
 * 해당 클래스를 조금 더 개량하거나, 일단은 InventoryStore Class를 사용하자.
 */
public class ItemStore {

    private final Map<Type, List<? extends Item>> itemStore = new HashMap<>();

    public ItemStore() {

    }

    public <T extends List<? extends Item>> boolean save(T itemList, TypeReference<T> typeReference) {
        return itemStore.put(typeReference.getType(), itemList) != null;
    }

    public <T extends List<? extends Item>> T get(TypeReference<T> typeReference) {

        @SuppressWarnings("unchecked")
        T itemList = (T) itemStore.get(typeReference.getType());

        return itemList;
    }

    public List<Item> getAll() {
        List<Item> allItemList = new ArrayList<>();
        this.itemStore.values().forEach(allItemList::addAll);

        return allItemList;
    }


}
