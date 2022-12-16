package src.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDAO {
    public AdminDAO(){
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS jogador (" +
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
}
