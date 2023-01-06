package Business;

import campeonato.Campeonato;
import campeonato.CampeonatosFacade;
import campeonato.Corrida;
import campeonato.Registo;
import carro.Carro;
import carro.CarrosFacade;
import circuito.Circuito;
import circuito.CircuitosFacade;
import circuito.Exceptions.NonExistantKey;
import piloto.Piloto;
import piloto.PilotoFacade;
import users.*;

import java.security.Key;
import java.sql.SQLException;
import java.util.*;

public class LogicaNegocio implements  F1Manager{

    private static Scanner ler = new Scanner(System.in);
    private static PilotoFacade pil;
    private static CircuitosFacade circ;
    private AuthenticatedPlayer jogadorAutenticado;
    private Guest guest;
    private Admin admin;

    private static CarrosFacade carr;
    private static UserFacade users;
    private static CampeonatosFacade camp;

    static {
        try {
            pil = new PilotoFacade();
            circ = new CircuitosFacade();
            users = new UserFacade();
            carr = new CarrosFacade();
            camp = new CampeonatosFacade();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NonExistantKey e) {
            throw new RuntimeException(e);
        }
    }

    public void addPiloto(String nome, float cts, float sva) {
        pil.addPiloto(nome, cts, sva);
    }

    public HashMap<String, Piloto> getpilotos() throws SQLException {
        return pil.getPilotos();
    }

    public boolean removePiloto(String codigo) {
        return pil.removePiloto(codigo);
    }

    public HashMap<String, Campeonato> getCampeonatos() throws SQLException {
        return camp.getCampeonatos();
    }

    public void createCampeonato(String nCamp) throws SQLException {
        camp.createCampeonato(nCamp);
    }

    public HashMap<String, Carro> getCarros() throws SQLException {
        return carr.getCarros();
    }

    public  Carro getCarro(String codCarro) throws SQLException {
        return carr.getCarro(codCarro);
    }
    public boolean existsCampeonato(String cCamp) {
        return camp.existsCampeonato(cCamp);
    }

    public boolean existsUser(String cJog) throws SQLException {
        return users.existeUser(cJog);
    }
    public boolean existsCodJog(String cJog) throws SQLException {
        return users.existscodJog(cJog);
    }

    public boolean existsPil(String codPil) throws SQLException {
        return pil.existsPiloto(codPil);
    }

    public boolean existsCarro(String codCarro) throws SQLException {
        return (carr.existeCarros(codCarro));
    }

    public void addRegisto(String cJog, String cPl, String cCar, String cCamp) throws SQLException {
        camp.addRegisto(cJog, cPl, cCar, cCamp);
    }

    public boolean validUser(String username, String password) throws SQLException {
        return users.validUser(username, password);
    }

    public boolean existsAdmin(String username) throws SQLException {
        return users.existeAdmin(username);
    }

    public boolean validPasswordAdmin(String username, String password) throws SQLException {
        return users.validPasswordAdmin(username, password);
    }

    public boolean validGuest(String username) throws SQLException {
        return users.validGuest(username);
    }

    public boolean containsUNAME(String username) throws SQLException {
        return users.containsUNAME(username);
    }

    public boolean createAPlayer(String name, String username, String password) throws SQLException, CloneNotSupportedException {
        return users.createAPlayer(name, username, password);
    }

    public boolean createAdmin(String name, String contacto, String email, String username, String password) throws SQLException, CloneNotSupportedException {
        return users.createAdmin(name, contacto, email, username, password);
    }

    public boolean createGuest(String username) throws SQLException, CloneNotSupportedException {
        return users.createGuest(username);
    }

    public HashMap<String, Admin> getAdmins() throws SQLException, CloneNotSupportedException {
        return users.getAdmins();
    }

    public HashMap<String, Jogador> getPlayers() throws SQLException, CloneNotSupportedException {
        return users.getPlayers();
    }

    public boolean createC1(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String tipoPneus) {
        return carr.createC1(marca, modelo, cilindrada, potencia, fiabilidade, PAC, tipoPneus);
    }

    public boolean createC1H(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float pac, String tipoPneus, int potencia_motor_eletrico) {
        return carr.createC1H(marca, modelo, cilindrada, potencia, fiabilidade, pac, tipoPneus, potencia_motor_eletrico);
    }

    public boolean createC2(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float pac, String tipoPneus, int a_mecanica) {
        return carr.createC2(marca, modelo, cilindrada, potencia, fiabilidade, pac, tipoPneus, a_mecanica);
    }

