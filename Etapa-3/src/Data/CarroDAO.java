package Data;

import carro.*;
import circuito.Circuito;
import circuito.CondicoesAtmosfericas;
import piloto.Piloto;

import java.sql.*;
import java.util.*;

import static java.util.stream.Collectors.toList;

import static java.util.stream.Collectors.*;

public class CarroDAO {
    private static CarroDAO singleton = null;


    public int getmaxkey() {

        int res = 0;
        if (this.size() == 0) return 0;
        else {
            try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
                 Statement stm = conn.createStatement()) {


                ResultSet rs = stm.executeQuery("SELECT MAX(codCarro) FROM carro");
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


    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM carro")) {
            if (rs.next()) {
                i = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return i;
    }


    public CarroDAO() {
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS carro (" +
                    "codCarro int  primary key," +
                    "marca varchar(45) NOT NULL," +
                    "modelo varchar(45) NOT NULL," +
                    "cilindrada int NOT NULL," +
                    "potencia int," +
                    "fiabilidade int," +
                    "PAC float ," +
                    "distPerc float ," +
                    "DNF int(1)," +
                    "tempo long ," +
                    "classe varchar(10))";
            stm.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS C1 (" +
                    "idCarro int primary key," +
                    "foreign key(idCarro) references carro(codCarro))";
            stm.executeUpdate(sql);


            sql = "CREATE TABLE IF NOT EXISTS C1H (" +
                    "potencia_motor_eletrico int NOT NULL," +
                    "idCarro int primary key," +
                    "foreign key(idCarro) references carro(codCarro))";
            stm.executeUpdate(sql);


            sql = "CREATE TABLE IF NOT EXISTS C2 (" +
                    "afinacao_mecanica int NOT NULL," +
                    "idCarro int primary key," +
                    "foreign key(idCarro) references carro(codCarro))";
            stm.executeUpdate(sql);


            sql = "CREATE TABLE IF NOT EXISTS C2H (" +
                    "potencia_motor_eletrico int NOT NULL," +
                    "afinacao int NOT NULL," +
                    "idCarro int primary key," +
                    "foreign key(idCarro) references carro(codCarro))";
            stm.executeUpdate(sql);


            sql = "CREATE TABLE IF NOT EXISTS GT (" +
                    "idCarro int primary key," +
                    "foreign key(idCarro) references carro(codCarro))";
            stm.executeUpdate(sql);


            sql = "CREATE TABLE IF NOT EXISTS GTH (" +
                    "potencia_motor_eletrico int NOT NULL," +
                    "idCarro int primary key," +
                    "foreign key(idCarro) references carro(codCarro))";
            stm.executeUpdate(sql);


            sql = "CREATE TABLE IF NOT EXISTS SC (" +
                    "piloto_cts float NOT NULL," +
                    "piloto_sva float NOT NULL," +
                    "idCarro int primary key," +
                    "foreign key(idCarro) references carro(codCarro))";
            stm.executeUpdate(sql);


            sql = "CREATE TABLE IF NOT EXISTS Pneu (" +
                    "tipo varchar(30) NOT NULL," +
                    "estado int NOT NULL," +
                    "idCarro int NOT NULL," +
                    "foreign key(idCarro) references carro(codCarro))";
            stm.executeUpdate(sql);


        } catch (SQLException e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    /*
    public static CarroDAO getInstance() {
        if (CarroDAO.singleton == null) {
            CarroDAO.singleton = new CarroDAO();
        }
        return CarroDAO.singleton;
    }
*/

    public static Carro get(Object key) {
        try {

            Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM carro WHERE codCarro" + "='" + key + "'");
            {
                if (rs.next()) {  // A chave existe na tabela
                    String classe = rs.getString("classe");

                    String cod = Integer.toString(rs.getInt("codCarro"));
                    String marca = rs.getString("marca");
                    String modelo = rs.getString("modelo");
                    int cilindrada = rs.getInt("cilindrada");
                    int potencia = rs.getInt("potencia");
                    int fiabilidade = rs.getInt("fiabilidade");
                    float pac = rs.getFloat("PAC");
                    float distPerc = rs.getFloat("distPerc");
                    int DNF = rs.getInt("DNF");
                    int tempo = rs.getInt("tempo");


                    ArrayList<Pneu> pneus = new ArrayList<>();
                    try (ResultSet cr1 = s.executeQuery("select * from pneu where idCarro" +
                            "='" + cod + "'");) {

                        while (cr1.next()) {
                            Pneu a = new Pneu(cr1.getString("tipo"), cr1.getInt("estado"), cod);
                            pneus.add(a);
                        }
                    }


                    if (classe.equals("C1")) {
                        C1 classe1 = new C1(marca, modelo, cilindrada, potencia, fiabilidade, pac, cod, pneus.get(0).getTipo());
                        classe1.setPneus(pneus);
                        return classe1.clone();


                    }
                    if (classe.equals("C1H")) {
                        int potencia_ele = 0;
                        try (ResultSet cr2 = s.executeQuery("select * from c1h where idCarro" +
                                "='" + cod + "'");) {
                            while (cr2.next()) {
                                potencia_ele = cr2.getInt("potencia_motor_eletrico");
                            }
                            C1H a = new C1H(marca, modelo, cilindrada, potencia, fiabilidade, pac, cod, pneus.get(0).getTipo(), potencia_ele);
                            a.setPneus(pneus);
                            return a.clone();
                        }
                    }
                    if (classe.equals("C2")) {
                        int afinacao = 0;
                        try (ResultSet cr3 = s.executeQuery("select * from c2 where idCarro" +
                                "='" + cod + "'");) {
                            while (cr3.next()) {
                                afinacao = cr3.getInt("afinacao_mecanica");
                            }
                            C2 a = new C2(marca, modelo, cilindrada, potencia, fiabilidade, pac, cod, pneus.get(0).getTipo(), afinacao);
                            a.setPneus(pneus);
                            return a.clone();
                        }
                    }
                    if (classe.equals("C2H")) {
                        int pot_ele = 0;
                        int adinacao = 0;
                        try (ResultSet cr4 = s.executeQuery("select * from c2h where idCarro" +
                                "='" + cod + "'");) {
                            while (cr4.next()) {
                                pot_ele = cr4.getInt("potencia_motor_eletrico");
                                adinacao = cr4.getInt("afinacao");
                            }
                            C2H a = new C2H(marca, modelo, cilindrada, potencia, fiabilidade, pac, cod, pneus.get(0).getTipo(), adinacao, pot_ele);
                            a.setPneus(pneus);
                            return a.clone();
                        }
                    }
                    if (classe.equals("GT")) {

                        GT a = new GT(marca, modelo, cilindrada, potencia, fiabilidade, pac, cod, pneus.get(0).getTipo());
                        a.setPneus(pneus);
                        return a.clone();
                    }
                    if (classe.equals("GTH")) {
                        int pot_ele = 0;
                        try (ResultSet cr5 = s.executeQuery("select * from gth where idCarro" +
                                "='" + cod + "'");) {
                            while (cr5.next()) {
                                pot_ele = cr5.getInt("potencia_motor_eletrico");
                            }
                            GTH a = new GTH(marca, modelo, cilindrada, potencia, fiabilidade, pac, cod, pneus.get(0).getTipo(), pot_ele);
                            a.setPneus(pneus);
                            return a.clone();
                        }
                    }
                    if (classe.equals("SC")) {
                        int pilotoCTS = 0;
                        int pilotoSVA = 0;
                        try (ResultSet cr6 = s.executeQuery("select * from sc where idCarro" +
                                "='" + cod + "'");) {
                            while (cr6.next()) {
                                pilotoCTS = cr6.getInt("piloto_cts");
                                pilotoSVA = cr6.getInt("piloto_sva");
                            }
                            SC a = new SC(marca, modelo, cilindrada, potencia, fiabilidade, pac, cod, pneus.get(0).getTipo());
                            a.setPiloto_cts(pilotoCTS);
                            a.setPiloto_sva(pilotoSVA);
                            a.setPneus(pneus);
                            return a.clone();
                        }
                    }

                }

            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return null;
    }

    public HashMap<String, Carro> getCarrosDB() throws SQLException {

        try {
            Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
            Statement s = conn.createStatement();
            try (ResultSet rs = s.executeQuery("select * from carro")) {
                HashMap<String, Carro> carros = new HashMap<>();
                while (rs.next()) {
                    String codCarro = rs.getString("codCarro");
                    carros.put(codCarro, this.get(codCarro));

                }
                return carros;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new NullPointerException(e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    public void put(Carro c) {
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            int dnf = 1;
            stm.executeUpdate(
                    "INSERT INTO carro " +
                            "VALUES ('" + c.getCodCarro() + "', '" +
                            c.getMarca() + "', '" +
                            c.getModelo() + "', '" +
                            c.getCilindrada() + "', '" +
                            c.getPotencia() + "', '" +
                            c.getFiabilidade() + "', '" +
                            c.getPAC() + "', '" +
                            c.getDistPerc() + "', '" +
                            c.getDNFINT() + "', '" +
                            c.getTempo() + "', '" +
                            c.getclasse() + "') ");

            if (c.getCategoria().equals("class carro.SC")) {
                SC aux = (SC) c;

                stm.executeUpdate(
                        "INSERT INTO sc VALUES ('" + aux.getPiloto_cts() + "', '" + aux.getPiloto_sva() + "', '" + c.getCodCarro() + "') ");
            }
            if (c.getCategoria().equals("class carro.C1")) {
                C1 aux = (C1) c;

                stm.executeUpdate(
                        "INSERT INTO c1 VALUES ('" + c.getCodCarro() + "') ");
            }
            if (c.getCategoria().equals("class carro.C1H")) {
                C1H aux = (C1H) c;

                stm.executeUpdate(
                        "INSERT INTO c1h VALUES ('" + aux.getPotEletrico() + "', '" + c.getCodCarro() + "') ");
            }
            if (c.getCategoria().equals("class carro.C2")) {
                C2 aux = (C2) c;

                stm.executeUpdate(
                        "INSERT INTO c2 VALUES ('" + aux.getAfinacao_mecanica() + "', '" + c.getCodCarro() + "') ");
            }
            if (c.getCategoria().equals("class carro.C2H")) {
                C2H aux = (C2H) c;

                stm.executeUpdate(
                        "INSERT INTO c2H VALUES ('" + aux.getPotEletrico() + "', '" + aux.getAfinacao_mecanica() + "', '" + c.getCodCarro() + "') ");
            }
            if (c.getCategoria().equals("class carro.GT")) {
                GT aux = (GT) c;

                stm.executeUpdate(
                        "INSERT INTO gt VALUES ('" + c.getCodCarro() + "') ");
            }
            if (c.getCategoria().equals("class carro.GTH")) {
                GTH aux = (GTH) c;

                stm.executeUpdate(
                        "INSERT INTO gth VALUES ('" + aux.getPotEletrico() + "', '" + c.getCodCarro() + "') ");
            }

            ArrayList<Pneu> pneu = c.getPneus();
            for (int i = 0; i < pneu.size(); i++) {
                stm.executeUpdate("INSERT INTO pneu " +
                        "VALUES ('" + pneu.get(i).getTipo() + "', '" +
                        pneu.get(i).getEstado() + "', '" +
                        c.getCodCarro() + "')");
            }


        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public boolean containsKey(Object key) {
        boolean r;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs =
                     stm.executeQuery("SELECT * FROM carro WHERE codCarro='"+key.toString()+"'")) {
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    public boolean remove(Object key) {
        if (this.containsKey(key)) {
            Carro t = this.get(key);
            boolean k = false;
            try {
                Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
                Statement stm = conn.createStatement();
                stm.executeUpdate("DELETE FROM pneu WHERE idCarro='" + key + "'");

                String classe = t.getclasse();
                if (classe.equals("C1")) {
                    stm.executeUpdate("DELETE FROM c1 WHERE idCarro='" + key + "'");
                }
                if (classe.equals("C1H")) {
                    stm.executeUpdate("DELETE FROM c1h WHERE idCarro='" + key + "'");
                }
                if (classe.equals("C2")) {
                    stm.executeUpdate("DELETE FROM c2 WHERE idCarro='" + key + "'");
                }
                if (classe.equals("C2H")) {
                    stm.executeUpdate("DELETE FROM c2h WHERE idCarro='" + key + "'");
                }
                if (classe.equals("GT")) {
                    stm.executeUpdate("DELETE FROM gt WHERE idCarro='" + key + "'");
                }
                if (classe.equals("GTH")) {
                    stm.executeUpdate("DELETE FROM gth WHERE idCarro='" + key + "'");
                }
                if (classe.equals("SC")) {
                    stm.executeUpdate("DELETE FROM sc WHERE idCarro='" + key + "'");
                }

                stm.executeUpdate("DELETE FROM carro WHERE codCarro='" + key + "'");
                k = true;
            } catch (Exception e) {
                // Database error!
                e.printStackTrace();
                throw new NullPointerException(e.getMessage());

            }
            return k;
        }
        return false;
    }

}







