package carro;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Carro
{

    private String marca;
    private String modelo;
    private int cilindrada;
    private int potencia;
    private int fiabilidade;
    private float PAC;
    private boolean DNF;

    private String codCarro;
    private ArrayList<Pneu> pneus;
    private int used;

    public Carro() {

        this.marca = null;
        this.modelo = null;
        this.cilindrada = -1;
        this.potencia = -1;
        this.fiabilidade = -1;
        this.PAC = -1;
        this.DNF = false;
        this.codCarro = null;
        this.used=0;

        this.pneus = new ArrayList<Pneu>();

        for (int i = 0; i < 4; i++) {

            this.pneus.add(new Pneu());
        }
    }

    public Carro(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String codCarro) {
        this.used=0;

        this.marca = new String(marca);
        this.modelo = new String(modelo);
        this.cilindrada = cilindrada;
        this.potencia = potencia;
        this.fiabilidade = fiabilidade;
        this.PAC = PAC;
        this.DNF = false;
        this.codCarro = new String(codCarro);

        this.pneus = new ArrayList<Pneu>();

        for (int i = 0; i < 4; i++) {

            this.pneus.add(new Pneu(this.codCarro));
        }
    }

    public Carro(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String codCarro, String tipo_de_pneus) {

        this.marca = new String(marca);
        this.modelo = new String(modelo);
        this.cilindrada = cilindrada;
        this.potencia = potencia;
        this.fiabilidade = fiabilidade;
        this.PAC = PAC;
        this.DNF = false;
        this.codCarro = new String(codCarro);

        this.pneus = new ArrayList<Pneu>();

        for (int i = 0; i < 4; i++) {

            this.pneus.add(new Pneu(tipo_de_pneus, this.codCarro));
        }
    }


    public Carro(Carro c) {

        this.marca = new String(c.marca);
        this.modelo = new String(c.modelo);
        this.cilindrada = c.cilindrada;
        this.potencia = c.potencia;
        this.fiabilidade = c.fiabilidade;
        this.PAC = c.PAC;
        this.DNF = c.DNF;
        this.codCarro = new String(c.codCarro);
        this.used=c.getUsed();

        this.pneus = new ArrayList<Pneu>();

        for (int i = 0; i < 4; i++) {

            Pneu p = c.pneus.get(i);

            this.pneus.add(new Pneu(p.getTipo(), p.getEstado(), this.codCarro));
        }
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public abstract boolean DNF(int volta, int totalVoltas, int clima);

 //   public abstract void setFiabilidade(int volta, int clima);

    public int getCilindrada() {

        return this.cilindrada;
    }



    @Override
    public String toString() {
        return "Carro : " +codCarro +
                "Classe: "+this.getclasse()+" "+
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", cilindrada=" + cilindrada +
                ", potencia=" + potencia +
                ", fiabilidade=" + fiabilidade +
                ", PAC=" + PAC +
                ", DNF=" + DNF +
                ", pneus=" + pneus.toString() ;
    }
    public String getCategoria()
    {

        if (this == null) return null;

        return this .getClass().toString();
    }


    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public int getPotencia() {
        return this.potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public void setFiabilidade(int fiabilidade)
    {
        this.fiabilidade = fiabilidade;
    }

    public int getFiabilidade()
    {
        return this.fiabilidade;
    }

    public float getPAC() {
        return this.PAC;
    }

    public void setPAC(float PAC) {
        this.PAC = PAC;
    }




    public boolean getDNF() {
        return this.DNF;
    }

    public void setDNF(boolean DNF) {
        this.DNF = DNF;
    }



    public String getCodCarro() {
        return this.codCarro;
    }

    public void setCodCarro(String codCarro) {
        this.codCarro = codCarro;
    }

    public ArrayList<Pneu> getPneus() {
        return new ArrayList<>(this.pneus);
    }

    public void setPneus(ArrayList<Pneu> pneus) {
        this.pneus = pneus;
    }

    public String getclasse()
    {
        if (this.getCategoria().equals("class carro.C1")) {
            return ("C1");
        }
        if (this.getCategoria().equals("class carro.C1H")) {
            return ("C1H");
        }
        if (this.getCategoria().equals("class carro.C2")) {
            return ("C2");
        }
        if (this.getCategoria().equals("class carro.C2H")) {
            return ("C2H");
        }
        if (this.getCategoria().equals("class carro.GT")){
            return ("GT");
        }
        if (this.getCategoria().equals("class carro.GTH")) {
            return ("GTH");
        }
        if (this.getCategoria().equals("class carro.SC")) {
            return ("SC");
        }
        else return ("None");

    }

    public  int getDNFINT(){
        if (this.DNF) return 1;
        return 0;
    }

    public float getEquipamento()
    {

         if (this.getclasse().equals("C1H")){
             C1H carr = (C1H)this;
             return this.PAC * this.potencia * this.cilindrada * carr.getPotEletrico() /1000;
        }
        else if (this.getclasse().equals("C2")){
             C2 carr = (C2) this;
             return this.PAC * this.potencia * this.cilindrada * carr.getAfinacao_mecanica() /1000;
        }
        else if (this.getclasse().equals("GTH")){
             GTH carr = (GTH) this;
             return this.PAC * this.potencia * this.cilindrada *carr.getPotEletrico()/1000;
        }
        else if (this.getclasse().equals("C2H")){
             C2H carr = (C2H) this;
             return this.PAC * this.potencia * this.cilindrada *carr.getPotEletrico() * carr.getAfinacao_mecanica()/1000;
        }
        else return this.PAC * this.potencia * this.cilindrada;
    }
}
