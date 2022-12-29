package campeonato;

import carro.Carro;
import piloto.Piloto;
import users.Jogador;

/**
 * Classe responsável armazenar a informação sobre um registo e operar sobre a sua informação
 */
public class Registo {

    private Jogador jogador;
    private Carro carro;
    private Piloto piloto;
    private int nrAfinacoes;

    /**
     * Contrutor da classe que inicializa as estruturas de dados que contém a informação de um registo
     */
    public Registo(){
        this.jogador = null;
        this.carro = null;
        this.piloto = null;
        this.nrAfinacoes = 0;
    }

    /**
     * Contrutor da classe que inicializa as estruturas de dados que contém a informação de um registo
     * @param jogador Jogador
     * @param carro Carro associado ao Jogador
     * @param piloto Piloto associado ao Jogador
     */
    public Registo(Jogador jogador, Carro carro, Piloto piloto){
        this.jogador = jogador;
        this.carro = carro;
        this.piloto = piloto;
        this.nrAfinacoes = 0;
    }

    /**
     * @return Retorna Jogador
     */
    public Jogador getJogador() {
        return this.jogador;
    }

    /**
     * Atualiza o Jogador
     * @param jogador Jogador novo
     */
    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    /**
     * @return Retorna o Carro
     */
    public Carro getCarro() {
        return this.carro;
    }

    /**
     * Atualiza o Carro
     * @param carro Carro novo
     */
    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    /**
     * @return Retorna o Piloto
     */
    public Piloto getPiloto() {
        return this.piloto;
    }

    /**
     * Atualiza o Piloto
     * @param piloto Piloto novo
     */
    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    /**
     * @return Retorna o número de afinações
     */
    public int getNrAfinacoes() {
        return this.nrAfinacoes;
    }

    /**
     * Atualiza o número de afinações
     * @param nrAfinacoes Número de afinações nova
     */
    public void setNrAfinacoes(int nrAfinacoes) {
        this.nrAfinacoes = nrAfinacoes;
    }

    @Override
    public String toString() {
        return "Registo{" +
                "jogador=" + jogador +
                ", carro=" + carro +
                ", piloto=" + piloto +
                ", nrAfinacoes=" + nrAfinacoes +
                '}';
    }
}
