package rpg.controller;

import rpg.dto.UserDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static rpg.common.JDBCTemplate.close;
import static rpg.common.JDBCTemplate.getConnection;

public class UpdateController {

    Properties prop = new Properties();

    public UpdateController() {
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/rpg/mapper/rpg-query.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int updateName(UserDTO user) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            String query = prop.getProperty("updateUserName");
//            System.out.println(query);
            int userCode = 1;

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, user.getName());
            pstmt.setInt(2, userCode);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }   finally {
            close(pstmt);
            close(con);
        }

        return result;

    }

    public void updateMoney(int money) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;

        try {
            String query = prop.getProperty("updateUserMoney");
            int userCode = 1;

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, money);
            pstmt.setInt(2, userCode);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }   finally {
            close(pstmt);
            close(con);
        }
    }

    public void updateCharm(int charm) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;

        try {
            String query = prop.getProperty("updateUserCharm");
            int userCode = 1;

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, charm);
            pstmt.setInt(2, userCode);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }   finally {
            close(pstmt);
            close(con);
        }
    }
}