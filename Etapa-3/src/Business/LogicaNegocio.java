package Business;

import campeonato.Campeonato;
import campeonato.CampeonatosFacade;
import carro.Carro;
import carro.CarrosFacade;
import circuito.Circuito;
import circuito.CircuitosFacade;
import circuito.Exceptions.NonExistantKey;
import piloto.Piloto;
import piloto.PilotoFacade;
import users.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class LogicaNegocio implements  F1Manager{

    private static Scanner ler = new Scanner(System.in);
    private static PilotoFacade pil;
    private static CircuitosFacade circ;
    private AuthenticatedPlayer jogadorAutenticado;
    private Guest guest;
    private Admin admin;

    private static CarrosFacade carr;
    private static UserFacade users;
    private static CampeonatosFacade camp;

    static {
        try {
            pil = new PilotoFacade();
            circ = new CircuitosFacade();
            users = new UserFacade();
            carr = new CarrosFacade();
            camp = new CampeonatosFacade();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NonExistantKey e) {
            throw new RuntimeException(e);
        }
    }

    public void addPiloto(String nome, int cts, int sva) {
        pil.addPiloto(nome, cts, sva);
    }

    public HashMap<String, Piloto> getpilotos() throws SQLException {
        return pil.getPilotos();
    }

    public boolean removePiloto(String codigo) {
        return pil.removePiloto(codigo);
    }

    public HashMap<String, Campeonato> getCampeonatos() throws SQLException {
        return camp.getCampeonatos();
    }

    public void createCampeonato(String nCamp) throws SQLException {
        camp.createCampeonato(nCamp);
    }

    public HashMap<String, Carro> getCarros() throws SQLException {
        return carr.getCarros();
    }

    public boolean existsCampeonato(String cCamp) {
        return camp.existsCampeonato(cCamp);
    }

    public boolean existsUser(String cJog) throws SQLException {
        return users.existeJogador(cJog);
    }

    public boolean existsPil(String codPil) throws SQLException {
        return pil.existsPiloto(codPil);
    }

    public boolean existsCarro(String codCarro) throws SQLException {
        return (carr.existeCarros(codCarro));
    }

    public void addRegisto(String cJog, String cPl, String cCar, String cCamp) throws SQLException {
        camp.addRegisto(cJog, cPl, cCar, cCamp);
    }

    public boolean validUser(String username, String password) throws SQLException {
        return users.validUser(username, password);
    }

    public boolean existsAdmin(String username) throws SQLException {
        return users.existeAdmin(username);
    }

    public boolean validPasswordAdmin(String username, String password) throws SQLException {
        return users.validPasswordAdmin(username, password);
    }

    public boolean validGuest(String username) throws SQLException {
        return users.validGuest(username);
    }

    public boolean containsUNAME(String username) throws SQLException {
        return users.containsUNAME(username);
    }

    public boolean createAPlayer(String name, String username, String password) throws SQLException, CloneNotSupportedException {
        return users.createAPlayer(name, username, password);
    }

    public boolean createAdmin(String name, String contacto, String email, String username, String password) throws SQLException, CloneNotSupportedException {
        return users.createAdmin(name, contacto, email, username, password);
    }

    public boolean createGuest(String username) throws SQLException, CloneNotSupportedException {
        return users.createGuest(username);
    }

    public HashMap<String, Admin> getAdmins() throws SQLException, CloneNotSupportedException {
        return users.getAdmins();
    }

    public HashMap<String, Jogador> getPlayers() throws SQLException, CloneNotSupportedException {
        return users.getPlayers();
    }

    public boolean createC1(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String tipoPneus) {
        return carr.createC1(marca, modelo, cilindrada, potencia, fiabilidade, PAC, tipoPneus);
    }

    public boolean createC1H(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float pac, String tipoPneus, int potencia_motor_eletrico) {
        return carr.createC1H(marca, modelo, cilindrada, potencia, fiabilidade, pac, tipoPneus, potencia_motor_eletrico);
    }

    public boolean createC2(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float pac, String tipoPneus, int a_mecanica) {
        return carr.createC2(marca, modelo, cilindrada, potencia, fiabilidade, pac, tipoPneus, a_mecanica);
    }

    public boolean createC2H(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float pac, String tipo_de_pneus, int afinacao_mecanica, int potencia_motor_eletrico) {
        return carr.createC2H(marca, modelo, cilindrada, potencia, fiabilidade, pac, tipo_de_pneus, afinacao_mecanica, potencia_motor_eletrico);
    }

    public boolean createGT(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float pac, String tipoPneus) {
        return carr.createGT(marca, modelo, cilindrada, potencia, fiabilidade, pac, tipoPneus);
    }

    public boolean createGTH(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float pac, String tipoPneus, int potencia_motor_eletrico) {
        return carr.createGTH(marca, modelo, cilindrada, potencia, fiabilidade, pac, tipoPneus, potencia_motor_eletrico);
    }

    public boolean createSC(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float pac, String tipoPneus){
   return  carr.createSC(marca,modelo,cilindrada,potencia,fiabilidade,pac,tipoPneus);
}
    public boolean removeCarro(String cod){
        return  carr.removeCarro(cod);
    }

    public boolean  createCicruito(int voltas, String ncirc, String local, float dist, List<Integer> curvasList, List<Integer> retaslist, List<Integer> chicaneList, float temperatura, int humidade, float temperatura_asf, String estado_climaterico, int DRS) throws SQLException {
       return  circ.createCicruito(voltas,ncirc,local,dist,curvasList,retaslist,chicaneList,temperatura,humidade,temperatura_asf,estado_climaterico,DRS);
    }
    public HashMap<String, Circuito>  getCircuitos() throws SQLException, NonExistantKey {
        return  circ.getCircuitos();
    }
    public boolean  removeCircuito(String cod)  {
        return  circ.removeCircuito(cod);
    }
    public boolean  removeCamp(String cod) {
        return camp.removeCampeonato(cod);
    }
    public ArrayList<Circuito> getCircuitosCamp(String cod) {
        return camp.getCircuitos(cod);
    }

    public ArrayList<Jogador> getJogadoresCamp(String cod) {
        return camp.getJogadores(cod);
    }

    public HashMap<String, Integer> getClassificacaoC(String cod)  {
        return camp.getClassificacaoC(cod);
    }
    public HashMap<String, Integer> getClassificacaoCH(String cod)  {
        return camp.getClassificacaoCH(cod);
    }
    public boolean addCorridaCamp(String cCamp,String cCir) throws SQLException, NonExistantKey {
        return camp.addCorrida(cCamp, cCir);
    }
}

