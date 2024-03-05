package rpg.itemshop;

import rpg.dto.ClothesDTO;
import rpg.dto.ItemDTO;

public class ClothesShop extends ItemShop {

    public ClothesShop() {
        this.itemDTOList = new ClothesDTO[] {
                new ClothesDTO(1, "정장", 100000, 30),
                new ClothesDTO(2, "셔츠와 청바지", 25000, 5),
                new ClothesDTO(3, "체크 셔츠에 멜빵바지", 15000, -10),
                new ClothesDTO(4, "구찌백", 1000000, -1000)
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
