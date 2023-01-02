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


    /**
     * Contrutor da classe que inicializa as estruturas de dados que contém a informação de uma corrida
     */
    public Corrida(){
        this.codCorr = null;
        this.codCamp = null;
        this.codCirc = null;
        this.tempos = new HashMap<String, Float>();
        this.classificacao = new ArrayList<String>();

    }

    /**
     * Contrutor da classe que inicializa as estruturas de dados que contém a informação de uma corrida
     * @param codCorr código identificador da corrida
     * @param codCamp código identificador do campeonato
     * @param codCirc código identificador do circuito
     */
    public Corrida(String codCorr, String codCamp, String codCirc){
        this.codCorr = codCorr;
        this.codCamp = codCamp;
        this.codCirc = codCirc;
        this.tempos = new HashMap<String, Float>();
        this.classificacao = new ArrayList<String>();
    }


    public Corrida (Corrida c){
        this.codCorr = c.getCodCorr();
        this.codCamp = c.getCodCamp();
        this.codCirc = c.getCodCirc();
        this.tempos = c.getTempos();
        this.classificacao = c.getClassificacao();
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

    public Corrida clone(){
        return new Corrida(this);
    }



    @Override
    public String toString() {
        return "Corrida : " +
                "codCorr='" + codCorr + '\'' +
                ", codCamp='" + codCamp + '\'' +
                ", codCirc='" + codCirc + '\'' +
                ", tempos=" + tempos +
                ", classificacao=" + classificacao.toString() ;
    }
}
