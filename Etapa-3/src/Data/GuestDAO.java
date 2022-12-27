package Data;

import users.Admin;
import users.AuthenticatedPlayer;
import users.Guest;

import java.sql.*;

public class GuestDAO {
    public GuestDAO() {
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS guest (" +
                    "idGuest int  primary key not null," +
                    "codJogador int not null," +
                    "foreign key(codJogador) references jogador(codJogador))";
            stm.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public Guest put( Guest t) {
        Guest res = null;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {

            stm.executeUpdate(
                    "INSERT INTO jogador VALUES ('" + t.getCodJogador() + "', '" + t.getPontosCorr() + "', '" + t.getPontosGlob() + "', '"+ t.getclasse() + "', '" + t.getNome() + "') ");


            stm.executeUpdate(
                    "INSERT INTO guest VALUES ('" + t.getIdGuest() + "', '" + t.getCodJogador() + "') ");


        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM guest")) {
            if (rs.next()) {
                i = rs.getInt(1);
            }
        } catch (Exception e) {
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


                ResultSet rs = stm.executeQuery("SELECT MAX(idGuest) FROM guest");
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


    public boolean validGuest(String username) throws SQLException {

        Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM jogador WHERE username" +
                "='" + username + "'");
        if (rs.next()) {
            int codigo = rs.getInt("codJogador");
            ResultSet rs2 = stm.executeQuery("SELECT * FROM guest WHERE codJogador" +
                    "='" + codigo + "'");
            if (rs2.next()) return true;
        }
        return false;
    }


    public Guest get(Object key) {
        Guest t ;
        try {

            Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM jogador WHERE codJogador" +
                    "='"+key+"'");
            {
                String username = "";
                int pontosCorr = 0;
                int pontosGlobais=0;
                int id=1000;
                if (rs.next()) {
                    username = rs.getString("username");
                    pontosCorr= rs.getInt("pontosCorr");
                    pontosGlobais= rs.getInt("pontosGlobais");
                }

                ResultSet rs2 = stm.executeQuery("SELECT * FROM guest WHERE codJogador" +
                        "='"+key+"'");
                if (rs2.next()){
                    id = rs2.getInt("idGuest");
                }
                t=new Guest(username,(String) key,Integer.toString(id),pontosCorr,pontosGlobais);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return t;
    }


}
