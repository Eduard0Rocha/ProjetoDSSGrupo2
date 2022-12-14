package campeonato;

import circuito.Circuito;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe responsável armazenar a informação sobre uma corrida e operar sobre a sua informação
 */
public class Corrida {

    private String codCorr;
    private String codCamp;
    private String codCirc;
    private HashMap<String, Float> tempos;
    private ArrayList<String> classificacao;
    private Circuito circuito;

    /**
     * Contrutor da classe que inicializa as estruturas de dados que contém a informação de uma corrida
     */
    public Corrida(){
        this.codCorr = null;
        this.codCamp = null;
        this.codCirc = null;
        this.tempos = new HashMap<String, Float>();
        this.classificacao = new ArrayList<String>();
        this.circuito = new Circuito();
    }

    /**
     * Contrutor da classe que inicializa as estruturas de dados que contém a informação de uma corrida
     * @param codCorr código identificador da corrida
     * @param codCamp código identificador do campeonato
     * @param codCirc código identificador do circuito
     * @param circuito Circuito associado ao código identificador do circuito
     */
    public Corrida(String codCorr, String codCamp, String codCirc, Circuito circuito){
        this.codCorr = codCorr;
        this.codCamp = codCamp;
        this.codCirc = codCirc;
        this.tempos = new HashMap<String, Float>();
        this.classificacao = new ArrayList<String>();
        this.circuito = circuito;
    }

    /**
     * @return Retorna o código identificador da corrida
     */
    public String getCodCorr() {
        return this.codCorr;
    }

    /**
     * Atualiza o código identificador da corrida
     * @param codCorr Novo código identificador da corrida
     */
    public void setCodCorr(String codCorr) {
        this.codCorr = codCorr;
    }

    /**
     * @return Retorna o código do campeonato
     */
    public String getCodCamp() {
        return this.codCamp;
    }

    /**
     * Atualiza o código identificador do campeonato
     * @param codCamp Novo código identificador do campeonato
     */
    public void setCodCamp(String codCamp) {
        this.codCamp = codCamp;
    }

    /**
     * @return Retorna o código do circuito
     */
    public String getCodCirc() {
        return this.codCirc;
    }

    /**
     * Atualiza o código identificador do circuito
     * @param codCirc Novo código identificador do circuito
     */
    public void setCodCirc(String codCirc) {
        this.codCirc = codCirc;
    }

    /**
     * @return Retorna os tempos da corrida
     */
    public HashMap<String, Float> getTempos() {
        return this.tempos;
    }

    /**
     * Atualiza os tempos
     * @param tempos tempos novos
     */
    public void setTempos(HashMap<String, Float> tempos) {
        this.tempos = tempos;
    }

    /**
     * @return Retorna a lista de classsificações
     */
    public ArrayList<String> getClassificacao() {
        return this.classificacao;
    }

    /**
     * Atualiza as classificações
     * @param classificacao Classificações novas
     */
    public void setClassificacao(ArrayList<String> classificacao) {
        this.classificacao = classificacao;
    }

    /**
     * @return Retorna o circuito associado à corrida
     */
    public Circuito getCircuito() {
        return this.circuito;
    }

    /**
     * Atualiza o circuito associado à corrida
     * @param circuito Novo circuito
     */
    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
    }
}
