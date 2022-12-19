package Data;

import carro.*;
import circuito.Circuito;
import circuito.CondicoesAtmosfericas;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import static java.util.stream.Collectors.toList;

import static java.util.stream.Collectors.*;

public class CarroDAO {
    private static CarroDAO singleton = null;



    public int getmaxkey()
    {

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




    public int size()
    {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM carro")) {
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



    public CarroDAO() {
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS carro (" +
                    "codCarro int auto_increment primary key," +
                    "marca varchar(45) NOT NULL," +
                    "modelo varchar(45) NOT NULL," +
                    "cilindrada int NOT NULL," +
                    "potencia int," +
                    "fiabilidade int," +
                    "PAC float ," +
                    "distPerc float ," +
                    "DNF boolean," +
                    "tempo long ,"+
                    "classe varchar(10))";
            stm.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS C1 ("+
                    "idCarro int primary key,"+
                    "foreign key(idCarro) references carro(codCarro))";
            stm.executeUpdate(sql);


            sql = "CREATE TABLE IF NOT EXISTS C1H ("+
                    "potencia_motor_eletrico int NOT NULL,"+
                    "idCarro int primary key,"+
                    "foreign key(idCarro) references carro(codCarro))";
            stm.executeUpdate(sql);


            sql = "CREATE TABLE IF NOT EXISTS C2 ("+
                    "afinacao_mecanica int NOT NULL,"+
                    "isValid boolean NOT NULL,"+
                    "idCarro int primary key,"+
                    "foreign key(idCarro) references carro(codCarro))";
            stm.executeUpdate(sql);


            sql = "CREATE TABLE IF NOT EXISTS C2H ("+
                    "potencia_motor_eletrico int NOT NULL,"+
                    "idCarro int primary key,"+
                    "foreign key(idCarro) references carro(codCarro))";
            stm.executeUpdate(sql);


            sql = "CREATE TABLE IF NOT EXISTS GT ("+
                    "isValid boolean NOT NULL,"+
                    "idCarro int primary key,"+
                    "foreign key(idCarro) references carro(codCarro))";
            stm.executeUpdate(sql);


            sql = "CREATE TABLE IF NOT EXISTS GTH ("+
                    "potencia_motor_eletrico int NOT NULL,"+
                    "idCarro int primary key,"+
                    "foreign key(idCarro) references carro(codCarro))";
            stm.executeUpdate(sql);


            sql = "CREATE TABLE IF NOT EXISTS SC ("+
                    "piloto_cts float NOT NULL,"+
                    "piloto_sva float NOT NULL,"+
                    "isValid boolean NOT NULL,"+
                    "idCarro int primary key,"+
                    "foreign key(idCarro) references carro(codCarro))";
            stm.executeUpdate(sql);


            sql = "CREATE TABLE IF NOT EXISTS Pneu ("+
                    "idPneu int auto_increment primary key,"+
                    "tipo varchar(30) NOT NULL,"+
                    "estado int NOT NULL,"+
                    "idCarro int NOT NULL,"+
                    "foreign key(idCarro) references carro(codCarro))";
            stm.executeUpdate(sql);


        } catch (SQLException e)
        {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public static CarroDAO getInstance() {
        if (CarroDAO.singleton == null) {
            CarroDAO.singleton = new CarroDAO();
        }
        return CarroDAO.singleton;
    }


    public Carro get(Object key)
    {
        try {

             Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM carro WHERE codCarro" + "='"+key+"'");
             {
            if (rs.next())
            {  // A chave existe na tabela
                String classe = rs.getString("classe");

                switch (classe)
                {
                    case "C1":
                        C1 t1 = new C1(rs.getString("marca"), rs.getString("modelo"), rs.getInt("cilindrada"), rs.getInt("potencia"), rs.getInt("fiabilidade"), rs.getInt("pac"), rs.getString("codCarro"), "");
                        System.out.println(t1.toString());
                        return t1;

                    case "C1H":
                        C1H t2 = new C1H(rs.getString("marca"), rs.getString("modelo"), rs.getInt("cilindrada"), rs.getInt("potencia"), rs.getInt("fiabilidade"), rs.getInt("pac"), rs.getString("codCarro"), "",rs.getInt("potencia_motor_eletrico"));
                        System.out.println(t2.toString());
                        return t2;

                    case "C2":
                        C2 t3 = new C2(rs.getString("marca"), rs.getString("modelo"), rs.getInt("cilindrada"), rs.getInt("potencia"), rs.getInt("fiabilidade"), rs.getInt("pac"), rs.getString("codCarro"), "",rs.getInt("afinacao_mecanica"));
                        System.out.println(t3.toString());
                        return t3;

                    case "C2H":
                        C2H t4 = new C2H(rs.getString("marca"), rs.getString("modelo"), rs.getInt("cilindrada"), rs.getInt("potencia"), rs.getInt("fiabilidade"), rs.getInt("pac"), rs.getString("codCarro"), "",rs.getInt("afinacao_mecanica"),rs.getInt("potencia_motor_eletrico"));
                        System.out.println(t4.toString());
                        return t4;

                    case "GT":
                        GT t5 = new GT(rs.getString("marca"), rs.getString("modelo"), rs.getInt("cilindrada"), rs.getInt("potencia"), rs.getInt("fiabilidade"), rs.getInt("pac"),rs.getString("codCarro"),"");
                        System.out.println(t5.toString());
                        return t5;

                    case "GTH":
                        GTH t6 = new GTH(rs.getString("marca"), rs.getString("modelo"), rs.getInt("cilindrada"), rs.getInt("potencia"), rs.getInt("fiabilidade"), rs.getInt("pac"), rs.getString("codCarro"), "",rs.getInt("potencia_motor_eletrico"));
                        System.out.println(t6.toString());
                        return t6;

                    case "SC":
                        SC t7 = new SC(rs.getString("marca"), rs.getString("modelo"), rs.getInt("cilindrada"), rs.getInt("potencia"), rs.getInt("fiabilidade"), rs.getInt("pac"), rs.getString("codCarro"), "");
                        System.out.println(t7.toString());
                        return t7;

                }
            }

                }
            }
        catch (SQLException e)
        {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return null;
    }


    public void put(Carro c)
    {
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement())
        {
            int dnf = 1;
            stm.executeUpdate(
                    "INSERT INTO carro " +
                            "VALUES ('"+ c.getCodCarro()+ "', '"+
                            c.getMarca()+"', '"+
                            c.getModelo()+"', '"+
                            c.getCilindrada()+"', '"+
                            c.getPotencia()+"', '"+
                            c.getFiabilidade()+"', '"+
                            c.getPAC()+"', '"+
                            c.getDistPerc()+"', '"+
                            c.getDNF()+"', '"+
                            c.getTempo()+"', '"+
                            c.getclasse()+"') ");



            if (c.getCategoria().equals("class carro.SC"))
            {
               SC aux = (SC) c;

               stm.executeUpdate(
                  "INSERT INTO sc VALUES ('"+aux.getPiloto_cts()+"', '"+aux.getPiloto_sva()+"', '"+aux.isValid()+"', '"+c.getCodCarro()+"') ");

            }



        } catch (SQLException e)
        {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }





}