    public boolean createC2H(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float pac, String tipo_de_pneus, int afinacao_mecanica, int potencia_motor_eletrico) {
        return carr.createC2H(marca, modelo, cilindrada, potencia, fiabilidade, pac, tipo_de_pneus, afinacao_mecanica, potencia_motor_eletrico);
    }

    public boolean createGT(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float pac, String tipoPneus) {
        return carr.createGT(marca, modelo, cilindrada, potencia, fiabilidade, pac, tipoPneus);
    }

    public boolean createGTH(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float pac, String tipoPneus, int potencia_motor_eletrico) {
        return carr.createGTH(marca, modelo, cilindrada, potencia, fiabilidade, pac, tipoPneus, potencia_motor_eletrico);
    }

    public boolean createSC(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float pac, String tipoPneus){
   return  carr.createSC(marca,modelo,cilindrada,potencia,fiabilidade,pac,tipoPneus);
}
    public boolean removeCarro(String cod){
        return  carr.removeCarro(cod);
    }

    public boolean  createCicruito(int voltas, String ncirc, String local, float dist, List<Integer> curvasList, List<Integer> retaslist, List<Integer> chicaneList, float temperatura, int humidade, float temperatura_asf, String estado_climaterico, int DRS) throws SQLException {
       return  circ.createCicruito(voltas,ncirc,local,dist,curvasList,retaslist,chicaneList,temperatura,humidade,temperatura_asf,estado_climaterico,DRS);
    }
    public HashMap<String, Circuito>  getCircuitos() throws SQLException, NonExistantKey {
        return  circ.getCircuitos();
    }
    public Circuito  getCircuito(String codCircuito) throws SQLException, NonExistantKey {
        return  circ.getCrcuito(codCircuito);
    }
    public Piloto  getPiloto(String codPiloto) throws SQLException, NonExistantKey {
        return  pil.getPiloto(codPiloto);
    }
    public boolean  removeCircuito(String cod)  {
        return  circ.removeCircuito(cod);
    }

    public boolean  existsCircuito(String codCirc)  {
        return circ.existsCircuito(codCirc);
    }
    public boolean  removeCamp(String cod) {
        return camp.removeCampeonato(cod);
    }

    public Jogador  getJogadorAG(String cod) throws SQLException {
        return users.getJogadorAG(cod);
    }
    public ArrayList<Circuito> getCircuitosCamp(String cod) throws SQLException, NonExistantKey {
            if (existsCampeonato(cod)){
                HashMap<String, Corrida> corr = camp.getCorridasA(cod);
                Object[] col=corr.values().toArray();
                ArrayList<Circuito> circ= new ArrayList<>();
                for(int i =0;i<col.length;i++){
                    Corrida c = (Corrida) col[i];
                     String codCircuito=c.getCodCirc();
                     Circuito novo = this.getCircuito(codCircuito);
                     circ.add(novo);
                }
                return circ;
            }
            else return new ArrayList<>();
        }


    public ArrayList<Jogador> getJogadoresCamp(String cod) throws SQLException {
        if (existsCampeonato(cod)) {
            ArrayList<Registo> regs=camp.getRegistos(cod);
            ArrayList<Jogador> players=new ArrayList<>();
            for(int i =0;i<regs.size();i++){
                Jogador a = this.getJogadorAG(regs.get(i).getJogador().getCodJogador());
                players.add(a);
            }
            return  players;
        }
        else return new ArrayList<>();
    }

    public HashMap<String, Integer> getClassificacaoC(String cod)  {

        return camp.getClassificacaoC(cod);
    }
    public HashMap<String, Integer> getClassificacaoCH(String cod)  {
        return camp.getClassificacaoCH(cod);
    }
    public boolean addCorridaCamp(String cCamp,String cCir) throws SQLException, NonExistantKey {

        return camp.addCorrida(cCamp, cCir);
    }

