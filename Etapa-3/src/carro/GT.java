package carro;

import java.util.Random;

public class GT extends Carro
{

    private boolean isValid;

    public GT(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String codCarro, String tipo_de_pneus) {

        super(marca, modelo, cilindrada, potencia, fiabilidade, PAC, codCarro, tipo_de_pneus);

        this.isValid = (cilindrada >= 2000 && cilindrada <= 4000);
    }
    public GT(GT a){
        super(a);
        this.isValid=a.isValid();
    }

    public boolean isValid() {
        return isValid;
    }

    public boolean DNF(int volta, int totalVoltas, int clima) {

        Random rand=new Random();

        int x=rand.nextInt(70);

        int fiabilidade = (int)((100000/super.getCilindrada())*2.55);

        int desgaste = (int)((volta+1)*0.5);

        return (x > (fiabilidade - desgaste));
    }
    public  int getISvalid(){
        if (this.isValid) return 1;
        return 0;
    }

    public GT clone(){
        return new GT(this);
    }

    @Override
    public String toString() {
        return super.toString()+'\n';
    }
}
