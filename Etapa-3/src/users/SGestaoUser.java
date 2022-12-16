package users;

public interface SGestaoUser {

    public abstract boolean createAPlayer(String nome, String username, String password) throws CloneNotSupportedException;
    public abstract boolean createAdmin(String nome, String ctcto, String email, String username, String password);
    public abstract  boolean createGuest(String nome);
    public abstract boolean addPontosCorr(String codJogador, int numPts);
    public abstract boolean addPontosGlob(int pontos, String codJogador);

    public abstract int getPontosGlob(String codJogador);
    public abstract Jogador getJogador(String codJogador);
}
