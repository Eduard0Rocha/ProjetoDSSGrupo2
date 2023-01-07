package carro;

public class Pneu {

    private String tipo;
    private int estado;
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
        this.estado=100;
    }

    public Pneu(String tipo, int estado, String codCarro) {

        this.tipo = new String(tipo);
        this.estado = estado;
        this.codCarro = new String(codCarro);
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {

        return new String(this.tipo);
    }

    public int getEstado() {

        return this.estado;
    }

    @Override
    public String toString() {
        return "Pneu{" +
                "tipo='" + tipo + '\'' +
                ", estado=" + estado +
                ", codCarro='" + codCarro + '\'' +
                '}';
    }
}
