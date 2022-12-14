package campeonato;

import carro.Carro;
import piloto.Piloto;
import users.Jogador;

public class Registo {

    private Jogador jogador;
    private Carro carro;
    private Piloto piloto;
    private int nrAfinacoes;

    public Registo(){
        this.jogador = null;
        this.carro = null;
        this.piloto = null;
        this.nrAfinacoes = 0;
    }

    public Registo(Jogador jogador, Carro carro, Piloto piloto){
        this.jogador = jogador;
        this.carro = carro;
        this.piloto = piloto;
        this.nrAfinacoes = 0;
    }

    public Jogador getJogador() {
        return this.jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Carro getCarro() {
        return this.carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Piloto getPiloto() {
        return this.piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public int getNrAfinacoes() {
        return this.nrAfinacoes;
    }

    public void setNrAfinacoes(int nrAfinacoes) {
        this.nrAfinacoes = nrAfinacoes;
    }
}
