package Data;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import piloto.Piloto;
import users.Admin;
import users.AuthenticatedPlayer;

public class AdminDAO {
    public AdminDAO(){
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS admin (" +
                    "codAdmin int primary key," +
                    "email varchar(45) NOT NULL," +
                    "contactoTLM varchar(45) NOT NULL," +
                    "nome varchar(45) NOT NULL," +
                    "username varchar(45) NOT NULL," +
                    "password varchar(45) NOT NULL)";
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

    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM admin")) {
            if(rs.next()) {
                i = rs.getInt(1);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return i;
    }

    public int getmaxkey() {


        int res = 0;
        if (this.size() == 0) return 0;
        else {
            try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
                 Statement stm = conn.createStatement()) {


                ResultSet rs = stm.executeQuery("SELECT MAX(codAdmin) FROM admin");
                if (rs.next()) {
                    res = rs.getInt(1);
                }


            } catch (SQLException e) {
                // Database error!
                e.printStackTrace();
                throw new NullPointerException(e.getMessage());
            }
            return res;
        }
    }

    public boolean validAdmin (String username, String password) throws SQLException {

        Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM admin WHERE username" +
                "='"+username+"'");
        if (rs.next()){
            String pass = rs.getString("password");
            if (pass.equals(password)) return true;
        }
            return false;
        }


    public boolean existsAdmin (String username) throws SQLException {

        Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM admin WHERE username" +
                "='"+username+"'");
        {
            if (rs.next()) {
                return true;
            }
            return false;
        }
    }

    public HashMap<String, Admin> getAdminsDB() throws SQLException{

        try {
            Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
            Statement s = conn.createStatement();
            try (ResultSet rs = s.executeQuery("select * from admin")) {
                HashMap<String,Admin> admins = new HashMap<>();
                while (rs.next()) {
                    admins.put(Integer.toString(rs.getInt("codAdmin")),new Admin(rs.getString("nome"),rs.getString("contactoTLM"),rs.getString("email"),Integer.toString(rs.getInt("codAdmin")),rs.getString("username"),rs.getString("password")));
                }

                return admins;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }



}
