package rpg.itemshop;

import rpg.dto.GiftDTO;
import rpg.dto.ItemDTO;

public class GiftShop extends ItemShop {


    public GiftShop() {
        this.itemDTOList = new GiftDTO[]{
                new GiftDTO(5, "꽃다발", 30000, 20),
                new GiftDTO(6, "케이크", 45000, 30),
                new GiftDTO(7, "발가락 양말", 3000, -20),
                new GiftDTO(8, "슈퍼카", 100000000, -1000)
        };
    }

    @Override
    public ItemDTO[] getItemList() {
        return this.itemDTOList;
    }

    @Override
    public ItemDTO sellItem(int index) {
        return this.itemDTOList[index];
    }


}
