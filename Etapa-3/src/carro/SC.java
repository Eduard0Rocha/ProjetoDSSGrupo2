package carro;

import java.util.Random;

public class SC extends Carro {

    private float piloto_cts;
    private float piloto_sva;
    private boolean isValid;

    public SC(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String codCarro, String tipo_de_pneus) {

        super(marca, modelo, cilindrada, potencia, fiabilidade, PAC, codCarro, tipo_de_pneus);

        this.piloto_cts = 0.5F;
        this.piloto_sva = 0.5F;
        this.isValid = (cilindrada == 6000);
    }

    public void setPiloto_cts(float piloto_cts) {
        this.piloto_cts = piloto_cts;
    }

    public void setPiloto_sva(float piloto_sva) {
        this.piloto_sva = piloto_sva;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public SC(SC a){
        super(a);
        this.isValid=a.getISvalid();
        this.piloto_cts=a.getPiloto_cts();
        this.piloto_sva=a.getPiloto_sva();
    }

    public void setPiloto_cts(int cts) {

        this.piloto_cts = cts;
    }

    public float getPiloto_cts() {
        return piloto_cts;
    }

    public float getPiloto_sva() {
        return piloto_sva;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setPiloto_sva(int sva) {

        this.piloto_sva = sva;
    }


    public boolean DNF(int volta, int totalVoltas, int clima) {

        Random rand=new Random();

        int x=rand.nextInt(73);

        float qualidade;

        if(clima == 1)
            qualidade = this.piloto_cts;
        else
            qualidade = this.piloto_sva;

        int fiabilidade = (int)(qualidade*0.75) + (int)((super.getCilindrada()/10)*0.25);

        return (x > fiabilidade);
    }

    public  boolean getISvalid(){
       return this.isValid;
    }

    public SC clone(){
        return new SC(this);
    }

    @Override
    public String toString() {
        return  super.toString() +
                "piloto_cts=" + piloto_cts +
                ", piloto_sva=" + piloto_sva +'\n';
    }
}
