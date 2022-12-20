package circuito;

import Data.CircuitoDAO;
import carro.C1;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import circuito.Exceptions.*;

public class CircuitosFacade implements SGestCircuitos {

    private HashMap <String,Circuito> Circuitos;
    private  int circuitosCounter;
    private  CircuitoDAO circuitoDAO;

    public CircuitosFacade() throws SQLException, NonExistantKey {
        this.circuitoDAO=new CircuitoDAO();
        this.circuitosCounter= this.circuitoDAO.getmaxkey();
        this.Circuitos=new HashMap<>(this.getCircuitos());
    }

    public boolean  createCicruito(int voltas, String nomeCirc, String local, float dist, List<Integer> curvas, List<Integer> retas, List<Integer> chicanes, float temperatura, int humidade, float temp_Asf, String estado_climaterico,int drs) throws SQLException {
        this.circuitosCounter++;
        String codCirc = new String( Integer.toString(this.circuitosCounter));
        this.Circuitos =  this.circuitoDAO.getCircuitosDB();
        if (!this.Circuitos.containsKey(codCirc)){
            Circuito c= new Circuito(codCirc,voltas,nomeCirc,local,dist,drs,curvas,retas,chicanes);
            c.setCondicoesATM(new CondicoesAtmosfericas(temp_Asf,temperatura,humidade,estado_climaterico));
            this.Circuitos.put(codCirc  ,c.clone());
            this.circuitoDAO.put(codCirc,c.clone());
            return true;
        }

        return false;
    }
    public boolean changeCondicoesATM(String codCirc, float temperatura, int humidade, float temp_Asf, String estado_climaterico){
        if (this.Circuitos.containsKey(codCirc)){
            this.Circuitos.get(codCirc).setCondicoesATM(new CondicoesAtmosfericas(temp_Asf,temperatura,humidade,estado_climaterico));
            return true;
        }
        return false;
      }
      public  HashMap<String, Circuito> getCircuitos() throws NonExistantKey, SQLException {
            this.Circuitos=circuitoDAO.getCircuitosDB();

              return new HashMap<>(this.Circuitos);

      }
     /* public Map<String,Circuito> getCircuitos(){
        Map<String,Circuito> copy = new HashMap<>();
        copy.putAll(Circuitos);
        return  copy;
      }*/

    public boolean removeCircuito(String codCirc){

        if (circuitoDAO.containsKey(codCirc)){
            circuitoDAO.remove(codCirc);
            return true;
        }
        else return false;
    }


}
