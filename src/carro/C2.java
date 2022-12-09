package carro;

import java.util.Random;

public class C2 extends Carro {

    private int afinacao_mecanica;
    private boolean isValid;

    public C2(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, int PAC, String codCarro, int a_mecanica) {

        super(marca, modelo, cilindrada, potencia, fiabilidade, PAC, codCarro);

        this.afinacao_mecanica = a_mecanica;
        this.isValid = (cilindrada >= 3000 && cilindrada <= 5000);
    }

    @Override
    public boolean DNF(int volta, int totalVoltas, int clima) {

        Random rand=new Random();

        int x=rand.nextInt(85);

        int fiabilidade = super.getFiabilidade() + (super.getCilindrada()/1200) + (this.afinacao_mecanica/10);

        return (x > fiabilidade);
    }

    @Override
    public void setFiabilidade(int volta, int clima) {

        // TODO
    }
}
