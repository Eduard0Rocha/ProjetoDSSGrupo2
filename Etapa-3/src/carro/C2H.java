package carro;

import java.util.Random;

public class C2H extends C2 implements Hibrido {

    private int potencia_motor_eletrico;

    public C2H(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String codCarro, String tipo_de_pneus, int a_mecanica, int potencia_motor_eletrico) {

        super(marca, modelo, cilindrada, potencia, fiabilidade, PAC, codCarro, tipo_de_pneus, a_mecanica);

        this.potencia_motor_eletrico = potencia_motor_eletrico;
    }
    public C2H(C2H a){
        super(a);
        this.potencia_motor_eletrico=a.potencia_motor_eletrico;
    }
    @Override
    public int getPotEletrico() {

        return this.potencia_motor_eletrico;
    }

    @Override
    public boolean DNF(int volta,int totalvoltas,int clima) {
        Random rand=new Random();
        int x=rand.nextInt(85);
        int fiabilidade = super.getFiabilidade() + (super.getCilindrada()/1200) + (this.getAfinacao_mecanica()/10);
        return (x > fiabilidade);
    }


    public void setFiabilidade(int volta, int clima) {

        // TODO
    }

    public C2H clone(){
        return  new C2H(this);
    }
}
