package carro;

import java.sql.SQLException;
import java.util.HashMap;

public interface SGestCarros {

    public abstract boolean createC1(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String tipo_de_pneus);
    public abstract boolean createC1H(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String tipo_de_pneus, int potencia_motor_eletrico);
    public abstract boolean createC2(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String tipo_de_pneus, int a_mecanica);
    public abstract boolean createC2H(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String tipo_de_pneus, int a_mecanica, int potencia_motor_eletrico);
    public abstract boolean createGT(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String tipo_de_pneus);
    public abstract boolean createGTH(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String tipo_de_pneus, int potencia_motor_eletrico);
    public abstract boolean createSC(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String tipo_de_pneus);
    public abstract long getTempoCorrida(String codCarro);
    public abstract HashMap<String,Carro> getCarros() throws SQLException;
    public abstract String getCategoria(String codCarro);
    public abstract Carro getCarro(String codCarro);
    public abstract  boolean removeCarro(String codCirc);
}
