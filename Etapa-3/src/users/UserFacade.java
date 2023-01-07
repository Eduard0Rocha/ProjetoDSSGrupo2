package users;

import java.sql.SQLException;
import java.util.HashMap;
import Data.*;

/**
 * Classe responsável pela interação com a informação relativa aos usuários
 */
public class UserFacade implements SGestaoUser {

    private HashMap<String,Admin> admins;
    private HashMap<String,Jogador> jogadores;

    private int Jogaadorcounter;
    private int Admincounter;
    private int GuestCounter;

    private JogadorAutenticadoDAO JogadorAutenticadoDAO;
    private JogadorDAO JogadorDAO;
    private GuestDAO GuestDAO;
    private AdminDAO AdminDAO;

    /**
     * Contrutor da classe que inicializa as estruturas de dados que contém a informação dos usuários
     */
    public UserFacade() throws SQLException {

        this.JogadorDAO = new JogadorDAO();
        this.GuestDAO = new GuestDAO();
        this.AdminDAO= new AdminDAO();
        this.JogadorAutenticadoDAO = new JogadorAutenticadoDAO();


        this.admins = AdminDAO.getAdminsDB();
        this.jogadores =JogadorDAO.getJogadoresDB();

        this.Jogaadorcounter = JogadorDAO.getmaxkey();
        this.Admincounter=AdminDAO.getmaxkey();
        this.GuestCounter=GuestDAO.getmaxkey();
    }

    /**
     * Método que adiciona um novo jogador autenticado
     * @param nome nome do jogador
     * @param username username do jogador
     * @param password password do jogador
     * @return true se foi bem sucedido, false caso contrário
     */
    @Override
    public boolean createAPlayer(String nome, String username, String password) throws CloneNotSupportedException {

        if (nome == null || username == null || password == null) return false;

        this.Jogaadorcounter++;
        String codJogador = new String( Integer.toString(this.Jogaadorcounter));



        AuthenticatedPlayer aux = new AuthenticatedPlayer(nome, codJogador, username, password);
        this.jogadores.put(codJogador,aux.clone());
        this.JogadorAutenticadoDAO.put(aux.clone());
        //System.out.println(aux);
        return true;
    }

    /**
     * Método que adiciona um novo administrador
     * @param nome nome do admin
     * @param ctcto contacto telefónico do admin
     * @param email email do admin
     * @param username username do admin
     * @param password password do admin
     * @return true se foi bem sucedido, false caso contrário
     */
    @Override
    public boolean createAdmin(String nome, String ctcto, String email, String username, String password) {

        if (nome == null || ctcto == null || email == null || username == null || password == null) return false;

        this.Admincounter++;
        String codAdmin = new String( Integer.toString(this.Admincounter));



        Admin aux = new Admin(nome, ctcto, email, codAdmin, username, password);

        this.admins.put(codAdmin, aux.clone());
        this.AdminDAO.put(Integer.toString(this.Admincounter), aux.clone());
       // System.out.println(aux);
        return true;
    }

    /**
     * Método que adiciona um novo jogador não autenticado
     * @param nome nome do jogador
     * @return true se foi bem sucedido, false caso contrário
     */
    @Override
    public boolean createGuest(String nome) {

        if (nome == null) return false;
        this.Jogaadorcounter++;
        String codJogador = new String( Integer.toString(Jogaadorcounter));

        this.GuestCounter++;
        String codGuest = new String(Integer.toString(GuestCounter));;
        Guest a=new Guest(nome, codJogador, codGuest);
        this.jogadores.put(codJogador,a.clone());
        this.GuestDAO.put(a.clone());
        return true;
    }

    /**
     * Método que adiciona pontos de corrida a um determinado jogador
     * @param codJogador código identificador do jogador
     * @param numPts número de pontos a atribuir
     * @return true se foi bem sucedido, false caso contrário
     */
    @Override
    public boolean addPontosCorr(String codJogador, int numPts) {

        if (codJogador == null) return false;

        Jogador j = this.jogadores.get(codJogador);

        if (j == null) return false;

        j.addPontosCorr(numPts);

        this.jogadores.put(codJogador, j);

        return true;
    }

    /**
     * Método que adiciona pontos globais a um determinado jogador
     * @param pontos número de pontos a atribuir
     * @param codJogador código identificador do jogador
     * @return true se foi bem sucedido, false caso contrário
     */
    @Override
    public boolean addPontosGlob(int pontos, String codJogador) {

        if (codJogador == null) return false;

        Jogador j = this.jogadores.get(codJogador);

        if (j == null) return false;

        j.addPontosGlob(pontos);

        this.jogadores.put(codJogador, j);

        return true;
    }

    /**
     * @param codJogador código identificador do jogador
     * @return número de pontos do jogador identificado pelo código de input (ou -1 se não encontrar o jogador)
     */


    /**
     * @param codJogador código identificador do jogador
     * @return jogador identificado pelo código de input
     */
    @Override
    public Jogador getJogador(String codJogador) {

        return this.jogadores.get(codJogador);
    }


    public boolean validUser(String userName,String pass) throws SQLException {


            return JogadorAutenticadoDAO.validUser(userName, pass);

    }

    public boolean existeUser(String userName) throws SQLException {
        if (JogadorAutenticadoDAO.existsUser(userName)) {
            return true;
        }
        return false;
        }
    public boolean existscodJog(String cod) throws SQLException {
        if (JogadorDAO.containskey(cod)) {
            return true;
        }
        return false;
    }

    public boolean existeAdmin(String userName) throws SQLException {
        if (AdminDAO.existsAdmin(userName)) {
            return true;
        }
        return false;
    }
    public boolean validPasswordAdmin(String userName,String pass) throws SQLException {


        return AdminDAO.validAdmin(userName, pass);

    }
    public boolean validGuest(String userName) throws SQLException {
        return GuestDAO.validGuest(userName);
    }

    public boolean containsUNAME(String  u){
        return JogadorDAO.containsUsername(u);
    }

    public HashMap<String,Admin> getAdmins() throws SQLException {
        this.admins=AdminDAO.getAdminsDB();
        return new HashMap<>(this.admins);
    }

    public HashMap<String,Jogador> getPlayers() throws SQLException {
        this.jogadores=JogadorDAO.getJogadoresDB();
        return new HashMap<>(this.jogadores);
    }

    public boolean existeJogador(String key) throws SQLException {
        return JogadorDAO.existsKey(key);
    }

    public Jogador getJogadorByUname(String username) throws SQLException {
        if (existeJogador(username)) {
            return JogadorDAO.getJogador(username);
        }
        return new AuthenticatedPlayer("","","","");
    }

    public Jogador getJogadorAG(String cod) throws SQLException {
            return JogadorDAO.getJogadorAG(cod);
    }
    public void setpontosDB(String cod,int pts) throws SQLException {
        if (this.existeJogador(cod)) {
             JogadorDAO.addPontos(cod, pts);
        }
    }

    @Override
    public int getPontosGlob(String codJogador) throws SQLException {

        if (this.existeJogador(codJogador)) {
           return  JogadorDAO.getpontosGlob(codJogador);
        }
        return -1;
    }
    public int sizejog(){
        return JogadorDAO.size();
    }

}
