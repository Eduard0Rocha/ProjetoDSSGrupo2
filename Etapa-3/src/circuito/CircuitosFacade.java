package src.circuito;

import src.carro.C1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.circuito.Exceptions.*;

public class CircuitosFacade implements SGestCircuitos {

    private HashMap <String,Circuito> Circuitos;
    private  int circuitosCounter;

    public CircuitosFacade(){
        this.Circuitos=new HashMap<>();
        this.circuitosCounter=0;
    }

    public boolean  createCicruito(int voltas, String nomeCirc, String local, float dist, List<Integer> curvas, List<Integer> retas, List<Integer> chicanes, float temperatura, int humidade, float temp_Asf, String estado_climaterico,int drs){
        String codCirc = new String("Circuito" + Integer.toString(this.circuitosCounter));
        this.circuitosCounter++;
        if (this.Circuitos.containsKey(codCirc)){
            Circuito c= new Circuito(codCirc,voltas,nomeCirc,local,dist,drs);
            c.setCondicoesATM(new CondicoesAtmosfericas(temp_Asf,temperatura,humidade,estado_climaterico));
            this.Circuitos.put(codCirc  ,c.clone());
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
      public  Circuito getCircuito(String codCirc) throws NonExistantKey{
          if (Circuitos.containsKey(codCirc)){
              return this.Circuitos.get(codCirc).clone();
          }
          else throw new NonExistantKey("Chave inexistente");
      }
      public Map<String,Circuito> getCircuitos(){
        Map<String,Circuito> copy = new HashMap<>();
        copy.putAll(Circuitos);
        return  copy;
      }
}
