package src.circuito;

import java.util.List;

public interface SGestCircuitos {
    public abstract boolean  createCicruito(int voltas, String nomeCirc, String local, float dist, List<Integer> curvas, List<Integer> retas, List<Integer> chicanes, float temperatura, int humidade, float temp_Asf, String estado_climaterico,int drs);
}
