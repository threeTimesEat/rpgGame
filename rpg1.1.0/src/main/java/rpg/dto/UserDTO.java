package rpg.dto;

import java.util.InputMismatchException;

public class UserDTO {

    private int code = 1;
    private String name;
    private int charm;
//    private BagDTO bagDTO = new BagDTO();
//    private ItemDTO equippedItemDTO;
    private int bagCode;
    private int equippedItemCode;
    private int money;

    public UserDTO() {
    }

    public UserDTO(int code, String name, int charm, int bagCode, int equippedItemCode, int money) {
        this.code = code;
        this.name = name;
        this.charm = charm;
        this.bagCode = bagCode;
        this.equippedItemCode = equippedItemCode;
        this.money = money;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCharm() {
        return charm;
    }

    public void setCharm(int charm) {
        this.charm = charm;
    }

    public int getBagCode() {
        return bagCode;
    }

    public void setBagCode(int bagCode) {
        this.bagCode = bagCode;
    }

    public int getEquippedItemCode() {
        return equippedItemCode;
    }

    public void setEquippedItemCode(int equippedItemCode) {
        this.equippedItemCode = equippedItemCode;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void addMoney(int money) {
        this.setMoney(this.getMoney() + money);
    }

    public void minusMoney(int money) {
        this.setMoney(this.getMoney() - money);
    }

    public void addCharm(int charm) {
        this.setCharm(this.getCharm() + charm);
    }

    public void minusCharm(int charm) {
        this.setCharm(this.getCharm() - charm);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", charm=" + charm +
                ", bagCode=" + bagCode +
                ", equippedItemCode=" + equippedItemCode +
                ", money=" + money +
                '}';
    }

    //    @Override
//    public String toString() {
//        return "";
////        return "■ ■ 내 상태 ■ ■ " + "\n"
////                + "[이름] : " + name + "\n"
////                + "[나의 매력도] : " + charm + "\n"
////                + "[소지품] : " + bagDTO.toString() + "\n"
////                + "[착용한 옷] : " +  (equippedItemDTO == null ? "없음" : equippedItemDTO) + "\n"
////                + "[소지한 돈] : " + money + "원";
//    }
}
