package Business;

import campeonato.Campeonato;
import carro.Carro;
import circuito.Circuito;
import circuito.Exceptions.NonExistantKey;
import piloto.Piloto;
import users.Admin;
import users.Jogador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface F1Manager {
     void addPiloto(String nome, int cts, int sva);
     HashMap<String, Piloto> getpilotos() throws SQLException;
     boolean removePiloto(String codigo);
     HashMap<String, Campeonato> getCampeonatos() throws SQLException;
     void createCampeonato(String nCamp) throws SQLException;
     HashMap<String, Carro> getCarros() throws SQLException;
     boolean existsCampeonato(String cCamp);
     boolean existsUser(String cJog) throws SQLException;
     boolean existsPil(String codPil) throws SQLException;
     boolean existsCarro(String codCarro) throws SQLException;
     void addRegisto(String cJog, String cPl, String cCar, String cCamp) throws SQLException;
     boolean validUser(String username, String password) throws SQLException;
     boolean existsAdmin(String username) throws SQLException;
     boolean validPasswordAdmin(String username, String password) throws SQLException;
     boolean validGuest(String username) throws SQLException;
     boolean containsUNAME(String username) throws SQLException;
     boolean createAPlayer(String name, String username, String password) throws SQLException, CloneNotSupportedException;
     boolean createAdmin(String name, String contacto, String email, String username, String password) throws SQLException, CloneNotSupportedException;
     boolean createGuest(String username) throws SQLException, CloneNotSupportedException;
     HashMap<String, Admin> getAdmins() throws SQLException, CloneNotSupportedException;
     HashMap<String, Jogador> getPlayers() throws SQLException, CloneNotSupportedException;
     boolean createC1(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String tipoPneus);
     boolean createC1H(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float pac, String tipoPneus, int potencia_motor_eletrico);

     boolean createC2(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float pac, String tipoPneus, int a_mecanica);
     boolean createC2H(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float pac, String tipo_de_pneus, int afinacao_mecanica, int potencia_motor_eletrico);
     boolean createGT(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float pac, String tipoPneus);
     boolean createGTH(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float pac, String tipoPneus, int potencia_motor_eletrico);

     boolean createSC(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float pac, String tipoPneus);

     boolean removeCarro(String cod);
     boolean  createCicruito(int voltas, String ncirc, String local, float dist, List<Integer> curvasList, List<Integer> retaslist, List<Integer> chicaneList, float temperatura, int humidade, float temperatura_asf, String estado_climaterico, int DRS) throws SQLException;

     HashMap<String, Circuito>  getCircuitos() throws SQLException, NonExistantKey;
     boolean  removeCircuito(String cod);

     boolean  removeCamp(String cod);

     ArrayList<Circuito> getCircuitosCamp(String cod) throws SQLException, NonExistantKey;
     ArrayList<Jogador> getJogadoresCamp(String cod) throws SQLException;
     HashMap<String, Integer> getClassificacaoC(String cod);

    public HashMap<String, Integer> getClassificacaoCH(String cod);
    public boolean addCorridaCamp(String cCamp,String cCir) throws SQLException, NonExistantKey;
}
