package circuito;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Circuito {

    private String codCircuito;
    private int nvoltas;
    private String nomeCircuito;
    private String localizacao;
    private List<Integer> retasGDU;
    private List<Integer> curvasGDU;
    private List<Integer> chicanesGDU;
    private float distancia;

    //o drs diz-nos em que reta pode ser usado , ou caso seja -1 , não pode ser usado
    private int DRS;
    private CondicoesAtmosfericas condicoesATM;

    public Circuito() {
        this.nvoltas=0;
        this.nomeCircuito="";
        this.localizacao="";
        this.distancia=0;
        this.DRS = -1;
        this.codCircuito=new String("");
        this.condicoesATM = new CondicoesAtmosfericas();
        this.retasGDU= new ArrayList<Integer>();
        this.curvasGDU= new ArrayList<Integer>();
        this.chicanesGDU= new ArrayList<Integer>();
    }
    public Circuito(String codCicruito,int nvoltas,String nomeCircuito,String localizacao,float distancia,int DRS,List<Integer>curvas ,List<Integer>retas,List<Integer>chicanes ){
        this.nvoltas=nvoltas;
        this.nomeCircuito=nomeCircuito;
        this.localizacao=localizacao;
        this.distancia=distancia;
        this.DRS= DRS;
        this.codCircuito=new String(codCicruito);
        this.condicoesATM = new CondicoesAtmosfericas();
        this.retasGDU= retas;
        this.curvasGDU= curvas;
        this.chicanesGDU= chicanes;
    }
    public Circuito(Circuito c){
        this.nvoltas=c.getNvoltas();
        this.nomeCircuito=c.getNomeCircuito();
        this.localizacao=c.getLocalizacao();
        this.distancia=c.getDistancia();
        this.codCircuito=new String(c.getCodCircuito());
        this.condicoesATM= new CondicoesAtmosfericas(c.getCondicoesATM());
        this.DRS=c.getDRS();
        this.retasGDU= c.getRetasGDU();
        this.curvasGDU= c.getCurvasGDU();
        this.chicanesGDU= c.getChicanesGDU();
    }

    public List<Integer> getRetasGDU() {
        return this.retasGDU;
    }

    public void setRetasGDU(List<Integer> retasGDU) {
        this.retasGDU = retasGDU;
    }

    public List<Integer> getCurvasGDU() {
        return this.curvasGDU;
    }

    public void setCurvasGDU(List<Integer> curvasGDU) {
        this.curvasGDU = curvasGDU;
    }

    public List<Integer> getChicanesGDU() {
        return this.chicanesGDU;
    }

    public void setChicanesGDU(List<Integer> chicanesGDU) {
        this.chicanesGDU = chicanesGDU;
    }

    public String getCodCircuito() {
        return this.codCircuito;
    }
    public int getCodCircuitoInt() {

        return Integer.parseInt(this.codCircuito);
    }

    public int getDRS() {
        return DRS;
    }

    public void setDRS(int DRS) {
        this.DRS = DRS;
    }

    public void setCodCircuito(String codCircuito) {
        this.codCircuito = codCircuito;
    }

    public int getNvoltas() {
        return this.nvoltas;
    }

    public void setNvoltas(int nvoltas) {
        this.nvoltas = nvoltas;
    }

    public String getNomeCircuito() {
        return this.nomeCircuito;
    }

    public void setNomeCircuito(String nomeCircuito) {
        this.nomeCircuito = nomeCircuito;
    }

    public String getLocalizacao() {
        return this.localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public float getDistancia() {
        return this.distancia;
    }

    public CondicoesAtmosfericas getCondicoesATM() {
        return this.condicoesATM;
    }

    public void setCondicoesATM(CondicoesAtmosfericas condicoesATM) {
        this.condicoesATM = condicoesATM.clone();
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

/*
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Circuito ciruito)) return false;
        return nvoltas == ciruito.nvoltas && Float.compare(ciruito.distancia, distancia) == 0 && DRS == ciruito.DRS && Objects.equals(codCircuito, ciruito.codCircuito) && Objects.equals(nomeCircuito, ciruito.nomeCircuito) && Objects.equals(localizacao, ciruito.localizacao) && Objects.equals(retasGDU, ciruito.retasGDU) && Objects.equals(curvasGDU, ciruito.curvasGDU) && Objects.equals(chicanesGDU, ciruito.chicanesGDU) && Objects.equals(condicoesATM, ciruito.condicoesATM);
    }
*/
    public Circuito clone(){
        return  new Circuito(this);
    }

    public String toString() {
        return "Ciruito{" +
                "codCircuito='" + codCircuito + '\'' +
                ", nvoltas=" + nvoltas +
                ", nomeCircuito='" + nomeCircuito + '\'' +
                ", localizacao='" + localizacao + '\'' +
                ", retasGDU=" + retasGDU +
                ", curvasGDU=" + curvasGDU +
                ", chicanesGDU=" + chicanesGDU +
                ", distancia=" + distancia +
                ", DRS=" + DRS +
                ", condicoesATM=" + this.condicoesATM.toString() +"\n";
    }
}
