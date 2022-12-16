package src.carro;


import java.nio.charset.CharacterCodingException;
import java.util.HashMap;
import java.util.Map;

public class CarrosFacade implements SGestCarros {

    private HashMap<String,Carro> carros;

    private int carrosCounter;

    public CarrosFacade() {

        this.carros = new HashMap<String,Carro>();
        this.carrosCounter = 0;
    }


    @Override
    public boolean createC1(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String tipo_de_pneus) {

        if (marca == null || modelo == null || tipo_de_pneus == null) return false;

        String codCarro = new String("Carro" + Integer.toString(this.carrosCounter));

        this.carrosCounter++;

        this.carros.put(codCarro, new C1(marca, modelo, cilindrada, potencia, fiabilidade, PAC, codCarro, tipo_de_pneus));

        return true;
    }

    @Override
    public boolean createC1H(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String tipo_de_pneus, int potencia_motor_eletrico) {

        if (marca == null || modelo == null || tipo_de_pneus == null) return false;

        String codCarro = new String("Carro" + Integer.toString(this.carrosCounter));

        this.carrosCounter++;

        this.carros.put(codCarro, new C1H(marca, modelo, cilindrada, potencia, fiabilidade, PAC, codCarro, tipo_de_pneus, potencia_motor_eletrico));

        return true;
    }

    @Override
    public boolean createC2(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String tipo_de_pneus, int a_mecanica) {

        if (marca == null || modelo == null || tipo_de_pneus == null) return false;

        String codCarro = new String("Carro" + Integer.toString(this.carrosCounter));

        this.carrosCounter++;

        this.carros.put(codCarro, new C2(marca, modelo, cilindrada, potencia, fiabilidade, PAC, codCarro, tipo_de_pneus, a_mecanica));

        return true;
    }

    @Override
    public boolean createC2H(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String tipo_de_pneus, int a_mecanica, int potencia_motor_eletrico) {

        if (marca == null || modelo == null || tipo_de_pneus == null) return false;

        String codCarro = new String("Carro" + Integer.toString(this.carrosCounter));

        this.carrosCounter++;

        this.carros.put(codCarro, new C2H(marca, modelo, cilindrada, potencia, fiabilidade, PAC, codCarro, tipo_de_pneus, a_mecanica, potencia_motor_eletrico));

        return true;
    }

    @Override
    public boolean createGT(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String tipo_de_pneus) {

        if (marca == null || modelo == null || tipo_de_pneus == null) return false;

        String codCarro = new String("Carro" + Integer.toString(this.carrosCounter));

        this.carrosCounter++;

        this.carros.put(codCarro, new GT(marca, modelo, cilindrada, potencia, fiabilidade, PAC, codCarro, tipo_de_pneus));

        return true;
    }

    @Override
    public boolean createGTH(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String tipo_de_pneus, int potencia_motor_eletrico) {

        if (marca == null || modelo == null || tipo_de_pneus == null) return false;

        String codCarro = new String("Carro" + Integer.toString(this.carrosCounter));

        this.carrosCounter++;

        this.carros.put(codCarro, new GTH(marca, modelo, cilindrada, potencia, fiabilidade, PAC, codCarro, tipo_de_pneus, potencia_motor_eletrico));

        return true;
    }

    @Override
    public boolean createSC(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String tipo_de_pneus) {

        if (marca == null || modelo == null || tipo_de_pneus == null) return false;

        String codCarro = new String("Carro" + Integer.toString(this.carrosCounter));

        this.carrosCounter++;

        this.carros.put(codCarro, new GT(marca, modelo, cilindrada, potencia, fiabilidade, PAC, codCarro, tipo_de_pneus));

        return true;
    }

    @Override
    public void removerCarro(String codCarro) {

        this.carros.remove(codCarro);
    }

    // TODO (falta a classe circuito)
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
