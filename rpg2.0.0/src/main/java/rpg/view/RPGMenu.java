package rpg.view;


import rpg.controller.RPGManager;
import rpg.item.dto.Clothes;
import rpg.item.dto.Gift;
import rpg.item.dto.Item;
import rpg.npc.dto.NPCDTO;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * 해당 게임의 View 역할을 하는 Class 객체
 * RPG Game의 출력을 담당하며, 데이터의 처리는 Controller로 기능하는 RPGManager가 담당한다.
 * 따라서 View를 담당하는 RPGMenu에서 데이터를 처리할 일이 있으면 RPGManager에 위임한다.
 */
public class RPGMenu {

    public RPGMenu() {
    }

    // 사용자의 입력을 받을 Scanner 객체
    private Scanner sc = new Scanner(System.in);

    // 데이터의 처리를 담당할 RPGManager
    private RPGManager RPGManager = new RPGManager();


    public void menu() {

        System.out.println("두근두근(?) 연애 시뮬레이션!");
        insertUserName();

        // 유저가 게임을 종료할 때까지 게임을 반복한다.
        while (true) {
            System.out.println("============ 메뉴 ============");
            System.out.println("1. 이름 변경하기");
            System.out.println("2. 내 상태 확인하기");
            System.out.println("3. 소지품 보기");
            System.out.println("4. 대화하기");
            System.out.println("5. 쇼핑하기");
            System.out.println("6. 아르바이트 하기");
            System.out.println("7. 호감도 확인하기");
            System.out.println("8. 종료");
            System.out.println("============================");
            System.out.print("번호를 선택하세요 : ");
            int selectNum = sc.nextInt();
            sc.nextLine();

            // 1~8번의 선택지에 대응하는 Method 실행
            switch (selectNum) {
                case 1 -> insertUserName(); 
                case 2 -> printUserStatus();
                case 3 -> viewUserInventory();
                case 4 -> talkToNPC();
                case 5 -> goToItemShop();
                case 6 -> goToWork();
                case 7 -> printCharm();
                case 8 -> {
                    System.out.println("게임을 종료합니다.");
                    return;
                }
            }
        }
    }

    /**
     * 유저의 이름을 Scanner로 입력받아 변경하는 메소드
     * 유저의 이름을 변경하는 것은 Controller가 담당한다.
     * 경우에 따라 이름을 잘 변경했는지에 대한 값을 Manger에서 받아와야 한다.
     * 따라서 필요에 따라 Manager의 setUserName() Method가 true/false를 반환하도록 수정 필요
     */
    public void insertUserName() {
        System.out.print("이름을 입력하세요 : ");
        String name = sc.nextLine();
        RPGManager.setUserName(name);
    }

    /**
     * 유저의 정보를 Manager로부터 받아와 출력하는 Method
     */
    public void printUserStatus() {
        System.out.println(RPGManager.getUserInfo());
    }

    /**
     * 유저의 인벤토리를 보여주는 메소드
     * 착용중인 아이템을 상단에 먼저 보여준 후, 인벤토리에 있는 아이템을 보여준다.
     * 출력문이 올바른지 확인 필요!
     */
    public void viewUserInventory() {
        System.out.println("착용 중인 아이템 =============");
        System.out.println((RPGManager.getEquippedItem() == null ? "없음" : RPGManager.getEquippedItem()));
        System.out.println("소지품 ======================");
        List<Item> itemList = RPGManager.getUserItemList();

        itemList.forEach(System.out::println);

    }


    /**
     * 누구와 대화할지를 선택하여 대화하는 메소드
     * 또한 대화 선택지에 따라 행동이 바뀌는 로직까지 해당 메소드에서 처리한다.
     */
    public void talkToNPC() {
        printNPCList();
        System.out.println("============================");
        System.out.print("누구랑 대화할까? : ");
        int npcType = sc.nextInt() - 1;
        sc.nextLine();
        NPCDTO selectedNPC = RPGManager.getNpcList()[npcType];
        System.out.println(selectedNPC.getName() + "을/를 만났다!");
        System.out.println("============================");
        System.out.println("[1] 오늘 날씨 어때?");
        System.out.println("[2] 우리 놀러갈래?");
        System.out.println("[3] 어제 뭐 먹고 잤니..?");
        System.out.println("[4] 선물을 준다..");
        System.out.println("[5] 아무 것도 하지 않는다...");
        System.out.println("============================");
        System.out.print("무엇을 할까? :");
        int talkType = sc.nextInt();
        sc.nextLine();

        switch (talkType) {
            case 1 -> talkAboutWeather(selectedNPC);
            case 2 -> talkAboutTrip(selectedNPC);
            case 3 -> talkAboutFood(selectedNPC);
            case 4 -> presentGift(selectedNPC);
            case 5 -> nothingHappened();
        }

    }

