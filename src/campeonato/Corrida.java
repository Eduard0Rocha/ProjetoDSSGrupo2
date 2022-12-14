package campeonato;

import circuito.Circuito;

import java.util.ArrayList;
import java.util.HashMap;

public class Corrida {

    private String codCorr;
    private String codCamp;
    private String codCirc;
    private HashMap<String, Float> tempos;
    private ArrayList<String> classificacao;
    private Circuito circuito;

    public Corrida(){
        this.codCorr = null;
        this.codCamp = null;
        this.codCirc = null;
        this.tempos = new HashMap<String, Float>();
        this.classificacao = new ArrayList<String>();
        this.circuito = new Circuito();
    }

    public Corrida(String codCorr, String codCamp, String codCirc, Circuito circuito){
        this.codCorr = codCorr;
        this.codCamp = codCamp;
        this.codCirc = codCirc;
        this.tempos = new HashMap<String, Float>();
        this.classificacao = new ArrayList<String>();
        this.circuito = circuito;
    }

    public String getCodCorr() {
        return this.codCorr;
    }

    public void setCodCorr(String codCorr) {
        this.codCorr = codCorr;
    }

    public String getCodCamp() {
        return this.codCamp;
    }

    public void setCodCamp(String codCamp) {
        this.codCamp = codCamp;
    }

    public String getCodCirc() {
        return this.codCirc;
    }

    public void setCodCirc(String codCirc) {
        this.codCirc = codCirc;
    }

    public HashMap<String, Float> getTempos() {
        return this.tempos;
    }

    public void setTempos(HashMap<String, Float> tempos) {
        this.tempos = tempos;
    }

    public ArrayList<String> getClassificacao() {
        return this.classificacao;
    }

    public void setClassificacao(ArrayList<String> classificacao) {
        this.classificacao = classificacao;
    }

    public Circuito getCircuito() {
        return this.circuito;
    }

    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
    }
}
