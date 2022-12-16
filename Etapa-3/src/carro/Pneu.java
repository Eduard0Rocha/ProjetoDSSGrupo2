package src.carro;


public class Pneu {

    private String tipo;
    private short estado;
    private String codCarro;

    public Pneu() {

        this.tipo = null;
        this.estado = 100;
        this.codCarro = null;
    }

    public Pneu(String codCarro) {

        this.tipo = null;
        this.estado = 100;
        this.codCarro = new String(codCarro);
    }

    public Pneu(String tipo, String codCarro) {

        this.tipo = new String(tipo);
        this.codCarro = new String(codCarro);
    }

    public Pneu(String tipo, short estado, String codCarro) {

        this.tipo = new String(tipo);
        this.estado = estado;
        this.codCarro = new String(codCarro);
    }

    public String getTipo() {

        return new String(this.tipo);
    }

    public short getEstado() {

        return this.estado;
    }
}
