package Data;

import carro.Carro;
import circuito.Circuito;
import circuito.CondicoesAtmosfericas;
import piloto.Piloto;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CircuitoDAO {
    private static CircuitoDAO singleton = null;

    public CircuitoDAO() {
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS circuito (" +
                    "codCirc int  primary key not null," +
                    "nvoltas int NOT NULL," +
                    "nomeCircuto varchar(45) NOT NULL," +
                    "localizacao varchar(45) NOT NULL," +
                    "distancia float NOT NULL ," +
                    "DRS int NOT NULL)";
            stm.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS condicoes (" +
                    "temperatura_Asf float DEFAULT NULL," +
                    "humidade int NOT NULL,"+
                    "temperatura float NOT NULL,"+
                    "estado varchar(50)," +
                    "codCirc int NOT NULL," +
                    "foreign key(codCirc) references circuito(codCirc))";
            stm.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS curva (" +
                    "nrCurva int not null," +
                    "GDU int not null," +
                    "codCirc int NOT NULL," +
                    "foreign key(codCirc) references circuito(codCirc))";
            stm.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS reta (" +
                    "nrReta int not null," +
                    "GDU int not null," +
                    "codCirc int NOT NULL," +
                    "foreign key(codCirc) references circuito(codCirc))";
            stm.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS chicane (" +
                    "nrChicane int not null," +
                    "GDU int not null," +
                    "codCirc int NOT NULL," +
                    "foreign key(codCirc) references circuito(codCirc))";
            stm.executeUpdate(sql);

        } catch (SQLException e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public static CircuitoDAO getInstance() {
        if (CircuitoDAO.singleton == null) {
            CircuitoDAO.singleton = new CircuitoDAO();
        }
        return CircuitoDAO.singleton;
    }

    public static Circuito get(Object key) {
        Circuito t = new Circuito();
        String codCirc="0";
        int voltas=0;
        String nome="";
        String local="";
        Float distancia= (float) 0;
        int DRS=0;

        CondicoesAtmosfericas s=null;
        try {
            Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM circuito WHERE codCirc" +
                    "='"+key+"'");
             if (rs.next()) {
             codCirc= Integer.toString(rs.getInt("codCirc"));
             voltas =rs.getInt("nvoltas");
             nome= rs.getString("nomeCircuto");
             local = rs.getString("localizacao");
              distancia = rs.getFloat("distancia");
              DRS = rs.getInt("DRS");
             }

            {
                    List<Integer> retas = new ArrayList<>();
                try (ResultSet cr1 = stm.executeQuery("select * from reta where codCirc" +
                        "='"+key+"'");)
                {
                    while (cr1.next()) {
                        retas.add(cr1.getInt("GDU"));
                    }
                }


                    List<Integer> curvas = new ArrayList<>();
                try (ResultSet cr2 = stm.executeQuery("select * from curva where codCirc" +
                        "='"+key+"'");)
                {
                    while (cr2.next()) {
                        curvas.add(cr2.getInt("GDU"));
                    }
                }

                List<Integer> chicanes = new ArrayList<>();
                try (ResultSet cr3 = stm.executeQuery("select * from chicane where codCirc" +
                        "='"+key+"'");)
                {
                    while (cr3.next())
                    {
                        chicanes.add(cr3.getInt("GDU"));
                    }
                }

                    t=new Circuito(codCirc,voltas,nome,local,distancia,DRS,curvas,retas,chicanes);

                    String sql = "SELECT * FROM condicoes WHERE codCirc='"+key+"'";
                    try (ResultSet rsa = stm.executeQuery(sql)) {
                        if (rsa.next()) {
                            s = new  CondicoesAtmosfericas(rsa.getFloat("temperatura_Asf"), rsa.getFloat("temperatura"), rsa.getInt("humidade"), rsa.getString("estado"));

                            t.setCondicoesATM(s);
                        }

                    } catch (Exception e){

                    }
                }
            }

        catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return t;
    }



    /*
     *Devolve o número de circuitos na BD
     */
    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM circuito")) {
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

    /*
     *Verifica se existe um circuito com uma chave na BD
     */
    public boolean containsKey(Object key) {
        boolean r;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs =
                     stm.executeQuery("SELECT * FROM circuito WHERE codCirc='"+key.toString()+"'")) {
            r = rs.next();
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    /*
     *Verifica se existe um circuito existe na BD
     */
    public boolean containsValue(Object value) {
        Circuito t = (Circuito) value;
        return this.containsKey(t.getCodCircuito());
    }

    public Circuito put(String key, Circuito t) {
        Circuito res = null;
        CondicoesAtmosfericas s = t.getCondicoesATM();
        List<Integer> curvas = t.getCurvasGDU();
        List<Integer> retas = t.getRetasGDU();
        List<Integer> chicane=t.getChicanesGDU();

        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
                stm.executeUpdate("INSERT INTO circuito VALUES ('"+t.getCodCircuito()+"', '"+t.getNvoltas()+"', '"+t.getNomeCircuito()+"', '"+t.getLocalizacao()+"', '"+t.getDistancia()+"', '"+t.getDRS()+"') ");
                stm.executeUpdate(
                    "INSERT INTO condicoes " +
                            "VALUES ('"+ s.getTemperaturaAsf()+ "', '"+
                            s.getHumidade()+"', '"+
                            s.getTemperatura()+"', '"+
                            s.getEstado_climaterico()+"', '"+
                            t.getCodCircuito()+"')");
            stm.executeUpdate("INSERT INTO condicoes " +
                            "VALUES ('"+ s.getTemperaturaAsf()+ "', '"+
                            s.getHumidade()+"', '"+
                            s.getTemperatura()+"', '"+
                            s.getEstado_climaterico()+"', '"+
                            t.getCodCircuito()+"')");


            for(int i=0;i<curvas.size();i++) {
                stm.executeUpdate("INSERT INTO curva " +
                        "VALUES ('" + i + "', '" +
                        curvas.get(i) + "', '" +
                        t.getCodCircuito() + "')");
            }
            for(int i=0;i<retas.size();i++) {
                stm.executeUpdate("INSERT INTO reta " +
                        "VALUES ('" + i + "', '" +
                        retas.get(i) + "', '" +
                        t.getCodCircuito() + "')");
            }
            for(int i=0;i<chicane.size();i++) {
                stm.executeUpdate("INSERT INTO chicane " +
                        "VALUES ('" + i + "', '" +
                        chicane.get(i) + "', '" +
                        t.getCodCircuito() + "')");
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


                ResultSet rs = stm.executeQuery("SELECT MAX(codCirc) FROM circuito");
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

    public HashMap<String,Circuito> getCircuitosDB() throws SQLException {

        try {
            Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
            Statement s = conn.createStatement();
            try (ResultSet rs = s.executeQuery("select * from circuito")) {
                HashMap<String, Circuito> circuitos = new HashMap<>();
                while (rs.next()) {
                    String codCirc = rs.getString("codCirc");
                    circuitos.put(codCirc, this.get(codCirc));
                }

                return circuitos;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

        public boolean remove(Object key) {
            Circuito t = this.get(key);
            boolean k=false;
            try {
                Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
                Statement stm = conn.createStatement();
                stm.executeUpdate("DELETE FROM curva WHERE codCirc='"+key+"'");
                stm.executeUpdate("DELETE FROM reta WHERE codCirc='"+key+"'");
                stm.executeUpdate("DELETE FROM chicane WHERE codCirc='"+key+"'");
                stm.executeUpdate("DELETE FROM condicoes WHERE codCirc='"+key+"'");
                stm.executeUpdate("DELETE FROM circuito WHERE codCirc='"+key+"'");
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
