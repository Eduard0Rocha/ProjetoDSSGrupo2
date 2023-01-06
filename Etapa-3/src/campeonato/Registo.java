package campeonato;

import carro.Carro;
import piloto.Piloto;
import users.Jogador;

/**
 * Classe responsável armazenar a informação sobre um registo e operar sobre a sua informação
 */
public class Registo {

    private String codRegisto;
    private String codjogador;
    private String codCarro;
    private String codpiloto;
    private int pontos;
    private int nrAfinacoes;

    /**
     * Contrutor da classe que inicializa as estruturas de dados que contém a informação de um registo
     */
    public Registo(){
        this.codRegisto=null;
        this.codpiloto = null;
        this.codRegisto = null;
        this.codjogador = null;
        this.pontos=0;
        this.nrAfinacoes = 0;
    }

    /**
     * Contrutor da classe que inicializa as estruturas de dados que contém a informação de um registo
     * @param jogador Jogador
     * @param carro Carro associado ao Jogador
     * @param piloto Piloto associado ao Jogador
     */
    public Registo(String jogador, String carro, String piloto,String codRegisto,int pontos){
        this.codRegisto=codRegisto;
        this.codjogador = jogador;
        this.codCarro = carro;
        this.codpiloto = piloto;
        this.nrAfinacoes = 0;
        this.pontos=pontos;
    }


    public Registo(Registo r){
        this.codRegisto=r.getCodRegisto();
        this.codjogador = r.getJogador();
        this.codCarro = r.getCarro();
        this.codpiloto = r.getPiloto();
        this.nrAfinacoes = r.getNrAfinacoes();
    }
    /**
     * @return Retorna Jogador
     */
    public String getJogador() {
        return this.codjogador;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    /**
     * Atualiza o Jogador
     * @param jogador Jogador novo
     */
    public void setJogador(String  jogador) {
        this.codjogador = jogador;
    }

    /**
     * @return Retorna o Carro
     */
    public String getCarro() {
        return this.codCarro;
    }

    public String getCodRegisto() {
        return codRegisto;
    }

    public void setCodRegisto(String codRegisto) {
        this.codRegisto = codRegisto;
    }

    /**
     * Atualiza o Carro
     * @param carro Carro novo
     */
    public void setCarro(String carro) {
        this.codCarro = carro;
    }

    /**
     * @return Retorna o Piloto
     */
    public String getPiloto() {
        return this.codpiloto;
    }

    /**
     * Atualiza o Piloto
     * @param piloto Piloto novo
     */
    public void setPiloto(String piloto) {
        this.codpiloto = piloto;
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


    public Registo clone(){
        return new Registo(this);
    }
    @Override
    public String toString() {
        return "Registo{" +
                "jogador=" + codjogador +
                ", carro=" + codCarro +
                ", piloto=" + codpiloto +
                ", nrAfinacoes=" + nrAfinacoes +
                '}';
    }
}
