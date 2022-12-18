import carro.*;
import Data.*;
import circuito.*;
import circuito.Exceptions.NonExistantKey;
import  piloto.*;
import users.UserFacade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main  {

     private static Scanner ler = new Scanner(System.in);
     private static CarroDAO a = new CarroDAO();
    private static PilotoFacade pil;
    private static  CircuitosFacade circ;

    private static UserFacade users;

    static {
        try {
            pil = new PilotoFacade();
            circ=new CircuitosFacade();
            users = new UserFacade();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void menuadmin() throws SQLException,NonExistantKey {

        System.out.println("---------------TP-DSS-GRUPO2-FASE3--------------------");
        System.out.println("|Insira as operações que pretende realizar  :        |");
        System.out.println("|1-> Operações relativas a circuitos                 |");
        System.out.println("|2-> Operações relativas a pilotos                   |");
        System.out.println("|3-> Operações relativas a Jogadores                 |");
        System.out.println("|4-> Operações relativas a Campeonatos               |");
        System.out.println("|                                                    |");
        System.out.println("|Outro-> Sair                                        |");
        System.out.println("-----------------------------------------------------|");
        int p=ler.nextInt();
        ler.nextLine();
        if (p==1){
            menuCircuitos();
        }
        if (p==2)
        {
         menuPilotos();
        }
        else return;
    }

    public  static void menuPilotos() throws SQLException, NonExistantKey {
        System.out.println("---------------Menu-Pilotos---------------------------");
        System.out.println("|Insira as operações que pretende realizar  :        |");
        System.out.println("|1-> Adicionar um piloto ao Programa                 |");
        System.out.println("|2-> Obter a lista de pilotos disponívei             |");
        System.out.println("|3-> Remover um piloto                               |");
        System.out.println("|                                                    |");
        System.out.println("|Outro -> Voltar                                     |");
        System.out.println("-----------------------------------------------------|");
        int p=ler.nextInt();
        ler.nextLine();
        if (p==1){
            System.out.println("Insira o nome do Piloto a adicionar : ");
            String nome=ler.nextLine();
            System.out.println("Insira o  grau CTS do piloto a adicionar : ");
            int cts=ler.nextInt();
            ler.nextLine();
            System.out.println("Insira o grau SVA do Piloto a adicionar : ");
            int sva=ler.nextInt();
            ler.nextLine();
            pil.addPiloto(nome,cts,sva);
        }
        if (p==2){
            System.out.println(pil.getPilotos().values().toString());
            menuPilotos();
        }
        if (p==3){
            System.out.println("Insira o código do piloto que pretende remover");
            String codigo=ler.nextLine();
            pil.removePiloto(codigo);
            menuPilotos();
        }
       else menuadmin();


    }

    public static int menuLogin() throws SQLException, NonExistantKey{
        System.out.println("---------------TP-DSS-GRUPO2-FASE3--------------------");
        System.out.println("|Como pretende efetuar login                         |");
        System.out.println("|1-> Jogador                                         |");
        System.out.println("|2-> Admin                                           |");
        System.out.println("|3-> Guest                                           |");
        System.out.println("------------------------------------------------------");
        int p=ler.nextInt();

        String username;
        String np;

        switch (p){
            case 1:
                System.out.println("---------------TP-DSS-GRUPO2-FASE3--------------------");
                System.out.println("|Insira Username                                     |");
                System.out.println("------------------------------------------------------");

                username=ler.nextLine();

                np = JogadorAutenticadoDAO.search_password(username);

                if(np.equals("NOT FOUND")){
                    System.out.println("---------------TP-DSS-GRUPO2-FASE3--------------------");
                    System.out.println("|User not found                                      |");
                    System.out.println("------------------------------------------------------");

                    return -1;
                }

                else{
                    System.out.println("---------------TP-DSS-GRUPO2-FASE3--------------------");
                    System.out.println("|Insira Password                                     |");
                    System.out.println("------------------------------------------------------");

                    String password=ler.nextLine();

                    if(password.equals(np)){
                        return 0;
                    }
                }
                return 1;


            case 2:
                System.out.println("---------------TP-DSS-GRUPO2-FASE3--------------------");
                System.out.println("|Insira Username                                     |");
                System.out.println("------------------------------------------------------");

                username=ler.nextLine();

                np = AdminDAO.search_password(username);

                if(np.equals("NOT FOUND")){
                    System.out.println("---------------TP-DSS-GRUPO2-FASE3--------------------");
                    System.out.println("|User not found                                      |");
                    System.out.println("------------------------------------------------------");

                    return -1;
                }

                else{
                    System.out.println("---------------TP-DSS-GRUPO2-FASE3--------------------");
                    System.out.println("|Insira Password                                     |");
                    System.out.println("------------------------------------------------------");

                    String password=ler.nextLine();

                    if(password.equals(np)){
                        return 0;
                    }
                }
                return 1;

            case 3:
                // Guest
                return 0;

            default:
                return 1;

        }


    }
    public static void menuCircuitos() throws SQLException, NonExistantKey {

        System.out.println("---------------TP-DSS-GRUPO2-FASE3--------------------");
        System.out.println("|Insira as operações que pretende realizar  :        |");
        System.out.println("|1-> Adicionar Ciruito                               |");
        System.out.println("|2-> Obter lista de Circuitos                        |");
        System.out.println("|3-> Remover Circuito                                |");
        System.out.println("|                                                    |");
        System.out.println("|Outro -> voltar                                     |");
        System.out.println("-----------------------------------------------------|");
        int p=ler.nextInt();
        ler.nextLine();
        if (p==1){
            System.out.println("Insira o nome do Circuito a adicionar : ");
            String ncirc=ler.nextLine();
            System.out.println("Insira o local  do Circuito a adicionar : ");
            String local=ler.nextLine();
            System.out.println("Insira a distancia  do Circuito a adicionar : ");
            float dist=ler.nextFloat();
            ler.nextLine();
            System.out.println("Insira o numero de voltas do Circuito a adicionar : ");
            int voltas =ler.nextInt();
            ler.nextLine();
            System.out.println("Insira o numero de retas : ");
            int retas =ler.nextInt();
            ler.nextLine();
            List<Integer> retaslist= new ArrayList<>();
            for (int i=0;i<retas;i++){
                System.out.println("Insira o GDU da reta : "+i);

                retaslist.add( ler.nextInt());
                ler.nextLine();
            }
            System.out.println("Insira o numero de curvas : ");
            int curvas =ler.nextInt();
            ler.nextLine();
            List<Integer> curvasList= new ArrayList<>();
            for (int i=0;i<curvas;i++){
                System.out.println("Insira o GDU da curva : "+i);
                curvasList.add( ler.nextInt());
                ler.nextLine();
            }

            System.out.println("Insira o numero de chicanes : ");
            int chicanes =ler.nextInt();
            ler.nextLine();
            List<Integer> chicaneList= new ArrayList<>();
            for (int i=0;i<chicanes;i++){
                System.out.println("Insira o GDU da chicane : "+i);
                chicaneList.add( ler.nextInt());
                ler.nextLine();
            }
            System.out.println("Insira a reta para a qual pretende permitir a utilização do DRS ");
            int DRS =ler.nextInt();
            ler.nextLine();
            System.out.println("Insira a temperatura do local ");
            float temperatura =ler.nextFloat();
            ler.nextLine();
            System.out.println("Insira a humidade do local ");
            int humidade =ler.nextInt();
            ler.nextLine();
            System.out.println("Insira a temperatura do asfalto ");
            float temperatura_asf =ler.nextFloat();
            ler.nextLine();
            System.out.println("Insira o estado climatérico :");
            String  estado_climaterico =ler.nextLine();

            circ.createCicruito(voltas,ncirc,local,dist,curvasList,retaslist,chicaneList,temperatura,humidade,temperatura_asf,estado_climaterico,DRS);

        }
        if (p==2){
            System.out.println(circ.getCircuitos().values());
        }
        if (p==3){
            System.out.println("Insira o codigo do circuito que pretente remover  ");
            String cod =ler.nextLine();
            boolean aux = circ.removeCircuito(cod);
            if (!aux) System.out.println("Circuito nao removido");
        }
        else menuadmin();
    }

    public static void main(String[] args) throws SQLException,NonExistantKey
    {
        menuadmin();
    }
}