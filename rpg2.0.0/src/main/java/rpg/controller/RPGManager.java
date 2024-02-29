package rpg.controller;


import rpg.item.dto.Clothes;
import rpg.item.dto.Gift;
import rpg.item.dto.Item;
import rpg.npc.dto.NPCDTO;
import rpg.shop.ItemShop;
import rpg.user.dto.UserDTO;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 * RPG 게임의 View에서 요청한 데이터를 처리하는 Controller
 * View(RPGMenu) 모든 요청 데이터는 해당 Controller(RPGManager)에서 책임진다.
 */
public class RPGManager {

    private UserDTO userDTO = new UserDTO();
    private NPCDTO[] npcList = new NPCDTO[]{
            new NPCDTO("금혁수", -20),
            new NPCDTO("구자윤", 0),
            new NPCDTO("조현", 15)
    };

    private final ItemShop<Clothes> clothesItemShop = new ItemShop<>(new ArrayList<>(
            List.of(new Clothes("정장", 100000, 30),
                    new Clothes("셔츠와 청바지", 25000, 5),
                    new Clothes("체크 셔츠에 멜빵바지", 15000, -10),
                    new Clothes("구찌백", 1000000, -1000))));

    private final ItemShop<Gift> giftItemShop = new ItemShop<>(new ArrayList<>(List.of(
            new Gift("꽃다발", 30000, 20),
            new Gift("케이크", 45000, 30),
            new Gift("발가락 양말", 3000, -20),
            new Gift("슈퍼카", 100000000, -1000)
    )));

    public RPGManager() {
    }

    public void setUserName(String name) {
        userDTO.setName(name);
    }

    public void takeMoney(int money) {
        userDTO.addMoney(money);
    }

    public void loseCharm(int charm) {
        userDTO.minusCharm(charm);
    }

    public String getUserInfo() {
        return this.userDTO.toString();
    }

    public String getUserName() {
        return this.userDTO.getName();
    }

    public int getUserMoney() {
        return this.userDTO.getMoney();
    }

    public int getUserCharm() {
        return this.userDTO.getCharm();
    }

    public Item buyItem(int shopType, int index) {

        return switch (shopType) {
            case 1 -> {
                Clothes buyClothes = this.clothesItemShop.sellItem(index);
                this.userDTO.obtainItem(buyClothes);
                yield buyClothes;
            }
            case 2 -> {
                Gift buyGift = this.giftItemShop.sellItem(index);
                this.userDTO.obtainItem(buyGift);
                yield buyGift;
            }
            default -> throw new InputMismatchException();
        };
    }


    public void equipItem(Clothes clothes) {
        userDTO.equipItem(clothes);
    }

    public Item getEquippedItem() {
        return userDTO.getEquippedItem();
    }


    public List<Item> getUserItemList() {
        return this.userDTO.getHaveAllItemList();
    }

    public List<Gift> getUserGiftList() {
        return this.userDTO.getHaveGiftList();
    }

    public List<Clothes> getUserClothesList() {
        return this.userDTO.getHaveClothesList();
    }

    public List<? extends Item> getItemShopItemList(int type) {
        return switch (type) {
            case 1 -> getClothesShopItemList();
            case 2 -> getGiftShopItemList();
            default -> throw new InputMismatchException();
        };

    }

    public List<Gift> getGiftShopItemList() {
        return this.giftItemShop.getItemList();
    }

    public List<Clothes> getClothesShopItemList() {
        return this.clothesItemShop.getItemList();
    }


    public NPCDTO[] getNpcList() {
        return this.npcList;
    }

    public Item getEqItElement() {
        return userDTO.getEquippedItem();
    }

    public void plusUserMoney(int money) {
        userDTO.addMoney(money);
    }

    public void minusUserMoney(int money) {
        userDTO.minusMoney(money);
    }

    public void plusNPCLike(NPCDTO selectedNPC, int like) {
        selectedNPC.setLike(selectedNPC.getLike() + like);
    }

    public void minusNPCLike(NPCDTO selectedNPC, int like) {
        selectedNPC.setLike(selectedNPC.getLike() - like);
    }

    public boolean presentGift(Gift item) {
        return userDTO.loseItem(item);
    }



}
