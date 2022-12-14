package campeonato;


import carro.Pneu;
import circuito.*;
import users.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CampeonatosFacade implements SGestCampeonatos{

    private HashMap<String, Campeonato> campeonatos;
    private int campCounter;

    public CampeonatosFacade(){
        this.campeonatos = new HashMap<String, Campeonato>();
        this.campCounter = 0;
    }

    @Override
    public HashMap<String, Campeonato> getCampeonatos() {
        HashMap<String, Campeonato> result = new HashMap<>();
        for (Map.Entry<String, Campeonato> x : this.campeonatos.entrySet()){
            result.put(x.getKey(), x.getValue());
        }
        return result;
    }

    @Override
    public ArrayList<Circuito> getCircuitos(String codCamp) {
        if (codCamp==null) return null;
        ArrayList<Circuito> result = new ArrayList<>();
        Campeonato c = this.campeonatos.get(codCamp);
        for(Map.Entry<String,Corrida> x : c.getCorridas().entrySet()){
            result.add(x.getValue().getCircuito());
        }
        return result;
    }

    @Override
    public boolean startCampeonato(String codCamp) {
        if(codCamp == null || !this.campeonatos.containsKey(codCamp)) return false;

        return true;
    }

    @Override
    public boolean fimCampeonato(String codCamp) {
        if(codCamp == null || !this.campeonatos.containsKey(codCamp)) return false;
        return true;
    }

    @Override
    public Circuito startCorrida() {
        return null;
    }

    @Override
    public boolean addCorrida(String codCamp, String codCorr, String codCirc) {
        if(codCamp==null || codCorr==null || codCirc==null || !this.campeonatos.containsKey(codCamp)) return false;
        Campeonato c = this.campeonatos.get(codCamp);


        return false;
    }

    @Override
    public HashMap<String, Integer> getClassificacaoC(String codCamp) {
        if(codCamp==null) return null;
        return this.campeonatos.get(codCamp).getClassificacao();
    }

    @Override
    public HashMap<String, Integer> getClassificacaoCH(String codCamp) {
        if(codCamp==null) return null;
        return this.campeonatos.get(codCamp).getClassificacaoH();
    }

    @Override
    public ArrayList<Jogador> getJogadores(String codCamp) {
        if(codCamp==null) return null;
        ArrayList<Jogador> result = new ArrayList<>();
        Campeonato c = this.campeonatos.get(codCamp);
        for(Registo r : c.getRegisto()){
            result.add(r.getJogador());
        }
        return result;
    }

    @Override
    public boolean createCampeonato(String nomeCamp) {
        for(Map.Entry<String, Campeonato> c : this.campeonatos.entrySet()){
            if(c.getValue().getNomeCamp() == nomeCamp) return false;
        }
        String codCamp = new String("Campeonato" + Integer.toString(this.campCounter));

        this.campCounter++;

        this.campeonatos.put(codCamp,new Campeonato(codCamp, nomeCamp));
        return true;
    }

    @Override
    public boolean addRegisto(String codJog, String codPiloto, String codCarro, String codCamp) {
        if(codJog==null || codPiloto==null || codCarro==null || codCamp==null || !this.campeonatos.containsKey(codCamp)) return false;
        Campeonato c = this.campeonatos.get(codCamp);
        c.getRegisto().add(new Registo());//falta completar
        return true;
    }

    @Override
    public boolean afinacoes(String codCamp, String codJog, double downforce) {
        if (codCamp==null || codJog==null) return false;
        Campeonato c = this.campeonatos.get(codCamp);
        int n = (2 * c.getCorridas().size()) / 3;
        for(Registo r : c.getRegisto()){
            if(r.getJogador().getCodJogador()==codJog){
                if(r.getNrAfinacoes() == n) return false;
                r.setNrAfinacoes(r.getNrAfinacoes()+1);
                //falta dar set da downforce
            }
        }
        return true;
    }

    @Override
    public boolean choosetyre(String codCamp, String codJog, ArrayList<Pneu> pneus) {
        if (codCamp==null || codJog==null) return false;
        Campeonato c = this.campeonatos.get(codCamp);
        for(Registo r : c.getRegisto()){
            if(r.getJogador().getCodJogador()==codJog){
                r.getCarro().setPneus(pneus);
            }
        }
        return true;
    }
}
