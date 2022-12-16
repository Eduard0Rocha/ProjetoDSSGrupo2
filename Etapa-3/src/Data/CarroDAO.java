package src.Data;



import src.carro.C1;
import src.carro.Carro;
import src.carro.SGestCarros;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import static java.util.stream.Collectors.toList;

import static java.util.stream.Collectors.*;

/**
 * VersÃ£o incompleta de um DAO para Turmas
 *
 * Tabelas a criar na BD: ver mÃ©todo getInstance
 *
 * @author JFC
 * @version 20201208
 */
public class CarroDAO {
    private static CarroDAO singleton = null;

    public CarroDAO() {
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS carro (" +
                    "codCarro int auto_increment primary key," +
                    "marca varchar(45) NOT NULL," +
                    "modelo varchar(45) NOT NULL," +
                    "cilindrada int NOT NULL," +
                    "potencia int  ," +
                    "fiabilidade int," +
                    "PAC float ," +
                    "distPerc float ," +
                    "DNF boolean," +
                    "tempo long )" ;
            stm.executeUpdate(sql);

          /*
            sql = "CREATE TABLE IF NOT EXISTS turmas (" +
                    "Id varchar(10) NOT NULL PRIMARY KEY," +
                    "Sala varchar(10) DEFAULT NULL," +
                    "foreign key(Sala) references salas(Num))";
            stm.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS alunos (" +
                    "Num varchar(10) NOT NULL PRIMARY KEY," +
                    "Nome varchar(45) DEFAULT NULL," +
                    "Email varchar(45) DEFAULT NULL," +
                    "Turma varchar(10), foreign key(Turma) references turmas(Id))";
            stm.executeUpdate(sql);
            */
        } catch (SQLException e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    /**
     * ImplementaÃ§Ã£o do padrÃ£o Singleton
     *
     * @return devolve a instÃ¢ncia Ãºnica desta classe
     */
    public static CarroDAO getInstance() {
        if (CarroDAO.singleton == null) {
            CarroDAO.singleton = new CarroDAO();
        }
        return CarroDAO.singleton;
    }
    public Carro get(Object key) {
        Carro t = null;
        try {

             Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM carro WHERE codCarro" +
                     "='"+key+"'");
             {
            if (rs.next()) {  // A chave existe na tabela

                System.out.println( "Código :" + rs.getString("codCarro"));
                System.out.println("Marca :" + rs.getString("marca"));
                System.out.println("Marca :" + rs.getString("modelo"));
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

}