    /**
     * 유저가 오늘 날씨에 대해 물어보는 선택지를 선택했을 시 실행되는 Method
     * @param selectedNPC 대화할 NPC 객체의 주소 값.
     *                    유저가 선택한 NPC의 객체의 주소 값을 전달.
     */
    public void talkAboutWeather(NPCDTO selectedNPC) {
        System.out.println(RPGManager.getUserName() + ": 오늘 날씨 어때?");
        System.out.println(selectedNPC.getName() + ": 화창한걸!");
        plusNPCLike(selectedNPC, 20);


    }

    /**
     * 유저가 "놀러가자" 선택지를 선택했을 시 실행되는 Method
     * @param selectedNPC 대화할 NPC 객체의 주소 값.
     *                    유저가 선택한 NPC의 객체의 주소 값을 전달.
     */
    public void talkAboutTrip(NPCDTO selectedNPC) {
        System.out.println(RPGManager.getUserName() + ": 우리 놀러갈래?!");
        if (selectedNPC.getLike() > 50) {
            System.out.println(selectedNPC.getName() + ": 좋아! 어디로 갈까?");
            System.out.println("둘은 어디로 놀러갈지 대화하기 시작했다...");
        } else {
            System.out.println(selectedNPC.getName() + ": 우리가..?");
            System.out.println(RPGManager.getUserName() + " 은/는 버려졌다....");
        }
    }

    /**
     * 유저가 "어제 뭐 먹고 잤니?" 선택지를 선택했을 시 실행되는 Method
     * @param selectedNPC 대화할 NPC 객체의 주소 값.
     *                    유저가 선택한 NPC의 객체의 주소 값을 전달.
     */
    public void talkAboutFood(NPCDTO selectedNPC) {
        System.out.println(RPGManager.getUserName() + ": 어제 뭐 먹고 잤니..?");
        System.out.println(selectedNPC.getName() + ": 뭐?");
        minusNPCLike(selectedNPC, 100);
    }

    /**
     * 유저가 선물을 주는 경우에 실행되는 Method
     * 가지고 있는 선물 중에서 하나를 선택해서 전달할 수 있다.
     * @param selectedNPC 대화할 NPC 객체의 주소 값.
     *                    유저가 선택한 NPC의 객체의 주소 값을 전달.
     */
    public void presentGift(NPCDTO selectedNPC) {
//        UserDTO userInfo = manager.getUserInfo();
        System.out.println("가지고 있는 선물");
        System.out.println("============================");

        // 줄 선물이 없으면 선물을 주지 못한다.
        if (!showListElementsWithIndex(RPGManager.getUserGiftList())) {
            return; // 줄 선물이 없으므로 대화가 종료된다
        }

        System.out.print("무엇을 줄까? : ");
        int selectedGift = sc.nextInt() - 1;
        sc.nextLine();
        Gift givenGift = RPGManager.getUserGiftList().get(selectedGift);
        RPGManager.presentGift(givenGift);
        int changeLike = 30 + givenGift.getCharm() + RPGManager.getUserCharm();

        if (changeLike > 0) {
            plusNPCLike(selectedNPC, changeLike);
        } else if (changeLike < 0) {
            minusNPCLike(selectedNPC, changeLike);
        } else {
            nothingHappened();
        }
    }

    /**
     * 유저가 가진 선물을 모두 보여주는 Method
     * 해당 Method보다는 showListElementsWithIndex를 사용할 것
     * @return 유저가 가진 선물이 있으면 true, 없으면 false 반환
     */
    @Deprecated
    public boolean showGiftList() {
        List<Gift> giftList = RPGManager.getUserGiftList();

        if (giftList == null) {
            return false;
        }

        for (Gift gift : giftList) {
            System.out.println("[" + (giftList.indexOf(gift) + 1) + "]" + gift);
        }
        return true;

    }

    /**
     * NPC의 호감도가 증가하는 경우 호출되는 통합 Method
     * NPC의 호감도 상태를 표시해 준다.
     * @param selectedNPC 유저가 선택한 NPC 객체의 주소 값
     * @param like 증가시킬 호감도의 수치
     */
    public void plusNPCLike(NPCDTO selectedNPC, int like) {
        System.out.println(selectedNPC.getName() + "의 호감도가 " + like + "만큼 상승했다!");
        RPGManager.plusNPCLike(selectedNPC, like);
        System.out.println(selectedNPC.getName() + "의 호감도가 " + selectedNPC.getLike() + "가 되었다.");
    }

    /**
     * NPC의 호감도가 감소하는 경우 호출되는 통합 Method
     * NPC의 호감도 상태를 표시해 준다.
     * @param selectedNPC 유저가 선택한 NPC 객체의 주소 값
     * @param like 감소시킬 호감도의 수치
     */
    public void minusNPCLike(NPCDTO selectedNPC, int like) {
        System.out.println(selectedNPC.getName() + "의 호감도가 " + like + "만큼 하락했다....");
        RPGManager.minusNPCLike(selectedNPC, like);
        System.out.println(selectedNPC.getName() + "의 호감도가 " + selectedNPC.getLike() + "가 되었다.");
    }

