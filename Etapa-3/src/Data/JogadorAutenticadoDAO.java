package Data;

import piloto.Piloto;
import users.AuthenticatedPlayer;
import users.*;

import java.sql.*;

public class JogadorAutenticadoDAO {


    public JogadorAutenticadoDAO(){
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS jogadorAutenticado (" +
                    "nome varchar(45) not null," +
                    "codJogador int NOT NULL," +
                    "password varchar(45) NOT NULL," +
                    "foreign key(codJogador) references jogador(codJogador))";
            stm.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public AuthenticatedPlayer put( AuthenticatedPlayer t) {
        AuthenticatedPlayer res = null;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {

            stm.executeUpdate(
                    "INSERT INTO jogador VALUES ('"+t.getCodJogador()+"', '"+t.getPontosCorr()+"', '"+t.getPontosGlob() +"', '"+t.getclasse() +  "', '"+t.getUsername()+"') ");



            stm.executeUpdate(
                    "INSERT INTO jogadorAutenticado VALUES ('"+t.getNome()+"', '"+t.getCodJogador()+"', '"+t.getPassword()+"') ");


        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    public AuthenticatedPlayer get(String key) {
        AuthenticatedPlayer t ;
        try {

            Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM jogador WHERE codJogador" +
                    "='"+key+"'");
            {
                String username = "";
                int pontosCorr = 0;
                int pontosGlobais=0;
                String nome="";
                String pass="";
                if (rs.next()) {
                    username = rs.getString("username");
                    pontosCorr= rs.getInt("pontosCorr");
                    pontosGlobais= rs.getInt("pontosGlobais");
                }

                ResultSet rs2 = stm.executeQuery("SELECT * FROM jogadorautenticado WHERE codJogador" +
                        "='"+key+"'");
                if (rs2.next()){
                    nome = rs2.getString("nome");
                    pass = rs2.getString("password");
                }
                t=new AuthenticatedPlayer(username,key,nome,pass,pontosCorr,pontosGlobais);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return t;
    }


    public boolean validUser (String username, String password) throws SQLException {

        Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM jogador WHERE username" +
                "='"+username+"'");
        {
            if (rs.next()) {
            int codigo = rs.getInt("codJogador");
                ResultSet rs2 = stm.executeQuery("SELECT * FROM jogadorautenticado WHERE codJogador" +
                        "='"+codigo+"'");
                 if (rs2.next()){
                     if (rs2.getString("password").equals(password)) return true;
                 }

            }
            return false;
        }
    }

    public boolean existsUser (String username) throws SQLException {

        Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM jogador WHERE username" +
                "='"+username+"'");
        {
            if (rs.next()) {
                if (rs.getString("classe").equals("AP")) return true;
            }
            return false;
        }
    }

    @Override
    public String toString() {
        return "JogadorAutenticadoDAO{}";
    }
}


