package Data;

import users.AuthenticatedPlayer;

import java.sql.*;

public class JogadorAutenticadoDAO {


    public JogadorAutenticadoDAO(){
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS jogadorAutenticado (" +
                    "nome varchar(45) primary key not null," +
                    "codJogador int NOT NULL," +
                    "password varchar(45) NOT NULL," +
                    "foreign key(codJogador) references jogador(codJogador))";
            stm.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public AuthenticatedPlayer put(String key, AuthenticatedPlayer t) {
        AuthenticatedPlayer res = null;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {

            stm.executeUpdate(
                    "INSERT INTO jogador VALUES ('"+t.getCodJogador()+"', '"+t.getPontosCorr()+"', '"+t.getPontosGlob() + "', '"+t.getUsername()+"') ");



            stm.executeUpdate(
                    "INSERT INTO jogadorAutenticado VALUES ('"+t.getCodJogador()+"', '"+t.getNome()+"', '"+t.getPassword()+"') ");


        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    public static String search_password(String username){

        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {

            ResultSet rs = stm.executeQuery("SELECT * FROM jogadorAutenticado");

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
