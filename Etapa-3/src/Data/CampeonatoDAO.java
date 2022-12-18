package Data;

import campeonato.Campeonato;
import campeonato.Corrida;
import campeonato.Registo;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CampeonatoDAO {
    private static CampeonatoDAO singleton = null;


    public CampeonatoDAO() {
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS campeonato (" +
                    "codCamp int primary key not null," +
                    "nomeCamp varchar(45) NOT NULL)";
            stm.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS registo (" +
                    "jogador table not null," +
                    "foreign table(jogador) references table(jogador)," +
                    "carro table not null," +
                    "foreign table(carro) references table(carro)," +
                    "piloto table not null," +
                    "foreign table(piloto) references table(piloto)," +
                    "nrAfinacoes int)";
            stm.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS corrida (" +
                    "codCorr int primary key not null," +
                    "codCamp int not null," +
                    "foreign key(codCamp) references campeonato(codCamp)," +
                    "codCirc int not null," +
                    "foreign key(codCirc) references circuito(codCirc)," +
                    "circuito table not null," +
                    "foreign table(circuito) references table(circuito))";
            stm.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS tempos(" +
                    "chave int primary key not null," +
                    "tempo float not null," +
                    "codCorr int not null," +
                    "foreign key(codCorr) references corrida(codCorr))";
            stm.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS classificacaoCorr(" +
                    "id int primary key not null," +
                    "classificacao varchar(45) not null," +
                    "codCorr int not null," +
                    "foreign key(codCorr) references corrida(codCorr)";
            stm.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS classificacao(" +
                    "chave int primary key not null," +
                    "classificacao integer not null," +
                    "codCamp int not null," +
                    "foreign key(codCamp) references campeonato(codCamp))";
            stm.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS classificacaoH(" +
                    "chave int primary key not null," +
                    "classificacaoH integer not null," +
                    "codCamp int not null," +
                    "foreign key(codCamp) references campeonato(codCamp))";
            stm.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS registos(" +
                    "id int primary key not null," +
                    "registo table not null," +
                    "foreign table(registo) references table(registo)," +
                    "codCamp int not null," +
                    "foreign key(codCamp) references campeonato(codCamp))";
            stm.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS corridas(" +
                    "chave int primary key not null," +
                    "corrida table not null," +
                    "foreign table(corrida) references table(corrida)," +
                    "codCamp int not null," +
                    "foreign key(codCamp) references campeonato(codCamp))";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public static CampeonatoDAO getInstance() {
        if (CampeonatoDAO.singleton == null) {
            CampeonatoDAO.singleton = new CampeonatoDAO();
        }
        return CampeonatoDAO.singleton;
    }

    public Campeonato get(Object key) {
        Campeonato c = new Campeonato();
        String nomeCamp = "";
        String codCamp = "0";
        HashMap<String, Integer> classificacao = new HashMap<>();
        HashMap<String, Integer> classificacaoH = new HashMap<>();
        ArrayList<Registo> registo = new ArrayList<>();
        HashMap<String, Corrida> corridas = new HashMap<>();

        try {
            Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM campeonato WHERE codCamp" +
                    "='"+key+"'");
            {
                if (rs.next()) {
                    nomeCamp = rs.getString("nomeCamp");
                    codCamp = Integer.toString(rs.getInt("codCamp"));
                }
                {
                    try (ResultSet cr1 = stm.executeQuery("select * from classificacao where codCamp" + "='"+key+"'");)
                    {
                        while (cr1.next()) {
                            classificacao.put(Integer.toString(rs.getInt("chave")), rs.getInt("classificacao"));
                        }
                    }

                    try (ResultSet cr2 = stm.executeQuery("select * from classificacaoH where codCamp" + "='"+key+"'");)
                    {
                        while (cr2.next()) {
                            classificacaoH.put(Integer.toString(rs.getInt("chave")), rs.getInt("classificacaoH"));
                        }
                    }

                    try (ResultSet cr3 = stm.executeQuery("select * from registos where codCamp" + "='"+key+"'");)
                    {
                        while (cr3.next()) {
                            registo.add((Registo) rs.getObject("registo"));
                        }
                    }

                    try (ResultSet cr4 = stm.executeQuery("select * from corridas where codCamp" + "='"+key+"'");)
                    {
                        while (cr4.next()) {
                            corridas.put(Integer.toString(rs.getInt("chave")),(Corrida) rs.getObject("corrida"));
                        }
                    }
                    c = new Campeonato(nomeCamp, codCamp, classificacao, classificacaoH, registo, corridas);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return c;
    }

    /*
     *Devolve o número de Campeonatos na BD
     */
    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM campeonato")) {
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
                     stm.executeQuery("SELECT Id FROM campeonato WHERE codCamp='"+key.toString()+"'")) {
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    public Campeonato put(String key, Campeonato t) {
        Campeonato res = null;
        HashMap<String, Integer> classificacao = t.getClassificacao();
        HashMap<String, Integer> classificacaoH = t.getClassificacaoH();
        ArrayList<Registo> registo = t.getRegisto();
        HashMap<String, Corrida> corridas = t.getCorridas();
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("INSERT INTO campeonato VALUES ('"+t.getCodCamp()+"', '"+t.getNomeCamp()+"')");

            for(int i=0;i<classificacao.size();i++) {
                stm.executeUpdate("INSERT INTO classificacao VALUES ('"+ i +"', '"+classificacao.get(i)+"', '"+t.getCodCamp()+"')");
            }
            for(int i=0;i<classificacaoH.size();i++) {
                stm.executeUpdate("INSERT INTO classificacaoH VALUES ('"+ i +"', '"+classificacaoH.get(i)+"', '"+t.getCodCamp()+"')");
            }
            for(int i=0;i<registo.size();i++) {
                stm.executeUpdate("INSERT INTO registo VALUES ('"+ i +"', '"+registo.get(i)+"', '"+t.getCodCamp()+"')");
            }
            for(int i=0;i<corridas.size();i++) {
                stm.executeUpdate("INSERT INTO corridas VALUES ('"+ i +"', '"+corridas.get(i)+"', '"+t.getCodCamp()+"')");
            }
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
                ResultSet rs = stm.executeQuery("SELECT MAX(codCamp) FROM campeonato");
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
        Campeonato t = this.get(key);
        boolean k = false;
        try {
            Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM campeonato WHERE codCamp='"+key+"'");
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
