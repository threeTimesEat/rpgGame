package rpg.itemshop;

import rpg.dto.ItemDTO;

public abstract class ItemShop {

    protected ItemDTO[] itemDTOList;

    protected ItemShop() {
    }
    public ItemShop(ItemDTO[] itemDTOList) {
        this.itemDTOList = itemDTOList;
    }

    public abstract ItemDTO[] getItemList();

    public abstract ItemDTO sellItem(int index);



}
