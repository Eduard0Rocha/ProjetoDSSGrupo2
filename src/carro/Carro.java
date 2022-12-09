package carro;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Carro {

    private String marca;
    private String modelo;
    private int cilindrada;
    private int potencia;
    private int fiabilidade;
    private float PAC;
    private float distPerc;
    private boolean DNF;
    private long tempo;
    private String codCarro;
    private ArrayList<Pneu> pneus;

    public Carro() {

        this.marca = null;
        this.modelo = null;
        this.cilindrada = -1;
        this.potencia = -1;
        this.fiabilidade = -1;
        this.PAC = -1;
        this.distPerc = -1;
        this.DNF = false;
        this.tempo = -1;
        this.codCarro = null;

        this.pneus = new ArrayList<Pneu>();

        for (int i = 0; i < 4; i++) {

            this.pneus.add(new Pneu());
        }
    }

    public Carro(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, int PAC, String codCarro) {

        this.marca = new String(marca);
        this.modelo = new String(modelo);
        this.cilindrada = cilindrada;
        this.potencia = potencia;
        this.fiabilidade = fiabilidade;
        this.PAC = PAC;
        this.distPerc = 0;
        this.DNF = false;
        this.tempo = 0;
        this.codCarro = new String(codCarro);

        this.pneus = new ArrayList<Pneu>();

        for (int i = 0; i < 4; i++) {

            this.pneus.add(new Pneu());
        }
    }

    public Carro(Carro c) {

        this.marca = new String(c.marca);
        this.modelo = new String(c.modelo);
        this.cilindrada = c.cilindrada;
        this.potencia = c.potencia;
        this.fiabilidade = c.fiabilidade;
        this.PAC = c.PAC;
        this.distPerc = c.distPerc;
        this.DNF = c.DNF;
        this.tempo = c.tempo;
        this.codCarro = new String(c.codCarro);

        this.pneus = new ArrayList<Pneu>();

        for (int i = 0; i < 4; i++) {

            Pneu p = c.pneus.get(i);

            this.pneus.add(new Pneu(p.getTipo(), p.getEstado(), this.codCarro));
        }
    }

    // TODO
    // falta a classe circuito
    /*public long tempoProxVolta(Circuito c, int clima, int volta) {

        return -1;
    }*/

    public abstract boolean DNF(int volta, int totalVoltas, int clima);

    public abstract void setFiabilidade(int volta, int clima);

    public int getCilindrada() {

        return this.cilindrada;
    }

    protected int getFiabilidade() {

        return this.fiabilidade;
    }
}
