package users;

/**
 * Classe responsável armazenar a informação sobre um jogador e operar sobre a informação
 */
public class Jogador {

    private String nome;
    private int pontosCorr;
    private int pontosglobais;
    private String codJogador;

    /**
     * Contrutor da classe jogador que recebe os seus atributos como input
     * @param username nome do jogador
     * @param codJogador código identificador do jogador
     */
    public Jogador(String username, String codJogador) {

        this.nome = new String(username);
        this.pontosCorr = 0;
        this.pontosglobais = 0;
        this.codJogador = new String(codJogador);
    }

    /**
     * Contrutor da classe jogador que recebe um objeto da classe jogador
     * @param j outro jogador
     */
    public Jogador(Jogador j) {

        this.nome = new String(j.nome);
        this.pontosCorr = j.pontosCorr;
        this.pontosglobais = j.pontosglobais;
        this.codJogador = new String(j.codJogador);
    }

    /**
     * Adiciona pontos globais ao jogador
     * @param pontos pontos a atribuir
     */
    public void addPontosGlob(int pontos) {

        this.pontosglobais += pontos;
    }

    /**
     * Adiciona pontos de corrida ao jogador
     * @param pontos pontos a atribuir
     */
    public void addPontosCorr(int pontos) {

        this.pontosCorr += pontos;
    }

    /**
     * @return Valor dos pontos globais
     */
    public int getPontosGlob() {

        return this.pontosglobais;
    }
}
