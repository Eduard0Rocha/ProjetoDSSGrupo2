package users;

/**
 * Classe que extende a classe jogador para Guests
 */
public class Guest extends Jogador {

    private String idGuest;

    /**
     * Contrutor da classe
     * @param username username do guest
     * @param pontosglobais pontos do guest
     * @param codJogador código de jogador do guest
     * @param idGuest código de guest a ser atribuido
     */
    public Guest(String username, int pontosglobais, String codJogador, String idGuest) {

        super(username, pontosglobais, codJogador);

        this.idGuest = new String(idGuest);
    }
}
