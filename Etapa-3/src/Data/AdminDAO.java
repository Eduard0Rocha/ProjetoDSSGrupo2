package Data;

import java.sql.*;

import users.Admin;
import users.AuthenticatedPlayer;

public class AdminDAO {
    public AdminDAO(){
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS admin (" +
                    "codAdmin int auto_increment primary key," +
                    "email varchar[45] NOT NULL," +
                    "contactoTLM varchar[45] NOT NULL," +
                    "nome varchar[45] NOT NULL," +
                    "username varchar[45] NOT NULL," +
                    "password varchar[45] NOT NULL";
            stm.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public Admin put(String key, Admin t) {
        Admin res = null;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {

            stm.executeUpdate(
                    "INSERT INTO admin VALUES ('"+t.getCodAdmin()+"', '"+t.getEmail()+"', '"+t.getContactoTLM() + "', '"+t.getNome()+ "', '"+t.getUsername()+ "', '"+t.getPassword()+"') ");



        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    public static String search_password(String username){

        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {

            ResultSet rs = stm.executeQuery("SELECT * FROM admin");

            while (rs.next()) {
                String name = rs.getString("name");

                if(username.equals(name)){
                    String password = rs.getString("password");
                    return password;
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

        return "NOT FOUND";
    }


}
