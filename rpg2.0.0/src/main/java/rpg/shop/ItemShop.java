package rpg.shop;

import rpg.item.dto.Item;
import rpg.item.store.ItemStore;
import rpg.util.TypeReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Item Class를 상속받는 모든 Item에 대응하는 Generic Class
 * Item 상점의 구현이 필요한 경우, 해당 Class의 사용을 고려할 수 있다.
 * @param <T> 판매할 Item의 종류
 */
public class ItemShop<T extends Item> {

    private List<T> itemList;

    /**
     * 외부에서 생성된 List를 통해 초기화하는 경우
     * @param itemList ItemShop의 초기 값으로 사용할 List 주소값
     */
    public ItemShop(List<T> itemList) {
        this.itemList = itemList;
    }

    /**
     * ItemStore를 연결하여 값을 가져올 경우
     * @param itemStore 연결할 ItemStore 객체의 주소 값
     */
    public ItemShop(ItemStore itemStore) {
        // 연결된 ItemStore에서 T 타입의 ItemList를 가져온다
        this.itemList = itemStore.get(new TypeReference<List<T>>() {});

        // 만약 연결된 Itemstore에 T 타입 리스트 객체가 없으면
        if (this.itemList == null) {
            // 새로운 ArrayList를 Itemstore에 연결한다.
            this.itemList = new ArrayList<>();
            itemStore.save(this.itemList, new TypeReference<List<T>>() {});
        }
    }

    public List<T> getItemList() {
        return this.itemList;
    }

    public T sellItem(int index) {
        return this.itemList.get(index);
    }



}