    public HashMap<String,Integer> simulaCampeonato(String codCamp) throws SQLException {
        HashMap<String,Corrida> corridas = camp.getCorridasA(codCamp);
        Corrida c = this.camp.getCorrida(codCamp);
        ArrayList<Registo> registos = this.camp.getRegistos(codCamp);

        HashMap<String,Integer> pontosCamp=new HashMap<>();

        for(int i =0;i<registos.size();i++){
            pontosCamp.put(registos.get(i).getJogador().getCodJogador(),this.getJogadorAG(registos.get(i).getJogador().getCodJogador()).getPontosGlob());
        }

        Object[] aux = corridas.keySet().toArray();
        for (int k =0;k<aux.length;k++){
                String au = (String) aux[k];
               Corrida corr = corridas.get(au);
                HashMap<String,Integer> classcorr=atribuipontos(simularCorrida(corr.getCodCorr(),corr.getCodCamp()));
                camp.addClassCorr(classcorr,corr.getCodCorr());
                pontosCamp = simulaCorr(pontosCamp,classcorr);
        }

        for (int i=0;i<registos.size();i++)
        {
            this.setpontosJogdb(registos.get(i).getJogador().getCodJogador(),pontosCamp.get(registos.get(i).getJogador()));
        }

       //TODO :PASSAR HASHMAPS DE PONTOS QUER NO FINAL DE UMA CORRIDA QUER NO FINAL DO CAMPEONATO PARA POR NA BD  PARA HASMAP <IDPLAYER,POSICAO>.
        // TODO : COM ESTES HASHMAPS POR NA BASE DE DADOS
        return pontosCamp;
    }

    public void setpontosJogdb(String codJog,int pts) throws SQLException {
        users.setpontosDB(codJog,pts);
    }



    public HashMap<String,Integer> atribuipontos(HashMap<String,Integer> classificacoes){
        HashMap<String,Integer> pontos=new HashMap<>();
        Object[] aux = classificacoes.keySet().toArray();
        for (int i=0;i<aux.length;i++){
            String n1=(String) aux[i];
            switch (classificacoes.get(n1)){
                case 1 : {
                    pontos.put(n1,12);
                    break;
                }
                case 2 :{
                    pontos.put(n1,10);
                    break;
                }
                case 3 : {
                    pontos.put(n1,8);
                    break;
                }
                case 4 :{
                    pontos.put(n1,7);
                    break;
                }
                case 5 : {
                    pontos.put(n1,4);
                    break;
                }
                case 6 :{
                    pontos.put(n1,4);
                    break;
                }
                case 7 : {
                    pontos.put(n1,4);
                    break;
                }
                case 8 :{
                    pontos.put(n1,4);
                    break;
                }
                case 9 : {
                    pontos.put(n1,4);
                    break;
                }
                case 10 :{
                    pontos.put(n1,4);
                    break;
                } default :break;

            }
        }
        return pontos;

    }


    public HashMap<String,Integer> simulaCorr(HashMap<String,Integer> glob,HashMap<String,Integer> corr)
    {
        Object[] arr= corr.keySet().toArray();
        for (int i =0;i<arr.length;i++){
            String a = (String)  arr[i];
            int pts =glob.get(a);
            glob.put(a,pts+corr.get(a));
        }
        return glob;
    }

    public  HashMap<String,Integer> simularCorrida(String corr_cod, String cod_camp) throws SQLException {

        Corrida c = this.camp.getCorrida(corr_cod);
        Circuito circuito = this.circ.getCrcuito(c.getCodCirc());
        ArrayList<Registo> registos = this.camp.getRegistos(cod_camp);
        HashMap<String,Float> tempos = new HashMap<>();

        int nvoltas=circuito.getNvoltas();



        for (int i = 0; i < registos.size(); i++)
        {
            Registo registo = registos.get(i);
            Piloto piloto = registo.getPiloto();
            Carro carro = registo.getCarro();
            Jogador jogador = registo.getJogador();
            float tempo =0;
                for (int k =0;k<nvoltas;k++)
                {
                tempo+=simularVolta(piloto,carro,circuito);
                    if (carro.DNF(k+1,nvoltas,circuito.getCondicoesATM().getHumidade())) {
                        k=nvoltas;
                        tempo=-1;
                    }
                }
            tempos.put(jogador.getCodJogador(),tempo);
        }

        HashMap<String,Integer> classificacao= new HashMap<>();
        String aux;
        int pos=1;
        while ((aux = returnlowerFinish(tempos))!=null){
            classificacao.put(aux,pos);
            pos++;
            tempos.remove(aux);
        }

        while (tempos.size()>0){
            aux=(String)tempos.keySet().toArray()[0];
            classificacao.put(aux,pos);
            pos++;
            tempos.remove(aux);
        }
        return classificacao;
    }
    public String returnlowerFinish(HashMap<String,Float>tempos){
    float min= Float.MAX_VALUE;
    String key =null;
        for (String k:tempos.keySet()) {
            if (tempos.get(k) == -1) continue;
                if (tempos.get(k) < min) {
                    key = k;
                    min = tempos.get(k);
                }
            }
        return key;
    }



