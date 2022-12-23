package users;

/**
 * Classe que extende a classe jogador para Guests
 */
public class Guest extends Jogador {

    private String idGuest;

    /**
     * Contrutor da classe
     * @param username username do guest
     * @param codJogador código de jogador do guest
     * @param idGuest código de guest a ser atribuido
     */
    public Guest(String username, String codJogador, String idGuest) {

        super(username, codJogador);

        this.idGuest = new String(idGuest);
    }

    @Override
    public String getCodJogador() {
        return super.getCodJogador();
    }

    @Override
    public int getPontosGlob() {
        return super.getPontosGlob();
    }

    @Override
    public int getPontosCorr() {
        return super.getPontosCorr();
    }

    public String getIdGuest() {
        return idGuest;
    }

    @Override
    public String getNome() {
        return super.getNome();
    }
}
