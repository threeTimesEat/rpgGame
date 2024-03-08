package rpg.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static rpg.common.JDBCTemplate.close;
import static rpg.common.JDBCTemplate.getConnection;

public class DeleteController {

    Properties prop = new Properties();

    public DeleteController() {
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/rpg/mapper/rpg-query.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeFKSetting() {
        Connection con = getConnection();
        PreparedStatement pstmt = null;

        try {
            String query = prop.getProperty("setFk");

            pstmt = con.prepareStatement(query);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }   finally {
            close(pstmt);
            close(con);
        }
    }

    public void initDatabase() {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;

        try {
            String query = prop.getProperty("setFk");
            String query2 = prop.getProperty("initBag");
            String query3 = prop.getProperty("initUser");
            int userCode = 1;

            pstmt = con.prepareStatement(query);
            pstmt2 = con.prepareStatement(query2);
            pstmt3 = con.prepareStatement(query3);

            pstmt.executeUpdate();
            pstmt2.executeUpdate();
            pstmt3.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }   finally {
            close(pstmt3);
            close(pstmt2);
            close(pstmt);
            close(con);
        }
    }

    public void deleteItem(int code) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;

        try {
            String query = prop.getProperty("deleteItem");
            int userCode = 1;

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, code);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }   finally {
            close(pstmt);
            close(con);
        }
    }
}