    public  float simularVolta(Piloto piloto, Carro carro, Circuito circuito) {
        float tempoVolta = 0;

        List<Integer> retas = circuito.getRetasGDU();
        List<Integer> curvas = circuito.getCurvasGDU();
        List<Integer> chicanes = circuito.getChicanesGDU();

        for (int i = 0; i < retas.size(); i++) {
            int grauDificuldade = retas.get(i);
            tempoVolta += simularReta(piloto, carro, grauDificuldade);
        }
        for (int i = 0; i < curvas.size(); i++) {
            int grauDificuldade = curvas.get(i);
            tempoVolta += simularCurva(piloto, carro, grauDificuldade);
        }
        for (int i = 0; i < chicanes.size(); i++) {
            int grauDificuldade = chicanes.get(i);
            tempoVolta += simularChicane(piloto, carro, grauDificuldade);
        }

        return tempoVolta;
    }

    public static float simularReta(Piloto piloto, Carro carro, int grauDificuldade) {
        float tempo = (grauDificuldade / piloto.getSVA()) * (grauDificuldade / carro.getEquipamento());
        return tempo;
    }

    public static float simularCurva(Piloto piloto, Carro carro, int grauDificuldade) {
        float tempo = (grauDificuldade / piloto.getSVA()) * (grauDificuldade / carro.getEquipamento());
        return tempo;
    }

    public static float simularChicane(Piloto piloto, Carro carro, int grauDificuldade) {
        float tempo = (grauDificuldade / piloto.getSVA()) * (grauDificuldade / carro.getEquipamento());
        return tempo;
    }

