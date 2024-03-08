package rpg.controller;

import rpg.dto.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static rpg.common.JDBCTemplate.close;
import static rpg.common.JDBCTemplate.getConnection;

public class SelectController {

    Properties prop = new Properties();

    public SelectController() {
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/rpg/mapper/rpg-query.xml"));
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UserDTO selectUserInfo() {

        Connection con = getConnection();

        PreparedStatement selectUserInfo = null;
        ResultSet userInfoResultSet = null;

        UserDTO userInfo = null;

        try {
            String query = prop.getProperty("selectUserInfo");

            int userCode = 1;
            selectUserInfo = con.prepareStatement(query);
            selectUserInfo.setInt(1, userCode);

//            System.out.println("query = " + query);

            userInfoResultSet = selectUserInfo.executeQuery();

            // 유저를 정확히 받아왔는지 체크하는 if 문
            if (userInfoResultSet.next()) {
                userInfo = new UserDTO();

                userInfo.setName(userInfoResultSet.getString("USER_NAME"));
                userInfo.setCharm(userInfoResultSet.getInt("USER_CHARM"));
                userInfo.setBagCode(userInfoResultSet.getInt("USER_BAG"));
                userInfo.setEquippedItemCode(userInfoResultSet.getInt("USER_EQUIPPED"));
                userInfo.setMoney(userInfoResultSet.getInt("USER_MONEY"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(userInfoResultSet);
            close(selectUserInfo);
            close(con);
        }

        return userInfo;
    }

    public BagDTO selectUserBag() {

        BagDTO userBag = new BagDTO();

        Connection con = getConnection();

        PreparedStatement selectUserBag = null;
        PreparedStatement selectItemInfo = null;
        ResultSet userBagResultSet = null;
        ResultSet itemInfo = null;

        try {
            String query = prop.getProperty("selectUserBag");
            String itemQuery = prop.getProperty("selectItem");
//            System.out.println(query);

            int bagCode = 1;
            selectUserBag = con.prepareStatement(query);
            selectUserBag.setInt(1, bagCode);

//            System.out.println("query = " + query);

            userBagResultSet = selectUserBag.executeQuery();

            while (userBagResultSet.next()) {
                ItemDTO itemDTO = null;
                selectItemInfo = con.prepareStatement(itemQuery);
                selectItemInfo.setInt(1, userBagResultSet.getInt("ITEM_CODE"));

                itemInfo = selectItemInfo.executeQuery();

                itemInfo.next();

                if (itemInfo.getInt("ITEM_CATEGORY") == 1) {
                    itemDTO = new ClothesDTO();
                    itemDTO.setCode(itemInfo.getInt("ITEM_CODE"));
                    itemDTO.setCharm(itemInfo.getInt("ITEM_CHARM"));
                    itemDTO.setName(itemInfo.getString("ITEM_NAME"));
                    itemDTO.setPrice(itemInfo.getInt("ITEM_PRICE"));

                    userBag.addItem(itemDTO);
                }
                else if (itemInfo.getInt("ITEM_CATEGORY") == 2){
                    itemDTO = new GiftDTO();
                    itemDTO.setCode(itemInfo.getInt("ITEM_CODE"));
                    itemDTO.setCharm(itemInfo.getInt("ITEM_CHARM"));
                    itemDTO.setName(itemInfo.getString("ITEM_NAME"));
                    itemDTO.setPrice(itemInfo.getInt("ITEM_PRICE"));

                    userBag.addItem(itemDTO);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(userBagResultSet);
            close(selectUserBag);
            close(con);
        }
        return userBag;
    }

    public ClothesDTO[] selectClothesList() {
        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        ClothesDTO row = null;
        List<ClothesDTO> clothesList = null;

        try {
            String query = prop.getProperty("selectClothesList");
//            System.out.println(query);


            pstmt = con.prepareStatement(query);

//            System.out.println("query = " + query);

            rset = pstmt.executeQuery();

            // 조회된 여러 행을 담아놓을 ArrayList 객체 생성
            clothesList = new ArrayList<>();

            while(rset.next()) {
                row = new ClothesDTO();

                row.setCode(rset.getInt("ITEM_CODE"));
                row.setCharm(rset.getInt("ITEM_CHARM"));
                row.setName(rset.getString("ITEM_NAME"));
                row.setPrice(rset.getInt("ITEM_PRICE"));

                clothesList.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }

        ClothesDTO[] arrList = clothesList.toArray(new ClothesDTO[0]);
        return arrList;
    }

    public GiftDTO[] selectGiftList() {
        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        GiftDTO row = null;
        List<GiftDTO> giftList = null;

        try {
            String query = prop.getProperty("selectGiftList");
//            System.out.println(query);


            pstmt = con.prepareStatement(query);

//            System.out.println("query = " + query);

            rset = pstmt.executeQuery();

            // 조회된 여러 행을 담아놓을 ArrayList 객체 생성
            giftList = new ArrayList<>();

            while(rset.next()) {
                row = new GiftDTO();

                row.setCode(rset.getInt("ITEM_CODE"));
                row.setCharm(rset.getInt("ITEM_CHARM"));
                row.setName(rset.getString("ITEM_NAME"));
                row.setPrice(rset.getInt("ITEM_PRICE"));

                giftList.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }

        GiftDTO[] arrList = giftList.toArray(new GiftDTO[0]);
        return arrList;
    }

    public NPCDTO[] selectNPCList() {
        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        NPCDTO row = null;
        List<NPCDTO> npcList = null;

        try {
            String query = prop.getProperty("selectNPCList");


            pstmt = con.prepareStatement(query);

//            System.out.println("query = " + query);

            rset = pstmt.executeQuery();

            // 조회된 여러 행을 담아놓을 ArrayList 객체 생성
            npcList = new ArrayList<>();

            while(rset.next()) {
                row = new NPCDTO();

                row.setCode(rset.getInt("NPC_CODE"));
                row.setName(rset.getString("NPC_NAME"));
                row.setLike(rset.getInt("NPC_CHARM"));

                npcList.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }

        NPCDTO[] arrList = npcList.toArray(new NPCDTO[0]);
        return arrList;
    }
}
