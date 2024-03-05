package rpg.dto;

import java.util.ArrayList;
import java.util.List;

public class BagDTO {

//    private ArrayList<Item> itemList = new ArrayList<>();
    private List<ItemDTO> itemDTOList = new ArrayList<>();
    public BagDTO() {}

    @Deprecated
    public void printBag() {
        for(ItemDTO itemDTO : itemDTOList) {
            System.out.println(itemDTO + "/" + "나의 매력도 강화 : " + itemDTO.getCharm());
        }
    }

    @Deprecated
    public void printGiftItem() {
        for(ItemDTO itemDTO : itemDTOList) {
            if(itemDTO instanceof GiftDTO) {
                System.out.println(itemDTO);
            }
        }
    }

    public List<ItemDTO> getItemList() {
        return this.itemDTOList;
    }

    public List<GiftDTO> getGiftList() {
        List<GiftDTO> giftList = new ArrayList<>();
        for(ItemDTO itemDTO : itemDTOList) {
            if (itemDTO instanceof GiftDTO) giftList.add((GiftDTO) itemDTO);
        }
        return giftList;
    }


    public void addItem(ItemDTO itemDTO) {
        itemDTOList.add(itemDTO);
    }

    public void deleteItem(ItemDTO itemDTO) {
        itemDTOList.remove(itemDTO);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        for (ItemDTO itemDTO : itemDTOList) {
            s.append(itemDTO.getName()).append(", ");
        }

        return s.toString();
    }
}
