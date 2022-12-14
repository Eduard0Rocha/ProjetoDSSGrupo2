package campeonato;

import java.util.ArrayList;
import java.util.HashMap;

public class Campeonato {
    private String nomeCamp;
    private String codCamp;
    private HashMap<String, Integer> classificacao;
    private HashMap<String, Integer> classificacaoH;
    private ArrayList<Registo> registo;
    private HashMap<String, Corrida> corridas;

    public Campeonato() {
        this.nomeCamp = null;
        this.codCamp = null;
        this.classificacao = new HashMap<String, Integer>();
        this.classificacaoH = new HashMap<String, Integer>();
        this.registo = new ArrayList<Registo>();
        this.corridas = new HashMap<String, Corrida>();
    }

    public Campeonato(String nomeCamp, String codCamp){
        this.nomeCamp = nomeCamp;
        this.codCamp = codCamp;
        this.classificacao = new HashMap<String, Integer>();
        this.classificacaoH = new HashMap<String, Integer>();
        this.registo = new ArrayList<Registo>();
        this.corridas = new HashMap<String, Corrida>();
    }

    public Campeonato(Campeonato c) {
        this.nomeCamp = c.getNomeCamp();
        this.codCamp = c.getCodCamp();
        this.classificacao = c.getClassificacao();
        this.classificacaoH = c.getClassificacaoH();
        this.registo = c.getRegisto();
        this.corridas = c.getCorridas();
    }

    public String getNomeCamp() {
        return this.nomeCamp;
    }

    public void setNomeCamp(String nomeCamp) {
        this.nomeCamp = nomeCamp;
    }

    public String getCodCamp() {
        return this.codCamp;
    }

    public void setCodCamp(String codCamp) {
        this.codCamp = codCamp;
    }

    public HashMap<String, Integer> getClassificacao() {
        return this.classificacao;
    }

    public void setClassificacao(HashMap<String, Integer> classificacao) {
        this.classificacao = classificacao;
    }

    public HashMap<String, Integer> getClassificacaoH() {
        return this.classificacaoH;
    }

    public void setClassificacaoH(HashMap<String, Integer> classificacaoH) {
        this.classificacaoH = classificacaoH;
    }

    public ArrayList<Registo> getRegisto() {
        return this.registo;
    }

    public void setRegisto(ArrayList<Registo> registo) {
        this.registo = registo;
    }

    public HashMap<String, Corrida> getCorridas() {
        return this.corridas;
    }

    public void setCorridas(HashMap<String, Corrida> corridas) {
        this.corridas = corridas;
    }
}
