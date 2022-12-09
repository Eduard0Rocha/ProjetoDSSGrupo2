package carro;

import java.util.Random;

public class C1 extends Carro {

    private boolean isValid;

    public C1(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, int PAC, String codCarro) {

        super(marca, modelo, cilindrada, potencia, fiabilidade, PAC, codCarro);

        this.isValid = (cilindrada == 6000);
    }

    @Override
    public boolean DNF(int volta, int totalVoltas, int clima) {

        Random rand = new Random();

        int x = rand.nextInt(87);

        return (x > super.getFiabilidade());
    }

    @Override
    public void setFiabilidade(int volta, int clima) {

        // TODO
    }
}
