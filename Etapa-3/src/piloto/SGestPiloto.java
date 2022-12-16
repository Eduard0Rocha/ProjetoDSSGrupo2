package src.piloto;

import java.util.HashMap;

public interface SGestPiloto {

    public abstract Piloto getP(String codPiloto);
    public abstract boolean addPiloto(String nome, int cts, int sva);
    public abstract boolean removePiloto(String codPiloto);
    public abstract HashMap<String,Piloto> getPilotos();
    public abstract boolean existsPiloto(String codPiloto);
}
