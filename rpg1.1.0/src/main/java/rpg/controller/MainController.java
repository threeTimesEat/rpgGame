package rpg.controller;


import rpg.dto.*;
import rpg.itemshop.ClothesShop;
import rpg.itemshop.GiftShop;
import rpg.itemshop.ItemShop;

import java.util.InputMismatchException;

public class MainController {

//    Scanner sc = new Scanner(System.in);
    DeleteController deleteController = new DeleteController();
    InsertController insertController = new InsertController();
    SelectController selectController = new SelectController();
    UpdateController updateController = new UpdateController();

    private UserDTO userDTO = new UserDTO();

    private BagDTO userBag = new BagDTO();

    private NPCDTO[] npcList = new NPCDTO[]{
            new NPCDTO(1, "금혁수", -20),
            new NPCDTO(2, "구자윤", 0),
            new NPCDTO(3, "조현", 15)
    };

    private ItemShop clothesShop = new ClothesShop();
    private ItemShop giftShop = new GiftShop();

    public MainController() {
        deleteController.initDatabase();
    }

    public void setUserName(String name) {
        userDTO.setName(name);
        updateController.updateName(this.userDTO);
    }
    
    public void takeMoney(int money) {
        updateController.updateMoney(userDTO.getMoney() + money);
    }

    public void loseCharm(int charm) {
        userDTO.minusCharm(charm);
        updateController.updateCharm(userDTO.getCharm() - charm);
    }

    // 해당 메소드에서 유저의 정보를 초기화한다.
    public UserDTO getUserInfo() {
        userDTO = selectController.selectUserInfo();
        userBag = selectController.selectUserBag();
        return this.userDTO;
    }



    public void buyItem(ItemDTO buyItemDTO) {
        System.out.println("호출확인");
        userBag.addItem(buyItemDTO);
    }

    public void equipItem(ClothesDTO buyItem) {
//        userDTO.equipItem(buyItem);
    }

    public ItemDTO getEquippedItem() {
        return null;
    }

    public BagDTO getUserBag() {
        return this.userBag;
    }

    public ItemDTO[] getItemShopItemList(int type) {
        return switch (type) {
            case 1 -> getClothesShopItemList();
            case 2 -> getGiftShopItemList();
            default -> throw new InputMismatchException();
        };
    }

    public GiftDTO[] getGiftShopItemList() {
        GiftDTO[] giftList = selectController.selectGiftList();
        return giftList;
    }

    public ClothesDTO[] getClothesShopItemList() {
        ClothesDTO[] clothesList = selectController.selectClothesList();
        return clothesList;
    }

    public ItemDTO getBuyClothes(int index) {
        ItemDTO item = this.clothesShop.sellItem(index);
        insertController.insertUserItem(item.getCode());
        return item;
    }

    public ItemDTO getBuyGift(int index) {
        ItemDTO item = this.giftShop.sellItem(index);
        insertController.insertUserItem(item.getCode());
        return item;
    }

    public NPCDTO[] getNpcList() {
        return this.npcList;
    }

    public ItemDTO getEqItElement() {
        return null;
    }

    public void plusNPCLike(NPCDTO selectedNPC, int like) {
        selectedNPC.setLike(selectedNPC.getLike() + like);
    }

    public void minusNPCLike(NPCDTO selectedNPC, int like) {
        selectedNPC.setLike(selectedNPC.getLike() - like);
    }

    public void presentGift(ItemDTO itemDTO) {
        userBag.deleteItem(itemDTO);
        deleteController.changeFKSetting();
        deleteController.deleteItem(itemDTO.getCode());
    }

}
