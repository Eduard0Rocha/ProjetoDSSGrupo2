package Data;

import piloto.Piloto;
import users.AuthenticatedPlayer;
import users.Guest;
import users.Jogador;

import javax.swing.*;
import java.sql.*;
import java.util.HashMap;

public class JogadorDAO {

    private static JogadorAutenticadoDAO jogadorAutenticadoDAO = new JogadorAutenticadoDAO();
    private static GuestDAO guestDAO = new GuestDAO();
    private static JogadorDAO singleton = null;

    public JogadorDAO(){
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS jogador (" +
                    "codJogador int primary key not null," +
                    "pontosCorr int NOT NULL," +
                    "pontosGlobais int NOT NULL," +
                    "classe varchar(10) NOT NULL," +
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


    public static Jogador get(String key) {
        Jogador t = null;
        try {

            Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM jogador WHERE codJogador" +
                    "='"+key+"'");
            if (rs.next())
            {
               String classe = rs.getString("classe");
               if (classe.equals("AP")){
                     return  jogadorAutenticadoDAO.get(key);

               }
               else  if (classe.equals("G")){
                  return  guestDAO.get(key);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return t;
    }

    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM jogador")) {
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


                ResultSet rs = stm.executeQuery("SELECT MAX(codJogador) FROM jogador");
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

    public boolean existsKey (String key) throws SQLException {

        Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM jogador WHERE codJogador" +
                "='"+key+"'");
        {
            if (rs.next()) {
                return true;
            }
            return false;
        }
    }

    public boolean containsUsername(String username) {
            try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
                 Statement stm = conn.createStatement()) {


                ResultSet rs = stm.executeQuery("SELECT * FROM jogador WHERE username" +
                        "='"+username+"'");
                if (rs.next()) {
                    return true;
                }

            return false;
            } catch (SQLException e) {
                // Database error!
                e.printStackTrace();
                throw new NullPointerException(e.getMessage());
            }
        }


    public HashMap<String,Jogador> getJogadoresDB() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
            Statement s = conn.createStatement();
            try (ResultSet rs = s.executeQuery("select * from jogador")) {
                HashMap<String,Jogador> jogadores = new HashMap<>();
                while (rs.next()) {
                    int cod =  rs.getInt("codJogador");
                    String classe = rs.getString("classe");
                    if (classe.equals("AP")){
                        AuthenticatedPlayer n = jogadorAutenticadoDAO.get(Integer.toString(cod));
                        jogadores.put(Integer.toString(cod),n.clone());
                    }
                    else if (classe.equals("G")){
                        Guest a = guestDAO.get(Integer.toString(cod));

                        jogadores.put(Integer.toString(cod),a.clone());
                    }
                }

                return jogadores;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }


    public AuthenticatedPlayer getJogador(String username) throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM jogador WHERE username" +
                    "='"+username+"'");
                if (rs.next()) {
                    int cod =  rs.getInt("codJogador");
                    String classe = rs.getString("classe");
                    if (classe.equals("AP")){
                        AuthenticatedPlayer n = jogadorAutenticadoDAO.get(Integer.toString(cod));
                        return n;
                    }
                }
            }


        catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return null;
    }



}