    /**
     * 유저가 아무것도 하지 않는 경우에 실행할 Method
     */
    public void nothingHappened() {
        System.out.println("아무 일도 일어나지 않았다.....");
    }


    /**
     * 유저가 아이템 구매 선택지를 선택했을 시 실행되는 Method
     * 어느 가게에서 쇼핑할 것인지 또한 이 메소드에서 선택지를 받아 처리한다.
     */
    public void goToItemShop() {
        System.out.println("1. 옷 가게 / 2. 선물 가게");
        System.out.println("============================");
        System.out.print("어느 가게로 가시겠습니까? : ");
        int shopType = sc.nextInt();
        sc.nextLine();

        switch (shopType) {
            case 1 -> goToClothesShop();
            case 2 -> goToGiftShop();
        }

        showListElementsWithIndex(RPGManager.getItemShopItemList(shopType));
        System.out.print("어느 물건을 달라 할까? : ");
        int itemIndex = sc.nextInt() - 1;

        Item buyItem = RPGManager.buyItem(shopType, itemIndex);

        if (RPGManager.getUserMoney() < buyItem.getPrice()) {
            System.out.println("돈이 없다. 아르바이트를 하러 가볼까?");
            return;
        }


        System.out.println(buyItem.getName() + "을/를 샀다!");
        System.out.println(buyItem.getPrice() + "원 사용했다.");
        RPGManager.minusUserMoney(buyItem.getPrice());
        System.out.println(RPGManager.getUserMoney() + "원 남았다.");

        if (buyItem instanceof Clothes) {
            RPGManager.equipItem((Clothes) buyItem);
            System.out.println("아이템을 착용하여 매력도가 " + RPGManager.getUserCharm() + "가 되었다!");
        }



    }

    public void goToClothesShop() {
        System.out.println("옷 가게에 입장했다 !");
        System.out.println("============================");
        System.out.println("중년의 주인장이 나타났다 ...");
        System.out.println("주인장 : 어떤 물건을 구입하시겠습니까?");
        System.out.println("============================");


    }

    public void goToGiftShop() {
        System.out.println("선물 가게에 입장했다 !");
        System.out.println("============================");
        System.out.println("야생의 산신령이 나타났다 ...");
        System.out.println("야생의 산신령 : 어떤 물건이 네 물건이냐?");
        System.out.println("============================");

    }

    public <T> boolean showListElementsWithIndex(List<T> list) {

        if (list == null) return false;

        list.forEach((element) -> {
            System.out.println("[" + (list.indexOf(element) + 1) + "]" + element);
        });

//        for (T element : list) {
//            System.out.println("[" + (list.indexOf(element) + 1) + "]" + element);
//        }

        return !list.isEmpty();

    }

    public void goToWork() {
        System.out.println("어느 아르바이트를 하러 갈까?");
        System.out.println("1. 편의점 / 2. 영화관 / 3. 식당");
        System.out.println("============================");
        System.out.print("번호를 선택하세요 : ");
        int workType = sc.nextInt();
        sc.nextLine();

        switch (workType) {
            case 1 -> goToWorkConvenienceStore();
            case 2 -> goToWorkCinema();
            case 3 -> goToWorkRestaurant();
        }

    }

    public void goToWorkConvenienceStore() {
        System.out.println("편의점 알바를 했다.");
        System.out.println("진상이 너무 많다............");
        takeMoney(10000);
        loseCharm(20);
    }

    public void goToWorkCinema() {
        System.out.println("영화관 알바를 했다.");
        System.out.println("메뚜기족들 잡느라 너무 힘들다........");
        takeMoney(25000);
        loseCharm(10);
    }

    public void goToWorkRestaurant() {
        System.out.println("식당 알바를 했다.");
        System.out.println("손님이 팁을 만원 주셨다!");
        takeMoney(30000);
        loseCharm(5);
    }

    public void takeMoney(int money) {
        System.out.println("총 " + money + "원을 벌었다.");
        RPGManager.takeMoney(money);
    }

    public void loseCharm(int charm) {
        System.out.println("나의 매력이" + charm + " 하락했다.");
        RPGManager.loseCharm(charm);
    }

    public void printCharm() {
        NPCDTO[] npcList = RPGManager.getNpcList();
        for (NPCDTO npc : npcList) {
            System.out.println(npc);
        }
    }

    /**
     * 추후 StringJoiner를 통해 가독성을 증가시킬 필요가 있어보임.
     * 또한 다양한 타입을 지원하는 print Method를 추가할 것도 고려해볼 요소이다.
     */
    public void printNPCList() {
        NPCDTO[] npcList = RPGManager.getNpcList();

        for (int i = 0; i < npcList.length; i++) {
            // NPC의 이름과 번호를 출력한다.
            System.out.print((i + 1) + npcList[i].getName());
            // 마지막에는 구분자 대신 개행 문자를 출력하고
            if (i >= npcList.length - 1) {
                System.out.println();
            } else { // 그 전에는 '/'로 구분짓는다
                System.out.print(" / ");
            }
        }

    }

}
