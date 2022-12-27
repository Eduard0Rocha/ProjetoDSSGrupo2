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


    public AuthenticatedPlayer(String username, String codJogador, String cred_username, String cred_password,int pontoscorr,int pontosGlob) {

        super(username, codJogador,pontoscorr,pontosGlob);

        this.credenciais = new Credenciais(cred_username, cred_password);
    }
    public AuthenticatedPlayer(AuthenticatedPlayer a) {

        super(a);

        this.credenciais = new Credenciais(a.getCredenciais().getUsername(), a.getCredenciais().getPassword());
    }


    @Override
    public int getPontosGlob() {
        return super.getPontosGlob();
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public int getPontosCorr() {
        return super.getPontosCorr();
    }

    @Override
    public String getCodJogador() {
        return super.getCodJogador();
    }

    public String getUsername() {
        return credenciais.getUsername();
    }

    public String getPassword() {
        return credenciais.getPassword();
    }

    public Credenciais getCredenciais() {
        return credenciais.clone();
    }

    public AuthenticatedPlayer clone (){
        return new AuthenticatedPlayer(this);
    }

    @Override
    public String toString() {
        return super.toString() +
                "credenciais=" + credenciais.toString() ;
    }
}
