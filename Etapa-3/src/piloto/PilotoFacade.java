package piloto;

import java.sql.SQLException;
import java.util.*;

import Data.*;

/**
 * Classe que serve de interface para a manipulação da informação sobre os pilotos
 */
public class PilotoFacade implements SGestPiloto  {

    private HashMap<String, Piloto> pilotos;
    private int pilotosCounter;
    private PilotoDAO pilotoDAO;

    /**
     * Construtor da classe
     */
    public PilotoFacade()  throws  SQLException{


        this.pilotoDAO=new PilotoDAO();
        this.pilotos = pilotoDAO.getPilotosDB();


        this.pilotosCounter = pilotoDAO.getmaxkey();
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
        pilotosCounter++;
        String codPiloto = new String( Integer.toString(this.pilotosCounter));



        Piloto aux=  new Piloto(nome, cts, sva, codPiloto);
        this.pilotos.put(codPiloto, aux.clone());
        this.pilotoDAO.put(Integer.toString(this.pilotosCounter), aux.clone());
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
        if (this.existsPiloto(codPiloto)){

            this.pilotos.remove(codPiloto);
            pilotoDAO.remove(codPiloto);
            return true;
        }
        else return  false;

    }

    /**
     * @return hasmap com a informação de todos os pilotos
     */
    @Override
    public HashMap<String, Piloto> getPilotos()  throws  SQLException{
        this.getPilotosfromDB();
        HashMap<String, Piloto> res = new HashMap<String, Piloto>();

        for (Map.Entry<String,Piloto> set : this.pilotos.entrySet()) {
            res.put(set.getKey(), set.getValue().clone());
        }
        return res;
    }

    public  void getPilotosfromDB() throws SQLException {
        this.pilotos=pilotoDAO.getPilotosDB();
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
