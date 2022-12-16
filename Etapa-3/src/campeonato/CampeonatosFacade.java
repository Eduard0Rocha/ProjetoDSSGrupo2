package campeonato;


import carro.Carro;
import carro.CarrosFacade;
import carro.Pneu;
import circuito.Circuito;
import circuito.CircuitosFacade;
import circuito.Exceptions.NonExistantKey;
import piloto.Piloto;
import piloto.PilotoFacade;
import users.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável pela interação com a informação relativa aos campeonatos
 */
public class CampeonatosFacade implements SGestCampeonatos{

    private HashMap<String, Campeonato> campeonatos;
    private int campCounter;

    /**
     * Contrutor da classe que inicializa as estruturas de dados que contém a informação dos campeonatos
     */
    public CampeonatosFacade(){
        this.campeonatos = new HashMap<String, Campeonato>();
        this.campCounter = 0;
    }

    /**
     * Método que retorna os campeonatos existentes
     * @return HashMap com todos os campeonatos existentes
     */
    @Override
    public HashMap<String, Campeonato> getCampeonatos() {
        HashMap<String, Campeonato> result = new HashMap<>();
        for (Map.Entry<String, Campeonato> x : this.campeonatos.entrySet()){
            result.put(x.getKey(), x.getValue());
        }
        return result;
    }

    /**
     * Método que retorna os circuitos existentes num dado campeonato
     * @param codCamp código identificador do Campeonato
     * @return ArrayList com os circuitos existentes num dado campeonato
     */
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

    /**
     * Método que dá início a um campeonato
     * @param codCamp código identificador do Campeonato
     * @return Retorna true se o campeonato foi iniciado com sucesso, false caso contrário
     */
    //@TODO
    @Override
    public boolean startCampeonato(String codCamp) {
        if(codCamp == null || !this.campeonatos.containsKey(codCamp)) return false;

        return true;
    }

    /**
     * Método que finaliza um campeonato
     * @param codCamp código identificador do Campeonato
     * @return Retorna true se o campeonato foi terminado com sucesso, false caso contrário
     */
    //@TODO
    @Override
    public boolean fimCampeonato(String codCamp) {
        if(codCamp == null || !this.campeonatos.containsKey(codCamp)) return false;
        return true;
    }

    /**
     * Método que dá início a uma corrida
     * @return Circuito associado à corrida iniciada
     */
    //@TODO
    @Override
    public Circuito startCorrida() {
        return null;
    }

    /**
     * Método que adiciona uma nova corrida a um dado campeonato
     * @param codCamp código identificador do Campeonato
     * @param codCorr código identificador da Corrida
     * @param codCirc código identificador do Circuito
     * @return Retorna true se a corrida foi adicionada com sucesso, false caso contrário
     */
    @Override
    public boolean addCorrida(String codCamp, String codCorr, String codCirc) throws SQLException, NonExistantKey {
        if(codCamp==null || codCorr==null || codCirc==null || !this.campeonatos.containsKey(codCamp)) return false;
        Campeonato c = this.campeonatos.get(codCamp);
        CircuitosFacade x = new CircuitosFacade();
        Map<String, Circuito> circuitos = x.getCircuitos();
        Circuito circuito = circuitos.get(codCirc);
        if(circuito==null) return false;
        Corrida nova = new Corrida(codCorr, codCamp, codCirc, circuito);
        c.addCorrida(codCorr, nova);
        return true;
    }

    /**
     * @param codCamp código identificador do Campeonato
     * @return HashMap com as classificações dos carros
     */
    @Override
    public HashMap<String, Integer> getClassificacaoC(String codCamp) {
        if(codCamp==null) return null;
        return this.campeonatos.get(codCamp).getClassificacao();
    }

    /**
     * @param codCamp código identificador do Campeonato
     * @return HashMap com as classificações dos carros Hibridos
     */
    @Override
    public HashMap<String, Integer> getClassificacaoCH(String codCamp) {
        if(codCamp==null) return null;
        return this.campeonatos.get(codCamp).getClassificacaoH();
    }

    /**
     * Método que retorna os jogadores de um dado campeonato
     * @param codCamp código identificador do Campeonato
     * @return ArrayList com os Jogadores
     */
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

    /**
     * Método que cria um campeonato novo
     * @param nomeCamp nome do campeonato que se pretende criar
     * @return true se o campeonato foi criado com sucesso, false caso contrário
     */
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

    /**
     * Método que adiciona um Registo novo a um dado Campeonato
     * @param codJog código identificador do Jogador
     * @param codPiloto código identificador do Piloto
     * @param codCarro código identificador do Carro
     * @param codCamp código identificador do Campeonato
     * @return true se o Registo foi adicionado com sucesso, false caso contrário
     */
    @Override
    public boolean addRegisto(String codJog, String codPiloto, String codCarro, String codCamp) throws SQLException {
        if(codJog==null || codPiloto==null || codCarro==null || codCamp==null || !this.campeonatos.containsKey(codCamp)) return false;
        Campeonato c = this.campeonatos.get(codCamp);
        UserFacade u = new UserFacade();
        Jogador j = u.getJogador(codJog);
        PilotoFacade p = new PilotoFacade();
        Piloto p1 = p.getP(codPiloto);
        CarrosFacade carros = new CarrosFacade();
        Carro carro = carros.getCarro(codCarro);
        Registo novo = new Registo(j, carro, p1);
        c.addRegisto(novo);
        return true;
    }

    /**
     * Método que realiza afinações
     * @param codCamp código identificador do Campeonato
     * @param codJog código identificador do Jogador
     * @param downforce valor da downforce
     * @return true se as afinações foram realizadas, false caso contrário
     */
    //@TODO
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

    /**
     * Método que altera o tipo de pneus
     * @param codCamp código identificador do Campeonato
     * @param codJog código identificador do Jogador
     * @param pneus lista do tipo dos quatro pneus
     * @return true se as alterações foram bem sucedidas, false caso contrário
     */
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
