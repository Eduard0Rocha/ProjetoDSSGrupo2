package Data;

import campeonato.Campeonato;
import campeonato.Corrida;
import campeonato.Registo;
import carro.Carro;
import carro.Pneu;
import piloto.Piloto;
import users.Jogador;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
                    "codRegisto int primary key not null," +
                    "codJogador int not null," +
                    "foreign key(codJogador) references jogador(codJogador)," +
                    "codCarro int not null," +
                    "foreign key(codCarro) references carro(codCarro)," +
                    "codPiloto int not null," +
                    "foreign key(codPiloto) references piloto(codPiloto)," +
                    "nrAfinacoes int," +
                    "codCamp int not null," +
                    "foreign key(codCamp) references campeonato(codCamp))";
            stm.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS corrida (" +
                    "codCorr int primary key not null," +
                    "codCamp int not null," +
                    "foreign key(codCamp) references campeonato(codCamp)," +
                    "codCirc int not null," +
                    "foreign key(codCirc) references circuito(codCirc))";
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
                    "foreign key(codCorr) references corrida(codCorr))";
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
                            classificacao.put(Integer.toString(cr1.getInt("chave")), cr1.getInt("classificacao"));
                        }
                    }

                    try (ResultSet cr2 = stm.executeQuery("select * from classificacaoH where codCamp" + "='"+key+"'");)
                    {
                        while (cr2.next()) {
                            classificacaoH.put(Integer.toString(cr2.getInt("chave")), cr2.getInt("classificacaoH"));
                        }
                    }

                    try (ResultSet cr3 = stm.executeQuery("select * from registo where codCamp" + "='"+key+"'");)
                    {
                        while (cr3.next()) {
                            int n = cr3.getInt("codRegisto");
                            try(ResultSet reg = stm.executeQuery("select * from registo where codRegisto" + "='"+n+"'");){
                              //  Registo aux = new Registo(JogadorDAO.getInstance().get(reg.getInt("codJogador")),CarroDAO.getInstance().get(reg.getInt("codCarro")), PilotoDAO.getInstance().get(reg.getInt("codPiloto")));
                              //  aux.setNrAfinacoes(reg.getInt("nrAfinacoes"));
                              //  registo.add(aux);
                            }
                        }
                    }


                    try (ResultSet cr4 = stm.executeQuery("select * from corrida where codCamp" + "='"+key+"'");)
                    {
                        while (cr4.next()) {
                            int n = cr4.getInt("codCorr");
                            try(ResultSet co = stm.executeQuery("select * from corrida where codCorr" + "='"+n+"'");
                                ResultSet tp = stm.executeQuery("select * from tempos where codCorr" + "='"+n+"'");
                                ResultSet cl = stm.executeQuery("select * from classificacaoCorr where codCorr" + "='"+n+"'");){
                                Corrida aux = new Corrida(Integer.toString(n), Integer.toString(co.getInt("codCamp")),Integer.toString(co.getInt("codCirc")));
                                HashMap<String, Float> tempos = new HashMap<>();
                                while(tp.next()){
                                    tempos.put(Integer.toString(tp.getInt("chave")),tp.getFloat("tempo"));
                                }
                                ArrayList<String> classCorr = new ArrayList<>();
                                while(cl.next()){
                                    classCorr.add(cl.getString("classificacao"));
                                }
                                aux.setTempos(tempos);
                                aux.setClassificacao(classCorr);
                                corridas.put(Integer.toString(cr4.getInt("chave")), aux);
                            }
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

    public HashMap<String, Campeonato> getCampeonatosDB() throws SQLException {
        Campeonato c ;
        String nomeCamp = "";

        HashMap<String, Campeonato> campeonatos = new HashMap<>();

        try {
            Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
            Statement stm = conn.createStatement();
            try(ResultSet rs = stm.executeQuery("SELECT * FROM campeonato")) {
                while(rs.next()) {
                    int key = rs.getInt("codCamp");
                     nomeCamp = rs.getString("nomeCamp");
                            c = new Campeonato(nomeCamp, Integer.toString(key), getClassificacoes(key), getClassificacoesH(key), getRegistos(key), getCorridas(key));
                            campeonatos.put(Integer.toString(key), c);
                        }
                        }

                }
        catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return campeonatos;
    }

    public  HashMap<String, Integer> getClassificacoes(int key) throws SQLException {
        Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
        Statement stm = conn.createStatement();
        HashMap<String, Integer> classificacao = new HashMap<>();
        try (ResultSet cr1 = stm.executeQuery("select * from classificacao where codCamp" + "='" + key + "'");) {

            while (cr1.next()) {
                classificacao.put(Integer.toString(cr1.getInt("chave")), cr1.getInt("classificacao"));
            }
        }
        return classificacao;
    }

    public  HashMap<String, Integer> getClassificacoesH(int key) throws SQLException {
        Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
        Statement stm = conn.createStatement();
        HashMap<String, Integer> classificacaoH = new HashMap<>();
        try (ResultSet cr2 = stm.executeQuery("select * from classificacaoH where codCamp" + "='" + key + "'");) {
            while (cr2.next()) {
                classificacaoH.put(Integer.toString(cr2.getInt("chave")), cr2.getInt("classificacaoH"));
            }
        }
        return classificacaoH;
    }
    public  Corrida getCorrida(int n) throws SQLException {
        if (existsCorrida(n) ){
            Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
            Statement stm = conn.createStatement();
            try (ResultSet co = stm.executeQuery("select * from corrida where codCorr" + "='" + n + "'")) {
                if (co.next()) {
                    Corrida aux = new Corrida(Integer.toString(n), Integer.toString(co.getInt("codCamp")), Integer.toString(co.getInt("codCirc")));
                    return aux;
                }
            }
        }

        return null;
    }

    public  HashMap<String,Corrida> getCorridas(int  key) throws SQLException {
        Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
        Statement stm = conn.createStatement();
        HashMap<String, Corrida> corridas ;
        try (ResultSet cr4 = stm.executeQuery("select * from corrida where codCamp" + "='" + key + "'")) {
            corridas=new HashMap<>();
               while (cr4.next()) {
                   Corrida aux ;
                   int n = cr4.getInt("codCorr");
                   HashMap<String, Float> tempos = this.getTempos(n);
                   ArrayList<String> classCorr = this.getClassificacaoCorr(n);
                   aux = getCorrida(n);
                   aux.setTempos(tempos);
                   aux.setClassificacao(classCorr);

                   corridas.put(Integer.toString(n), aux);
               }
            return corridas;
           }
    }

    public HashMap<String, Float> getTempos(int n) throws SQLException {
        Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
        Statement stm = conn.createStatement();
        HashMap<String, Float> tempos = new HashMap<>();
        try ( ResultSet tp = stm.executeQuery("select * from tempos where codCorr" + "='" + n + "'")){

            while (tp.next()) {
                tempos.put(Integer.toString(tp.getInt("chave")), tp.getFloat("tempo"));
            }
        }
        return tempos;
    }
    public ArrayList<String>  getClassificacaoCorr(int n) throws SQLException {
        Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
        Statement stm = conn.createStatement();
        ArrayList<String> classCorr = new ArrayList<>();
        try (ResultSet cl = stm.executeQuery("select * from classificacaoCorr where codCorr" + "='" + n + "'")) {
            while (cl.next()) {
                classCorr.add(cl.getString("classificacao"));
            }
        }
        return classCorr;
    }


    public ArrayList<Registo> getRegistos(int key) throws SQLException {
        Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
        Statement stm = conn.createStatement();
        ArrayList <Registo> registo = new ArrayList<>();
        try (ResultSet cr3 = stm.executeQuery("select * from registo where codCamp" + "='" + key + "'")) {
            while (cr3.next()) {
                int n = cr3.getInt("codRegisto");
                    Jogador novo = JogadorDAO.get(Integer.toString(  cr3.getInt("codJogador")));
                    Carro car=CarroDAO.get(cr3.getInt("codCarro"));
                    Piloto pil = PilotoDAO.get(cr3.getInt("codPiloto"));
                    Registo aux = new Registo(novo,car,pil,Integer.toString(n));
                    aux.setNrAfinacoes(cr3.getInt("nrAfinacoes"));
                    registo.add(aux);
                }
            }
        return registo;
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
                     stm.executeQuery("SELECT * FROM campeonato WHERE codCamp='"+key.toString()+"'")) {
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    public boolean existsCorrida(Object key) {
        boolean r;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs =
                     stm.executeQuery("SELECT * FROM corrida WHERE codCorr='"+key.toString()+"'")) {
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    public void put( Campeonato t) {
        HashMap<String, Integer> classificacao = t.getClassificacao();
        HashMap<String, Integer> classificacaoH = t.getClassificacaoH();
        ArrayList<Registo> registo = t.getRegisto();
        HashMap<String, Corrida> corridas = t.getCorridas();
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("INSERT INTO campeonato VALUES ('"+Integer.parseInt(t.getCodCamp())+"', '"+t.getNomeCamp()+"')");
            for(int i=0;i<classificacao.size();i++) {
                stm.executeUpdate("INSERT INTO classificacao VALUES ('"+ i +"', '"+classificacao.get(i)+"', '"+Integer.parseInt(t.getCodCamp())+"')");
            }
            for(int i=0;i<classificacaoH.size();i++) {
                stm.executeUpdate("INSERT INTO classificacaoH VALUES ('"+ i +"', '"+classificacaoH.get(i)+"', '"+Integer.parseInt(t.getCodCamp())+"')");
            }
            for(int i=0;i<registo.size();i++) {
                stm.executeUpdate("INSERT INTO registo VALUES ('"+ Integer.parseInt(registo.get(i).getCodRegisto()) +"', '"+Integer.parseInt(registo.get(i).getJogador().getCodJogador())+"', '"+Integer.parseInt(registo.get(i).getCarro().getCodCarro())+"', '"+Integer.parseInt(registo.get(i).getPiloto().getCodPiloto())+"', '"+registo.get(i).getNrAfinacoes()+"', '"+Integer.parseInt(t.getCodCamp())+"')");
            }
            for(int i=0;i<corridas.size();i++) {
                stm.executeUpdate("INSERT INTO corrida VALUES ('"+ i +"', '"+Integer.parseInt(t.getCodCamp())+"', '"+Integer.parseInt(corridas.get(i).getCodCirc())+"')");
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();

            throw new NullPointerException(e.getMessage());
        }
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

    public int getmaxkeyCorrida() {
        int res = 0;
        if (this.size() == 0) return 0;
        else {
            try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
                 Statement stm = conn.createStatement()) {
                ResultSet rs = stm.executeQuery("SELECT MAX(codCorr) FROM corrida");
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


    public int getmaxkeyRegisto() {
        int res = 0;
        if (this.size() == 0) return 0;
        else {
            try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
                 Statement stm = conn.createStatement()) {
                ResultSet rs = stm.executeQuery("SELECT MAX(codRegisto) FROM registo");
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
        boolean k = false;
        try {
            Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
            Statement stm = conn.createStatement();
            try (ResultSet cr1 = stm.executeQuery("select * from corrida where codCamp" +
                    "='" + key + "'");) {
                if (cr1.next()) {
                    while (cr1.next()) {
                        int codCorr = cr1.getInt("codCorr");
                        System.out.println(codCorr);
                        stm.executeUpdate("DELETE FROM classificacaocorr WHERE codCorr='" + codCorr + "'");
                        stm.executeUpdate("DELETE FROM tempos WHERE codCorr='" + codCorr + "'");
                    }
                }

                 stm.executeUpdate("DELETE FROM corrida WHERE codCamp='" + key + "'");
                 stm.executeUpdate("DELETE FROM classificacao WHERE codCamp='" + key + "'");
                 stm.executeUpdate("DELETE FROM classificacaoH WHERE codCamp='" + key + "'");
                 stm.executeUpdate("DELETE FROM registo WHERE codCamp='" + key + "'");
                 stm.executeUpdate("DELETE FROM campeonato WHERE codCamp='" + key + "'");
                 k = true;
             } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
        catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return k;
    }

    public int sizeCorr() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM corrida")) {
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

    public void addCorr(Corrida cr) {

        HashMap<String, Float> tempos = cr.getTempos();
        ArrayList<String> classificacaoCorr = cr.getClassificacao();

        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("INSERT INTO corrida VALUES ('"+Integer.parseInt(cr.getCodCorr())+"', '"+Integer.parseInt(cr.getCodCamp())+"', '"+Integer.parseInt(cr.getCodCirc())+"')");

            for(int i=0; i<tempos.size(); i++){
                stm.executeUpdate("INSERT INTO tempos VALUES ('"+ i +"', '"+tempos.get(i)+"', '"+Integer.parseInt(cr.getCodCorr())+"')");
            }

            for(int i=0; i<classificacaoCorr.size(); i++){
                stm.executeUpdate("INSERT INTO classificacaoCorr VALUES ('"+ i +"', '"+classificacaoCorr.get(i)+"', '"+Integer.parseInt(cr.getCodCorr())+"')");
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public int sizeReg() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM registo")) {
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

    public void addReg(String codJog, String codPiloto, String codCarro, String codCamp,String codReg) {
        int sReg = sizeReg() + 1;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("INSERT INTO registo VALUES ('"+Integer.parseInt(codReg)+"', '"+Integer.parseInt(codJog)+"', '"+Integer.parseInt(codCarro)+"', '"+Integer.parseInt(codPiloto)+"', '"+0+"', '"+Integer.parseInt(codCamp)+"')");
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }
}
