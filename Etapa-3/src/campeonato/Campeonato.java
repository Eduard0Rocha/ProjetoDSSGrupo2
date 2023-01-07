package campeonato;

import java.util.ArrayList;
import java.util.HashMap;
import Data.*;

/**
 * Classe responsável armazenar a informação sobre um campeonato e operar sobre a sua informação
 */
public class Campeonato {

    private String nomeCamp;
    private String codCamp;
    private HashMap<String, Integer> classificacao;
    private HashMap<String, Integer> classificacaoH;
    private ArrayList<Registo> registo;
    private HashMap<String, Corrida> corridas;

    private int simulated;

    @Override
    public String toString() {
        return "Campeonato{" +
                "nomeCamp='" + nomeCamp + '\'' +
                ", codCamp='" + codCamp + '\'' +
                ", classificacao=" + classificacao +
                ", classificacaoH=" + classificacaoH +
                ", registo=" + registo +
                ", corridas=" + corridas +
                ", corridas=" + corridas +
                '\n';
    }

    /**
     * Contrutor da classe que inicializa as estruturas de dados que contém a informação de um campeonato
     */
    public Campeonato() {
        this.nomeCamp = null;
        this.codCamp = null;
        this.classificacao = new HashMap<String, Integer>();
        this.classificacaoH = new HashMap<String, Integer>();
        this.registo = new ArrayList<Registo>();
        this.corridas = new HashMap<String, Corrida>();
        this.simulated=0;
    }

    /**
     * Contrutor da classe que inicializa as estruturas de dados que contém a informação de um campeonato
     * @param nomeCamp nome do campeonato
     * @param codCamp código identificador do campeonato
     */
    public Campeonato(String nomeCamp, String codCamp){
        this.nomeCamp = nomeCamp;
        this.codCamp = codCamp;
        this.classificacao = new HashMap<String, Integer>();
        this.classificacaoH = new HashMap<String, Integer>();
        this.registo = new ArrayList<Registo>();
        this.corridas = new HashMap<String, Corrida>();
        this.simulated=0;
    }

    /**
     * Contrutor da classe que inicializa as estruturas de dados que contém a informação de um campeonato
     * @param nomeCamp nome do campeonato
     * @param codCamp código identificador do campeonato
     * @param classificacao classificações
     * @param classificacaoH classificações dos hibridos
     * @param registo Lista de registos
     * @param corridas HashMap de Corridas
     */
    public Campeonato(String nomeCamp, String codCamp, HashMap<String, Integer> classificacao, HashMap<String, Integer> classificacaoH, ArrayList<Registo> registo, HashMap<String, Corrida> corridas,int simulated){
        this.nomeCamp = nomeCamp;
        this.codCamp = codCamp;
        this.classificacao = classificacao;
        this.classificacaoH = classificacaoH;
        this.registo = registo;
        this.corridas = corridas;
        this.simulated=simulated;
    }

    /**
     * Contrutor da classe que inicializa as estruturas de dados que contém a informação de um campeonato
     * @param c Campeonato
     */
    public Campeonato(Campeonato c) {
        this.nomeCamp = c.getNomeCamp();
        this.codCamp = c.getCodCamp();
        this.classificacao = c.getClassificacao();
        this.classificacaoH = c.getClassificacaoH();
        this.registo = c.getRegisto();
        this.corridas = c.getCorridas();
        this.simulated=c.getSimulated();
    }

    public int getSimulated() {
        return simulated;
    }

    public void setSimulated(int simulated) {
        this.simulated = simulated;
    }

    /**
     * @return Retorna o nome do campeonato
     */
    public String getNomeCamp() {
        return this.nomeCamp;
    }

    /**
     * Atualiza o nome do campeonato
     * @param nomeCamp nome do campeonato
     */
    public void setNomeCamp(String nomeCamp) {
        this.nomeCamp = nomeCamp;
    }

    /**
     * @return Retorna o código identificador do campeonato
     */
    public String getCodCamp() {
        return this.codCamp;
    }

    /**
     * Atualiza o código do campeonato
     * @param codCamp código do campeonato
     */
    public void setCodCamp(String codCamp) {
        this.codCamp = codCamp;
    }

    /**
     * @return Retorna as classificações
     */
    public HashMap<String, Integer> getClassificacao() {
        return  new HashMap<>(this.classificacao);
    }

    /**
     * Atualiza as classsificações
     * @param classificacao classificações
     */
    public void setClassificacao(HashMap<String, Integer> classificacao) {
        this.classificacao = classificacao;
    }

    /**
     * @return Retorna as classificações dos hibridos
     */
    public HashMap<String, Integer> getClassificacaoH() {
        return new HashMap<>(this.classificacaoH) ;
    }

    /**
     * Atualiza as classificações dos hibridos
     * @param classificacaoH classificações dos hibridos
     */
    public void setClassificacaoH(HashMap<String, Integer> classificacaoH) {
        this.classificacaoH = classificacaoH;
    }

    /**
     * @return Retorna a lista dos registos
     */
    public ArrayList<Registo> getRegisto() {
        return  new ArrayList<>(this.registo);
    }

    /**
     * Atualiza a lista dos Registos
     * @param registo Lista de registos
     */
    public void setRegisto(ArrayList<Registo> registo) {
        this.registo = registo;
    }

    /**
     * @return Retorna as corridas
     */
    public HashMap<String, Corrida> getCorridas() {
        return this.corridas;
    }

    /**
     * Atualiza as corridas existentes
     * @param corridas HashMap de Corridas
     */
    public void setCorridas(HashMap<String, Corrida> corridas) {
        this.corridas = corridas;
    }

    /**
     * Adiciona uma corrida ao HashMap de corridas
     * @param codCorr código identificador da corrida que se pretende adicionar
     * @param corrida corrida que se pretende adicionar
     */
    public void addCorrida(String codCorr, Corrida corrida){
        this.corridas.put(codCorr, corrida);
    }

    /**
     * Adiciona um registo novo
     * @param registo registo novo que se pretende adicionar
     */
    public void addRegisto(Registo registo){
        this.registo.add(registo);
    }

    public Campeonato clone(){
        return new Campeonato(this);
    }
}
