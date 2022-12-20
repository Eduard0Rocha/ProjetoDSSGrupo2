package carro;

import java.nio.charset.CharacterCodingException;
import java.util.HashMap;
import java.util.Map;
import Data.*;

public class CarrosFacade implements SGestCarros {

    private HashMap<String,Carro> carros;

    private int carrosCounter;
    private CarroDAO carroAcess;

    public CarrosFacade() {

        this.carros = new HashMap<String,Carro>();
        this.carroAcess = new CarroDAO();
        this.carrosCounter = this.carroAcess.getmaxkey();

    }


    @Override
    public boolean createC1(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String tipo_de_pneus) {

        if (marca == null || modelo == null || tipo_de_pneus == null) return false;

        this.carrosCounter++;
        String codCarro = new String( Integer.toString(this.carrosCounter));

        C1 aux = new C1(marca, modelo, cilindrada, potencia, fiabilidade, PAC, codCarro, tipo_de_pneus);
        this.carros.put(codCarro,aux.clone() );
        this.carroAcess.put(aux.clone());
        return true;
    }

    @Override
    public boolean createC1H(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String tipo_de_pneus, int potencia_motor_eletrico) {

        if (marca == null || modelo == null || tipo_de_pneus == null) return false;
        this.carrosCounter++;
        String codCarro = new String( Integer.toString(this.carrosCounter));


        C1H aux = new C1H(marca, modelo, cilindrada, potencia, fiabilidade, PAC, codCarro, tipo_de_pneus,potencia_motor_eletrico);
        this.carros.put(codCarro, aux.clone());
        this.carroAcess.put(aux.clone());
        return true;
    }

    @Override
    public boolean createC2(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String tipo_de_pneus, int a_mecanica) {

        if (marca == null || modelo == null || tipo_de_pneus == null) return false;

        this.carrosCounter++;
        String codCarro = new String( Integer.toString(this.carrosCounter));


        C2 aux = new C2(marca, modelo, cilindrada, potencia, fiabilidade, PAC, codCarro, tipo_de_pneus, a_mecanica);
        this.carros.put(codCarro, aux.clone());
        this.carroAcess.put(aux.clone());

        return true;
    }

    @Override
    public boolean createC2H(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String tipo_de_pneus, int a_mecanica, int potencia_motor_eletrico) {

        if (marca == null || modelo == null || tipo_de_pneus == null) return false;

        this.carrosCounter++;
        String codCarro = new String(Integer.toString(this.carrosCounter));

        C2H aux = new C2H(marca, modelo, cilindrada, potencia, fiabilidade, PAC, codCarro, tipo_de_pneus, a_mecanica, potencia_motor_eletrico);
        this.carros.put(codCarro,aux.clone());
        this.carroAcess.put(aux.clone());
        return true;
    }

    @Override
    public boolean createGT(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String tipo_de_pneus) {

        if (marca == null || modelo == null || tipo_de_pneus == null) return false;

        this.carrosCounter++;
        String codCarro = new String( Integer.toString(this.carrosCounter));

        GT aux=  new GT(marca, modelo, cilindrada, potencia, fiabilidade, PAC, codCarro, tipo_de_pneus);
        this.carros.put(codCarro, aux.clone());
        this.carroAcess.put(aux.clone());
        return true;
    }


    public boolean createGTH(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String tipo_de_pneus, int potencia_motor_eletrico) {

        if (marca == null || modelo == null || tipo_de_pneus == null) return false;

        this.carrosCounter++;
        String codCarro = new String(Integer.toString(this.carrosCounter));


        GTH a = new GTH(marca, modelo, cilindrada, potencia, fiabilidade, PAC, codCarro, tipo_de_pneus, potencia_motor_eletrico);
        this.carros.put(codCarro, a.clone());
        this.carroAcess.put(a.clone());

        return true;
    }

    @Override
    public boolean createSC(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String tipo_de_pneus) {

        if (marca == null || modelo == null || tipo_de_pneus == null) return false;

        this.carrosCounter++;
        String codCarro = new String( Integer.toString(this.carrosCounter));


        SC teste = new SC(marca, modelo, cilindrada, potencia, fiabilidade, PAC, codCarro, tipo_de_pneus);
        //System.out.println(teste.toString());

        this.carros.put(codCarro, teste);
        this.carroAcess.put(teste);

        return true;
    }

    @Override
    public void removerCarro(String codCarro) {

        this.carros.remove(codCarro);
    }

    @Override
    public long getTempoCorrida(String codCarro) {
        return 0;
    }

    @Override
    public HashMap<String, Carro> getCarros() {

        HashMap<String,Carro> result = new HashMap<String, Carro>();

        for (Map.Entry<String,Carro> set : this.carros.entrySet()) {

            result.put(set.getKey(),set.getValue());
        }

        return result;
    }

    @Override
    public String getCategoria(String codCarro) {

        Carro c = this.carros.get(codCarro);

        if (c == null) return null;

        return c.getClass().toString();
    }

    @Override
    public Carro getCarro(String codCarro) {

        return this.carros.get(codCarro);
    }

}
