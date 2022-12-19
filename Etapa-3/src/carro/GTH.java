package carro;

import java.util.Random;

public class GTH extends GT implements Hibrido {

    private int potencia_motor_eletrico;

    public GTH(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String codCarro, String tipo_de_pneus, int potencia_motor_eletrico) {

        super(marca, modelo, cilindrada, potencia, fiabilidade, PAC, codCarro, tipo_de_pneus);

        this.potencia_motor_eletrico = potencia_motor_eletrico;
    }


    public int getPotEletrico() {

        return this.potencia_motor_eletrico;
    }


    public boolean DNF(int volta,int totalvoltas,int clima) {
        Random rand=new Random();
        int x=rand.nextInt(70);
        int motorh = this.potencia_motor_eletrico/20;
        int fiabilidade = (int)((100000/super.getCilindrada())*2.55);
        int desgaste = (int)((volta+1)*0.5);
        return (x > (fiabilidade - desgaste - motorh));
    }
}
