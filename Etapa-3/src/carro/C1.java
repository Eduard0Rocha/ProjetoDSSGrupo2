package carro;

import java.util.Random;

public class C1 extends Carro
{

    private boolean isValid;

    public C1(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String codCarro, String tipo_de_pneus) {

        super(marca, modelo, cilindrada, potencia, fiabilidade, PAC, codCarro, tipo_de_pneus);

        this.isValid = (cilindrada == 6000);
    }

    public C1(C1 a) {
        super(a);
        this.isValid = a.isValid();
    }

    public boolean isValid() {
        return isValid;
    }

    public boolean DNF(int volta, int totalVoltas, int clima) {

        Random rand = new Random();

        int x = rand.nextInt(87);

        return (x > super.getFiabilidade());
    }


public C1 clone(){
        return  new C1(this);
}


    @Override
    public String toString() {
        String k=super.toString();
        return k +'\n';
    }
}
