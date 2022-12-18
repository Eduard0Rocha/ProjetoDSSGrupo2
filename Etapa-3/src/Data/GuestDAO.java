package Data;

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
                    "username varchar(45) primary key not null," +
                    "foreign key(codJogador) references jogador(codJogador))";
            stm.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }
}
