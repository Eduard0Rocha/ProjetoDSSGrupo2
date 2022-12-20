package carro;

import java.util.Random;

public class C1H extends C1 implements Hibrido
{

    private int potencia_motor_eletrico;

    public C1H(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String codCarro, String tipo_de_pneus, int potencia_motor_eletrico) {

        super(marca, modelo, cilindrada, potencia, fiabilidade, PAC, codCarro, tipo_de_pneus);

        this.potencia_motor_eletrico = potencia_motor_eletrico;
    }

    public C1H(C1H a){
        super(a);
        this.potencia_motor_eletrico=a.getPotEletrico();
    }

    @Override
    public int getPotEletrico() {

        return this.potencia_motor_eletrico;
    }

    @Override
    public boolean DNF(int volta,int totalvoltas,int clima) {
        Random rand=new Random();
        int x=rand.nextInt(85);
        int motorh = this.potencia_motor_eletrico/20;
        return (x > (super.getFiabilidade()-motorh));
    }

    public C1H clone(){
        return new C1H(this);
    }
}
