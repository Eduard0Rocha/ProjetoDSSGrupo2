package piloto;

import users.Jogador;

/**
 * Classe responsável por armazenar e operar sobre a informação do piloto
 */
public class Piloto {

    private String nome;
    private float cts;
    private float sva;
    private String codPiloto;

    /**
     * Contrutor da classe Piloto por omissão de argumentos
     */
    public Piloto() {

        this.nome = null;
        this.cts = -1;
        this.sva = -1;
        this.codPiloto = null;
    }

    /**
     * Contrutor da classe Piloto que recebe todos os seus atributos como input
     * @param nome nome do Piloto
     * @param cts avaliação cts do Piloto
     * @param sva avaliação sva do Piloto
     * @param codPiloto código identificador do piloto
     */
    public Piloto(String nome, float cts, float sva, String codPiloto) {

        this.nome = new String(nome);
        this.cts = cts;
        this.sva = sva;
        this.codPiloto = new String(codPiloto);
    }

    /**
     * Contrutor da classe Piloto que clona o piloto fornecido como argumento
     * @param p objeto da classe piloto
     */
    public Piloto(Piloto p) {

        this.nome = new String(p.nome);
        this.cts = p.cts;
        this.sva = p.sva;
        this.codPiloto = new String(p.codPiloto);
    }

    public String getNome() {

        return new String(this.nome);
    }

    public float getCTS() {

        return this.cts;
    }

    public float getSVA() {

        return this.sva;
    }

    public void setNome(String nome) {

        this.nome = new String(nome);
    }

    public String getCodPiloto() {
        return codPiloto;
    }

    public void setCodPiloto(String codPiloto) {
        this.codPiloto = codPiloto;
    }

    public void setCTS(int cts) {

        this.cts = cts;
    }

    public void setSVA(int sva) {

        this.sva = sva;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder("Piloto ");

        sb.append(new String(this.codPiloto));
        sb.append("\nNome: ");
        sb.append(new String(this.nome));
        sb.append("\nCTS: ");
        sb.append(new String(Float.toString(this.cts)));
        sb.append("\nSVA: ");
        sb.append(new String(Float.toString(this.sva)));

        return sb.toString();
    }

    public Piloto clone() {

        return new Piloto(this);
    }
}
