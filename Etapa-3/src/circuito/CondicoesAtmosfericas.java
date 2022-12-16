package src.circuito;

import java.util.Objects;

public class CondicoesAtmosfericas {

    private float temperatura_Asf;
    private float temperatura;
    private int humidade;
    private String estado_climaterico;




    public CondicoesAtmosfericas() {
        this.temperatura_Asf = 0;
        this.temperatura = 0;
        this.humidade = 0;
        this.estado_climaterico = "";
    }
    public CondicoesAtmosfericas(float temperatura_Asf, float temperatura, int humidade, String estado_climaterico) {
        this.temperatura_Asf = temperatura_Asf;
        this.temperatura = temperatura;
        this.humidade = humidade;
        this.estado_climaterico = estado_climaterico;
    }

    public CondicoesAtmosfericas(CondicoesAtmosfericas c) {
        this.temperatura_Asf = c.getTemperaturaAsf();
        this.temperatura = c.getTemperatura();
        this.humidade = c.getHumidade();
        this.estado_climaterico = c.getEstado_climaterico();
    }

    public float getTemperaturaAsf() {
        return this.temperatura_Asf;
    }

    public void setTemperatura_Asf(float temperatura) {
        this.temperatura_Asf = temperatura;
    }

    public float getTemperatura() {
        return this.temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public int getHumidade() {
        return this.humidade;
    }

    public void setHumidade(int humidade) {
        this.humidade = humidade;
    }

    public String getEstado_climaterico() {
        return this.estado_climaterico;
    }

    public void setEstado_climaterico(String estado_climaterico) {
        this.estado_climaterico = estado_climaterico;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CondicoesAtmosfericas that = (CondicoesAtmosfericas) o;
        return Float.compare(that.temperatura_Asf, temperatura_Asf) == 0 && Float.compare(that.temperatura, temperatura) == 0 && humidade == that.humidade && Objects.equals(estado_climaterico, that.estado_climaterico);
    }

    public  CondicoesAtmosfericas clone(){
        return  new CondicoesAtmosfericas(this);
    }


    public String toString() {
        return "CondicoesAtmosfericas{" +
                "temperatura_Asf=" + temperatura_Asf +
                ", temperatura=" + temperatura +
                ", humidade=" + humidade +
                ", estado_climaterico='" + estado_climaterico + '\'' +
                '}';
    }
}
