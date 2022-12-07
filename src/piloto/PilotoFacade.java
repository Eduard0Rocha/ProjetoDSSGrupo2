package piloto;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe que serve de interface para a manipulação da informação sobre os pilotos
 */
public class PilotoFacade implements SGestPiloto {

    private HashMap<String, Piloto> pilotos;
    private int pilotosCounter;

    /**
     * Construtor da classe
     */
    public PilotoFacade() {

        this.pilotos = new HashMap<String, Piloto>();
        this.pilotosCounter = 0;
    }

    /**
     * @param codPiloto código identificador de um piloto
     * @return piloto identificado pelo código fornecido como input
     */
    @Override
    public Piloto getP(String codPiloto) {

        return this.pilotos.get(codPiloto);
    }

    /**
     * Método que adiciona um piloto ao sistema
     * @param nome nome do piloto
     * @param cts avaliação cts do piloto
     * @param sva avaliação sva do piloto
     * @return true se foi bem sucedido, false caso contrário
     */
    @Override
    public boolean addPiloto(String nome, int cts, int sva) {

        if (nome == null) return false;

        String codPiloto = new String("Piloto" + Integer.toString(this.pilotosCounter));

        pilotosCounter++;

        this.pilotos.put(codPiloto, new Piloto(nome, cts, sva, codPiloto));

        return true;
    }

    /**
     * Método que remove um piloto do sistema
     * @param codPiloto código identificador do piloto
     * @return true se foi bem sucedido, false caso contrário
     */
    @Override
    public boolean removePiloto(String codPiloto) {

        if (codPiloto == null) return false;

        this.pilotos.remove(codPiloto);

        return true;
    }

    /**
     * @return hasmap com a informação de todos os pilotos
     */
    @Override
    public HashMap<String, Piloto> getPilotos() {

        HashMap<String, Piloto> res = new HashMap<String, Piloto>();

        for (Map.Entry<String,Piloto> set : this.pilotos.entrySet()) {

            res.put(set.getKey(), set.getValue());
        }

        return res;
    }

    /**
     * @param codPiloto código identificador do piloto
     * @return true se o piloto existe no sistema, false caso contrário
     */
    @Override
    public boolean existsPiloto(String codPiloto) {

        return this.pilotos.containsKey(codPiloto);
    }
}
