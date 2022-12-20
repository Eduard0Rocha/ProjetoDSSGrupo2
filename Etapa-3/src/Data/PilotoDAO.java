package Data;

import circuito.Circuito;
import circuito.CondicoesAtmosfericas;
import piloto.Piloto;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PilotoDAO {
    private static PilotoDAO singleton = null;


    public PilotoDAO() {
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS piloto (" +
                    "codPiloto int primary key not null," +
                    "nome varchar(45) NOT NULL," +
                    "cts double NOT NULL," +
                    "sva double NOT NULL)";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }



    public static PilotoDAO getInstance() {
        if (PilotoDAO.singleton == null) {
            PilotoDAO.singleton = new PilotoDAO();
        }
        return PilotoDAO.singleton;
    }

    public Piloto get(Object key) {
        Piloto t = new Piloto();
        try {

            Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM piloto WHERE codPiloto" +
                    "='"+key+"'");
            {
                if (rs.next()) {
                    t=new Piloto(rs.getString("nome"),rs.getFloat("cts"),rs.getFloat("cts"),rs.getString("codPiloto"));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return t;
    }

    public HashMap<String,Piloto> getPilotosDB() throws SQLException {

        try {
            Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
            Statement s = conn.createStatement();
            try (ResultSet rs = s.executeQuery("select * from piloto")) {
                HashMap<String,Piloto> pilotos = new HashMap<>();
                while (rs.next()) {
                    pilotos.put(Integer.toString(rs.getInt("codPiloto")),new Piloto(rs.getString("nome"),rs.getFloat("cts"),rs.getFloat("sva"),Integer.toString(rs.getInt("codPiloto"))));
                }

                return pilotos;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    /*
     *Devolve o número de Pilotos na BD
     */
    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM piloto")) {
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

    public boolean containsKey(Object key) {
        boolean r;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs =
                     stm.executeQuery("SELECT Id FROM piloto WHERE codPiloto='"+key.toString()+"'")) {
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }
    public Piloto put(String key, Piloto t) {
        Piloto res = null;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {


            stm.executeUpdate(
                    "INSERT INTO piloto VALUES ('"+t.getCodPiloto()+"', '"+t.getNome()+"', '"+t.getCTS()+"', '"+t.getSVA()+"') ");


        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    public int getmaxkey() {


        int res = 0;
        if (this.size() == 0) return 0;
        else {
            try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
                 Statement stm = conn.createStatement()) {


                ResultSet rs = stm.executeQuery("SELECT MAX(codPiloto) FROM piloto");
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


    public boolean remove(Object key) {
        Piloto t = this.get(key);
        boolean k=false;
        try {
             Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             stm.executeUpdate("DELETE FROM piloto WHERE codPiloto='"+key+"'");
             k=true;
        }
        catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());

        }
        return k;
    }



}
