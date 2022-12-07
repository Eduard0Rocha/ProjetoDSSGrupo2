package users;

/**
 * Classe que extende a classe jogador para jogadores autenticados
 */
public class AuthenticatedPlayer extends Jogador {

    private Credenciais credenciais;

    /**
     * Contrutor da classe
     * @param username nome do jogador
     * @param codJogador código identificador do jogador
     * @param cred_username username nas credenciais do jogador
     * @param cred_password password nas credenciais do jogador
     */
    public AuthenticatedPlayer(String username, String codJogador, String cred_username, String cred_password) {

        super(username, codJogador);

        this.credenciais = new Credenciais(cred_username, cred_password);
    }
}
