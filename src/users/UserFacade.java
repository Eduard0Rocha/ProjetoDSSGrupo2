package users;

import java.util.HashMap;

/**
 * Classe responsável pela interação com a informação relativa aos usuários
 */
public class UserFacade implements SGestaoUser {

    private HashMap<String,Admin> admins;
    private HashMap<String,Jogador> jogadores;

    private int adminCounter;
    private int jogadorCounter;
    private int guestCounter;

    /**
     * Contrutor da classe que inicializa as estruturas de dados que contém a informação dos usuários
     */
    public UserFacade() {

        this.admins = new HashMap<String,Admin>();
        this.jogadores = new HashMap<String, Jogador>();

        this.adminCounter = 0;
        this.jogadorCounter = 0;
        this.guestCounter = 0;
    }

    /**
     * Método que adiciona um novo jogador autenticado
     * @param nome nome do jogador
     * @param username username do jogador
     * @param password password do jogador
     * @return true se foi bem sucedido, false caso contrário
     */
    @Override
    public boolean createAPlayer(String nome, String username, String password) {

        if (nome == null || username == null || password == null) return false;

        String codJogador = new String("P" + Integer.toString(jogadorCounter));

        this.jogadorCounter++;

        this.jogadores.put(codJogador,new AuthenticatedPlayer(nome, codJogador, username, password));

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

        String codAdmin = new String("A" + Integer.toString(this.adminCounter));

        this.adminCounter++;

        this.admins.put(codAdmin, new Admin(nome, ctcto, email, codAdmin, username, password));

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

        String codJogador = new String("P" + Integer.toString(jogadorCounter));

        this.jogadorCounter++;

        String idGuest = new String("G" + Integer.toString(this.guestCounter));

        this.guestCounter++;

        this.jogadores.put(codJogador,new Guest(nome, codJogador, idGuest));

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
    @Override
    public int getPontosGlob(String codJogador) {

        if (codJogador == null) return -1;

        Jogador j = this.jogadores.get(codJogador);

        return (j != null) ? j.getPontosGlob() : -1;
    }

    /**
     * @param codJogador código identificador do jogador
     * @return jogador identificado pelo código de input
     */
    @Override
    public Jogador getJogador(String codJogador) {

        return this.jogadores.get(codJogador);
    }
}
