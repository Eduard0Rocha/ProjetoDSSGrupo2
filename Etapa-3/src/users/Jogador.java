package users;

/**
 * Classe responsável armazenar a informação sobre um jogador e operar sobre a informação
 */
public abstract class Jogador {

    private String nome;
    private int pontosCorr;
    private int pontosglobais;
    private String codJogador;
    private String classe;

    /**
     * Construtor da classe jogador que não recebe atributos como input
     */
    public Jogador() {

        this.nome = "";
        this.pontosCorr = 0;
        this.pontosglobais = 0;
        this.codJogador = "";
        this.classe="";
    }

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
        this.classe=this.getclasse();
    }

    public Jogador(String username, String codJogador,int pontosCorr,int postosglob) {

        this.nome = new String(username);
        this.pontosCorr = pontosCorr;
        this.pontosglobais = postosglob;
        this.codJogador = new String(codJogador);
        this.classe=this.getclasse();
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
        this.classe=this.getclasse();
    }

    public String getCodJogador() {
        return this.codJogador;
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

    public int getPontosCorr() {
        return pontosCorr;
    }


    public String getNome() {
        return nome;
    }

    public String getclasse(){

        String cl= this.getClass().toString();
        if (cl.equals("class users.AuthenticatedPlayer")){
            return ("AP");
        }
        if (cl.equals("class users.Guest")){
            return ("G");
        }
        else return "J";

    }

    public int getPontosglobais() {
        return pontosglobais;
    }

    public String getClasse() {
        return classe;
    }


    @Override
    public String toString() {
        return "Jogador{" +
                "nome='" + nome + '\'' +
                ", pontosCorr=" + pontosCorr +
                ", pontosglobais=" + pontosglobais +
                ", codJogador='" + codJogador + '\'' +
                ", classe='" + classe + '\'' ;
    }
}
