package Data;

import piloto.Piloto;
import users.Jogador;

import java.sql.*;

public class JogadorDAO {

    private static JogadorDAO singleton = null;

    public JogadorDAO(){
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS jogador (" +
                    "codJogador int auto_increment primary key not null," +
                    "pontosCorr int NOT NULL," +
                    "pontosGlobais int NOT NULL," +
                    "username varchar(45) NOT NULL)";
            stm.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public static JogadorDAO getInstance() {
        if (JogadorDAO.singleton == null) {
            JogadorDAO.singleton = new JogadorDAO();
        }
        return JogadorDAO.singleton;
    }

    public Jogador get(Object key) {
        Jogador t = new Jogador();
        try {

            Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM jogador WHERE codJogador" +
                    "='"+key+"'");
            {
                if (rs.next()) {
                    t=new Jogador(rs.getString("username"), Integer.toString(rs.getInt("codJogador")));
                    int pC = rs.getInt("pontosCorr");
                    t.addPontosCorr(pC);
                    int pG = rs.getInt("pontosGlobais");
                    t.addPontosGlob(pG);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return t;
    }
}
