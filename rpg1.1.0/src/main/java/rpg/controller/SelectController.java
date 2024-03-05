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
            System.out.println(query);

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
}
