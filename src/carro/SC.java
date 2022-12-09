package carro;

import java.util.Random;

public class SC extends Carro {

    private boolean isValid;

    public SC(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, int PAC, String codCarro) {

        super(marca, modelo, cilindrada, potencia, fiabilidade, PAC, codCarro);

        this.isValid = (cilindrada == 6000);
    }

    @Override
    public boolean DNF(int volta, int totalVoltas, int clima) {

        // TODO
        /*
        Random rand=new Random();
        int x=rand.nextInt(73);
        Piloto p = null;
        if(volta<totalvoltas/2)
            p = super.getEquipa().getPiloto1();
        else
            p = super.getEquipa().getPiloto2();
        int qualidade;
        if(chuva == 1)
            qualidade = p.getQualidadeChuva();
        else
            qualidade = p.getQualidade();
        //no maximo fiabilidade de 70%
        int fiabilidade = (int)(qualidade*0.75) + (int)((super.getCilindrada()/10)*0.25);
        //System.out.println("Fiabilidade: "+fiabilidade);
        //System.out.println("Random: "+x);
        return (x > fiabilidade);*/

        return false;
    }

    @Override
    public void setFiabilidade(int volta, int clima) {

        // TODO
    }
}
