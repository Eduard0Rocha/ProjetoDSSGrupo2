package Data;

import carro.Carro;
import circuito.Circuito;
import circuito.CondicoesAtmosfericas;

import java.sql.*;

public class CircuitoDAO {
    private static CircuitoDAO singleton = null;

    public CircuitoDAO() {
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS circuito (" +
                    "codCirc int auto_increment primary key," +
                    "nvoltas int NOT NULL," +
                    "nomeCircuto varchar(45) NOT NULL," +
                    "localizacao varchar(45) NOT NULL," +
                    "distancia float NOT NULL ," +
                    "DRS int NOT NULL)";
            stm.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS condicoes (" +
                    "id_condicoes int NOT NULL PRIMARY KEY," +
                    "temperatura_Asf varchar(10) DEFAULT NULL," +
                    "humidade int NOT NULL,"+
                    "temperatura float NOT NULL,"+
                    "estado varchar(35)," +
                    "codCirc int NOT NULL," +
                    "foreign key(codCirc) references circuito(codCirc))";
            stm.executeUpdate(sql);

        } catch (SQLException e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }
    public Circuito get(Object key) {
        Circuito t = new Circuito();
        CondicoesAtmosfericas s=null;
        try {

            Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM circuito WHERE codCirc" +
                    "='"+key+"'");
            {
                if (rs.next()) {  // A chave existe na tabela

                    t=new Circuito(Integer.toString(rs.getInt("codCirc")),
                            rs.getInt("nvoltas"),
                            rs.getString("nomeCircuto"),
                            rs.getString("localizacao"),
                            rs.getFloat("distancia"),
                            rs.getInt("DRS"));

                    String sql = "SELECT * FROM condicoes WHERE codCirc='"+key+"'";
                    try (ResultSet rsa = stm.executeQuery(sql)) {
                        if (rsa.next()) {
                            s = new  CondicoesAtmosfericas(rsa.getFloat("temperatura_Asf"), rsa.getFloat("temperatura"), rsa.getInt("humidade"), rsa.getString("estado"));

                            t.setCondicoesATM(s);
                        }

                    } catch (Exception e){

                    }
                                    // BD inconsistente!! Sala nÃ£o existe - tratar com excepÃ§Ãµes.
                } // catch Ã© feito no try inicial - este try serve para fechar o ResultSet automaticamente
                // Nota: abrir um novo ResultSet no mesmo Statement fecha o ResultSet anterior
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
                     stm.executeQuery("SELECT Id FROM circuito WHERE codCirc='"+key.toString()+"'")) {
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
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {

            /*
            stm.executeUpdate(
                    "INSERT INTO condicoes " +
                            "VALUES ('"+ s.get()+ "', '"+
                            s.getEdificio()+"', "+
                            s.getCapacidade()+") " +
                            "ON DUPLICATE KEY UPDATE Edificio=Values(Edificio), " +
                            "Capacidade=Values(Capacidade)");*/

            stm.executeUpdate(
                    "INSERT INTO circuito VALUES ('"+t.getCodCircuito()+"', '"+t.getNvoltas()+"', '"+t.getNomeCircuito()+"', '"+t.getLocalizacao()+"', '"+t.getDistancia()+"', '"+t.getDRS()+"') ");


        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }





}