    public void  povoarbasedados() throws SQLException, CloneNotSupportedException {
        createCampeonato("CampeonatoMIEI");
        createCampeonato("CampeonatoLEI");
        createCampeonato("CampeonatoDSS");
        createCampeonato("CampeonatoDI");
// APlayer: createAPlayer(String name, String username, String password)
        createAPlayer("Eduardo", "Edu4rd0", "123");
        createAPlayer("Bernardo", "Bern4rdo", "456");
        createAPlayer("Guilherme", "Gu1lherme", "678");
        createAPlayer("Alex", "Al3x", "012");
        createAPlayer("Hugo", "Hu6o", "345");
        createAPlayer("ALS10", "ALS", "123");
        createAPlayer("Creissac", "Creissac10", "789");
        createAPlayer("Nestor", "ANestor15", "678");
// Guest: createGuest(String username)
        createGuest("Guest0");
        createGuest("Guest1");
        createGuest("Guest2");
// C1: createC1(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float PAC, String tipoPneus)
        createC1("Aston", "Martin", 6000, 700, 95, 0.5F, "Duro");
        createC1("Bugatti", "Chiron", 6000, 600, 95, 0.5F, "Macio");
// C1H: createC1H(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float pac, String tipoPneus, int potencia_motor_eletrico)
        createC1H("Bugatti", "Veyron", 6000, 700, 95, 0.5F, "Duro", 400);
        createC1H("Ferrari", "Aperta", 6000, 600, 95, 0.5F, "Macio", 500);
// C2: createC2(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float pac, String tipoPneus, int a_mecanica)
        createC2("Pagani", "Huarya", 4000, 500, 75, 0.5F, "Duro", 5);
        createC2("Koenigsegg", "Regera", 4000, 400, 75, 0.5F, "Macio", 5);
// C2H: createC2H(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float pac, String tipo_de_pneus, int afinacao_mecanica, int potencia_motor_eletrico)
        createC2H("Zenvo", "TS1", 4000, 500, 75, 0.5F, "Duro", 5, 400);
        createC2H("Arash", "AF10", 4000, 400, 75, 0.5F, "Macio", 5, 500);
        // GT: createGT(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float pac, String tipoPneus)
        createGT("Bentley", "Continental", 3000, 300, 80, 0.5F, "Macio");
        createGT("Lexus", "LC500", 3000, 250, 80, 0.5F, "Duro");
// GTH: createGTH(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float pac, String tipoPneus, int potencia_motor_eletrico)
        createGTH("Mercedes", "AMG", 3000, 300, 80, 0.5F, "Macio", 250);
        createGTH("Porsche", "Taycan", 3000, 250, 80, 0.5F, "Duro", 300);
// SC: createSC(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, float pac, String tipoPneus)
        this.createSC("Subaru", "Impreza", 2500, 200, 75, 0.5F, "Macio");
        this.createSC("Mazda", "RX-7", 2500, 190, 75, 0.5F, "Duro");
        this.createSC("BMW", "E36", 2500, 180, 75, 0.5F, "Macio");
        this.createSC("Ford", "Mustang", 2500, 170, 75, 0.5F, "Duro");
// Piloto: addPiloto(String nome, int cts, int sva)
        this.addPiloto("Lewis Hamilton", 0.5F, 0.5F);
        this.addPiloto("Michael Schumacher", 0.3F, 0.7F);
        this.addPiloto("Johnny Sins", 0, 1);
        this.addPiloto("Fernando Alonso,", 0.3F, 0.8F);
        this.addPiloto("Philippe Adams", (float)0.9, 0.4F);
        // Circuito: createCircuito(int voltas, String ncirc, String local, float dist, List<Integer> curvasList, List<Integer> retaslist, List<Integer> chicaneList, float temperatura, int humidade, float temperatura_asf, String estado_climaterico, int DRS)

        ArrayList<Integer> curvasList1 = new ArrayList<Integer>();
        curvasList1.add(2);
        curvasList1.add(9);
        curvasList1.add(5);

        ArrayList<Integer> curvasList2 = new ArrayList<Integer>();
        curvasList2.add(7);
        curvasList2.add(10);
        curvasList2.add(1);

        ArrayList<Integer> curvasList3 = new ArrayList<Integer>();
        curvasList3.add(5);
        curvasList3.add(3);
        curvasList3.add(8);

        ArrayList<Integer> curvasList4 = new ArrayList<Integer>();
        curvasList4.add(7);
        curvasList4.add(4);
        curvasList4.add(3);

        ArrayList<Integer> retasList3 = new ArrayList<Integer>();
        retasList3.add(5);
        retasList3.add(3);
        retasList3.add(8);

        ArrayList<Integer> retasList4 = new ArrayList<Integer>();
        retasList4.add(7);
        retasList4.add(4);
        retasList4.add(3);

        ArrayList<Integer> retasList1 = new ArrayList<Integer>();
        retasList1.add(2);
        retasList1.add(9);
        retasList1.add(5);

        ArrayList<Integer> retasList2 = new ArrayList<Integer>();
        retasList2.add(7);
        retasList2.add(10);
        retasList2.add(1);

        ArrayList<Integer> chicaneList1 = new ArrayList<Integer>();
        chicaneList1.add(2);
        chicaneList1.add(9);
        chicaneList1.add(5);

        ArrayList<Integer> chicaneList2 = new ArrayList<Integer>();
        chicaneList2.add(7);
        chicaneList2.add(10);
        chicaneList2.add(1);

        ArrayList<Integer> chicaneList4 = new ArrayList<Integer>();
        chicaneList4.add(7);
        chicaneList4.add(4);
        chicaneList4.add(3);

        ArrayList<Integer> chicaneList3 = new ArrayList<Integer>();
        chicaneList3.add(5);
        chicaneList3.add(3);
        chicaneList3.add(8);

        this.createCicruito(10, "Circuito de Aintree", "Reino Unido", 4.828F, curvasList1, retasList1, chicaneList1, 15, 75, 20, "Chuva fraca", 10);
        this.createCicruito(5, "Circuito de Albert Park", "Australia", 5.303F, curvasList2, retasList2, chicaneList2, 25, 10, 30, "Ceu limpo", 10);
        this.createCicruito(15, "Autodromo internacional do Algarve", "Portugal", 4.692F, curvasList3, retasList3, chicaneList3, 20, 20, 25, "Ceu nublado", 10);
        this.createCicruito(20, "Circuito das Americas", "Estados Unidos", 5.513F, curvasList4, retasList4, chicaneList4, 15, 10, 20, "Ceu limpo", 10);

// Registo: addRegisto(String cJog, String cPl, String cCar, String cCamp)

        this.addRegisto("1", "1", "1", "3");
        this.addRegisto("2", "2", "2", "3");
        this.addRegisto("3", "3", "3", "3");
        this.addRegisto("4", "4", "4", "3");
        this.addRegisto("5", "5", "5", "3");

// Admin: createAdmin(String name, String contacto, String email, String username, String password)

        this.createAdmin("Admin", "910 000 000", "admin@f1manager.com", "Adm1n", "admin123");

    }
    public int sizepil(){
        return pil.sizepil();
    }
    public int sizejog(){
        return users.sizejog();
    }
    public int sizecirc(){
        return circ.sizecirc();
    }
    public int sizecar(){
        return carr.size();
    }
    public int sizecamp(){
        return camp.sizecamp();
    }

}

