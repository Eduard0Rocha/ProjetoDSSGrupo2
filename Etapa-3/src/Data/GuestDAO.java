package Data;

import users.Admin;
import users.Guest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GuestDAO {
    public GuestDAO(){
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS guest (" +
                    "idGuest int auto_increment primary key not null," +
                    "codJogador int not null," +
                    "foreign key(codJogador) references jogador(codJogador))";
            stm.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public Guest put(String key, Guest t) {
        Guest res = null;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {

            stm.executeUpdate(
                    "INSERT INTO jogador VALUES ('"+t.getCodJogador()+"', '"+t.getPontosCorr()+"', '"+t.getPontosGlob() + "', '"+t.getNome()+"') ");


            stm.executeUpdate(
                    "INSERT INTO guest VALUES ('"+t.getIdGuest()+"', '"+t.getCodJogador()+"') ");



        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }
}
