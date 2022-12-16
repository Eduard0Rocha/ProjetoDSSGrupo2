package src.campeonato;


import carro.Pneu;
import circuito.Circuito;
import src.users.Jogador;

import java.util.ArrayList;
import java.util.HashMap;

public interface SGestCampeonatos {
    public abstract HashMap<String, Campeonato> getCampeonatos();
    public abstract ArrayList<Circuito> getCircuitos(String codCamp);
    public abstract boolean startCampeonato(String codCamp);
    public abstract boolean fimCampeonato(String codCamp);
    public abstract Circuito startCorrida();
    public abstract boolean addCorrida(String codCamp, String codCorr, String codCirc);
    public abstract HashMap<String, Integer> getClassificacaoC(String codCamp);
    public abstract HashMap<String, Integer>  getClassificacaoCH(String codCamp);
    public abstract ArrayList<Jogador> getJogadores(String codCamp);
    public abstract boolean createCampeonato(String nomeCamp);
    public abstract boolean addRegisto(String codJog, String codPiloto, String codCarro, String codCamp);
    public abstract boolean afinacoes(String codCamp, String codJog, double downforce);
    public abstract boolean choosetyre(String codCamp, String codJog, ArrayList<Pneu> pneus);
}
