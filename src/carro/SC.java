package carro;

import java.util.Random;

public class SC extends Carro {

    private float piloto_cts;
    private float piloto_sva;
    private boolean isValid;

    public SC(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, int PAC, String codCarro) {

        super(marca, modelo, cilindrada, potencia, fiabilidade, PAC, codCarro);

        this.piloto_cts = 0.5F;
        this.piloto_sva = 0.5F;
        this.isValid = (cilindrada == 6000);
    }

    public void setPiloto_cts(int cts) {

        this.piloto_cts = cts;
    }

    public void setPiloto_sva(int sva) {

        this.piloto_sva = sva;
    }

    @Override
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

    @Override
    public void setFiabilidade(int volta, int clima) {

        // TODO
    }
}
