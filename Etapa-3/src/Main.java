import campeonato.CampeonatosFacade;
import carro.*;
import Data.*;
import circuito.*;
import circuito.Exceptions.NonExistantKey;
import  piloto.*;
import users.UserFacade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main  {

     private static Scanner ler = new Scanner(System.in);
    private static PilotoFacade pil;
    private static  CircuitosFacade circ;

    private static CarrosFacade carr;
    private static UserFacade users;
    private static CampeonatosFacade camp;

    static {
        try {
            pil = new PilotoFacade();
            circ=new CircuitosFacade();
            users = new UserFacade();
            carr = new CarrosFacade();
            camp = new CampeonatosFacade();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NonExistantKey e) {
            throw new RuntimeException(e);
        }
    }

    public static void menuadmin() throws SQLException,NonExistantKey {

        System.out.println("---------------Menu-Admin-----------------------------");
        System.out.println("|Insira as operações que pretende realizar  :        |");
        System.out.println("|1-> Operações relativas a circuitos                 |");
        System.out.println("|2-> Operações relativas a pilotos                   |");
        System.out.println("|3-> Operações relativas a Jogadores                 |");
        System.out.println("|4-> Operações relativas a Campeonatos               |");
        System.out.println("|5-> Operações relativas a Carros                    |");
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
        if (p==4)
        {
            menuCampeonatos();
        }
        if(p==5)
        {
            menuCarros();
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
                if (pil.removePiloto(codigo)) System.out.println("Piloto removido com sucesso");
                else System.out.println("Piloto inexistente");
            menuPilotos();
        }
       else menuadmin();


    }


    public static void imprimeMenuLogin0(){
        System.out.println("---------------TP-DSS-GRUPO2-FASE3--------------------");
        System.out.println("|Bem-Vindo ao F1Manager                              |");
        System.out.println("------------------------------------------------------");
        System.out.println("|Insira a opção que pretende realizar                |");
        System.out.println("|1->Efetuar Login                                    |");
        System.out.println("|2->Registar-me                                      |");
        System.out.println("|3->Obter Lista de Administradores                   |");
        System.out.println("|4->Obter lista de Jogadores                         |");
        System.out.println("------------------------------------------------------");
    }
    public static void imprimeMenuLogin1(){
        System.out.println("---------------TP-DSS-GRUPO2-FASE3--------------------");
        System.out.println("|Como pretende efetuar login                         |");
        System.out.println("|1-> Jogador                                         |");
        System.out.println("|2-> Admin                                           |");
        System.out.println("|3-> Guest                                           |");
        System.out.println("|Outro->Voltar                                       |");
        System.out.println("------------------------------------------------------");
    }

    public static void imprimeMenuRegisto(){
        System.out.println("---------------TP-DSS-GRUPO2-FASE3--------------------");
        System.out.println("|Como pretende Registar-se ?                         |");
        System.out.println("|1-> Jogador                                         |");
        System.out.println("|2-> Admin                                           |");
        System.out.println("|3-> Guest                                           |");
        System.out.println("|Outro-> voltar                                      |");
        System.out.println("------------------------------------------------------");
    }

    public static void imprimeUsernamePrompt(){
        System.out.println("---------------TP-DSS-GRUPO2-FASE3--------------------");
        System.out.println("|Insira Username                                     |");
        System.out.println("------------------------------------------------------");
    }

    public static void imprimeInvalidUserPrompt(){
        System.out.println("---------------TP-DSS-GRUPO2-FASE3--------------------");
        System.out.println("|User not found                                      |");
        System.out.println("------------------------------------------------------");
    }

    public static void imprimeContactoPrompt(){
        System.out.println("---------------TP-DSS-GRUPO2-FASE3--------------------");
        System.out.println("|Insira Contacto De telemóel                         |");
        System.out.println("------------------------------------------------------");
    }

    public static void imprimeemailPrompt(){
        System.out.println("---------------TP-DSS-GRUPO2-FASE3--------------------");
        System.out.println("|Insira email                                        |");
        System.out.println("------------------------------------------------------");
    }


    public static void imprimePasswordPrompt(){
        System.out.println("---------------TP-DSS-GRUPO2-FASE3--------------------");
        System.out.println("|Insira Password                                     |");
        System.out.println("------------------------------------------------------");
    }

    public static void imprimeGuestCreatedPrompt(){
        System.out.println("---------------TP-DSS-GRUPO2-FASE3--------------------");
        System.out.println("|Guest criado com sucesso                            |");
        System.out.println("------------------------------------------------------");
    }

    public static void imprimePlayerCreatedPrompt(){
        System.out.println("---------------TP-DSS-GRUPO2-FASE3--------------------");
        System.out.println("|Jogador criado com sucesso                          |");
        System.out.println("------------------------------------------------------");
    }

    public static void imprimeAdminCreatedPrompt(){
        System.out.println("---------------TP-DSS-GRUPO2-FASE3--------------------");
        System.out.println("|Admin   criado com sucesso                          |");
        System.out.println("------------------------------------------------------");
    }


    public static void imprimeGuestUnsuccessfulPrompt(){
        System.out.println("---------------TP-DSS-GRUPO2-FASE3--------------------");
        System.out.println("|Erro na criação do Guest                            |");
        System.out.println("------------------------------------------------------");
    }

    public static void imprimeJogadorUnsuccessfulPrompt(){
        System.out.println("---------------TP-DSS-GRUPO2-FASE3--------------------");
        System.out.println("|Erro na criação do Jogador                          |");
        System.out.println("------------------------------------------------------");
    }

    public static void imprimeNamePrompt(){
        System.out.println("---------------TP-DSS-GRUPO2-FASE3--------------------");
        System.out.println("|Insira Nome                                         |");
        System.out.println("------------------------------------------------------");
    }

    public static void imprimeMenuPlayer(){
        System.out.println("---------------Menu-Player---------------------------");
        System.out.println("|Insira a opcao que pretende Realizar                 |");
        System.out.println("|1->Entrar num Campeonato                             |");
        System.out.println("|2->Obter lista de Camepeonatos existentes            |");
        System.out.println("|3->Criar Campeonato                                  |");
        System.out.println("|4->Apagar Campeonato                                 |");
        System.out.println("|                                                     |");
        System.out.println("------------------------------------------------------");
        int i = ler.nextInt();
        ler.nextLine();
        if (i==1){

        }
        if (i==2){

        }
        if (i==3){

        }
    }

    public static void  menuLogin() throws IOException, SQLException, NonExistantKey {
        BufferedReader systemIn = new BufferedReader(new InputStreamReader(System.in));
        imprimeMenuLogin0();
        int p=ler.nextInt();
        ler.nextLine();


        String username;
        String np;
        String password;
        boolean valid;
            if (p==1) {// login

                imprimeMenuLogin1();
                int k = ler.nextInt();
                ler.nextLine();
                switch (k) {
                    case 1:
                        imprimeUsernamePrompt();
                        username = systemIn.readLine();
                        if (users.existeUser(username) ){
                        int i = 0;
                        while (i < 3) {
                            imprimePasswordPrompt();
                            password = ler.nextLine();
                            if (users.validUser(username, password)) {
                                System.out.println("Login efetuado com sucesso como jogador");
                                imprimeMenuPlayer();
                                break;
                            }
                            i++;
                            System.out.println("Invalido ");
                        }

                        }
                        else {
                            System.out.println("User Inexistente");
                            menuLogin();
                        }
                        break;

                    case 2:

                        imprimeUsernamePrompt();
                        username = systemIn.readLine();
                        if (users.existeAdmin(username) ){
                            int i = 0;
                            while (i < 3) {
                                imprimePasswordPrompt();
                                password = ler.nextLine();
                                if (users.validPasswordAdmin(username, password)) {
                                    System.out.println("Login efetuado com sucesso como Admin");
                                    menuadmin();
                                    break;
                                }
                                i++;
                                System.out.println("Invalido ");
                            }

                        }
                        else {
                            System.out.println("Admin Inexistente");
                            menuLogin();
                        }
                        break;
                    case 3: // Guest
                        imprimeUsernamePrompt();
                        username = systemIn.readLine();
                       if (users.validGuest(username)){
                          menuGuest();
                       }
                       else {
                           System.out.println("Guest Inexistente");
                           menuLogin();
                       }
                       break;
                    default:
                        break;
                }
            }

            else if (p== 2) {
                imprimeMenuRegisto();
                int leu = ler.nextInt();
                ler.nextLine();
                if (leu == 1) {
                    imprimeUsernamePrompt();
                    username = systemIn.readLine();
                    while (users.containsUNAME(username)){
                        System.out.println("Username Inválido ");
                        imprimeUsernamePrompt();
                        username = systemIn.readLine();
                    }
                    imprimeNamePrompt();
                    String name = systemIn.readLine();

                    imprimePasswordPrompt();
                    password = systemIn.readLine();

                    try {
                        valid = users.createAPlayer(name, username, password);
                    } catch (CloneNotSupportedException e) {
                        throw new RuntimeException(e);
                    }

                    if (valid) {
                        imprimePlayerCreatedPrompt();
                        menuLogin();
                    }
                    else {
                        imprimeJogadorUnsuccessfulPrompt();
                        menuLogin();
                    }
                }
                else if (leu == 2)
                {
                    imprimeUsernamePrompt();
                    username = systemIn.readLine();

                    imprimeNamePrompt();
                    String name = systemIn.readLine();

                    imprimePasswordPrompt();
                    password = systemIn.readLine();

                    imprimeemailPrompt();
                    String email=ler.nextLine();

                    imprimeContactoPrompt();
                    String contacto=ler.nextLine();

                    valid = users.createAdmin(name,contacto,email,username,password);

                    if (valid) {
                        imprimeAdminCreatedPrompt();
                        menuLogin();

                    }
                    else {
                        System.out.println("Admin não criado");
                        menuLogin();
                    }
                }
                else if (leu == 3) {
                    imprimeUsernamePrompt();
                    username = systemIn.readLine();
                    while (users.containsUNAME(username)){
                        System.out.println("Username Inválido");
                        imprimeUsernamePrompt();
                        username = systemIn.readLine();
                    }

                    valid = users.createGuest(username);
                    if (valid) {

                        imprimeGuestCreatedPrompt();
                        menuLogin();
                    }
                    else {
                        imprimeGuestUnsuccessfulPrompt();
                        menuLogin();
                    }
                }
                else menuLogin();

            }
            if (p==3) {
                System.out.println( users.getAdmins());
                menuLogin();
            }
            if (p==4){
                System.out.println(users.getPlayers());
                menuLogin();
            }
            else menuLogin();
    }

    public static void  menuCarros () throws SQLException,NonExistantKey {
        System.out.println("---------------Menu-Carros---------------------------");
        System.out.println("|Insira as operações que pretende realizar  :        |");
        System.out.println("|1-> Adicionar um carro ao Programa                  |");
        System.out.println("|2-> Obter a lista de carros disponíveis             |");
        System.out.println("|3-> Remover um carro                                |");
        System.out.println("|                                                    |");
        System.out.println("|Outro -> Voltar                                     |");
        System.out.println("-----------------------------------------------------|");


        int p = ler.nextInt();
        ler.nextLine();
        if (p == 1)
        {
            System.out.println("Insira a classe do Carro (C1 | C1H | C2 | C2H | GT | GTH | SC )");
            String classe = ler.nextLine();

            if (classe.equals("C1")) {
                System.out.println("Insira a marca do Carro a adicionar : ");
                String marca = ler.nextLine();
                System.out.println("Insira o modelo do Carro a adicionar : ");
                String modelo = ler.nextLine();
                System.out.println("Insira a cilindrada do Carro a adicionar : ");
                int cilindrada = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira a potencia do Carro a adicionar : ");
                int potencia = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira a fiabilidade do Carro a adicionar : ");
                int fiabilidade = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira o PAC do Carro a adicionar : ");
                int pac = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira o tipo de pneus do Carro a adicionar : ");
                String tipoPneus = ler.nextLine();
                carr.createC1(marca, modelo, cilindrada, potencia, fiabilidade, pac, tipoPneus);
                menuCarros();
            }

            if (classe.equals("C1H")) {
                System.out.println("Insira a marca do Carro a adicionar : ");
                String marca = ler.nextLine();
                System.out.println("Insira o modelo do Carro a adicionar : ");
                String modelo = ler.nextLine();
                System.out.println("Insira a cilindrada do Carro a adicionar : ");
                int cilindrada = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira a potencia do Carro a adicionar : ");
                int potencia = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira a fiabilidade do Carro a adicionar : ");
                int fiabilidade = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira o PAC do Carro a adicionar : ");
                int pac = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira o tipo de pneus do Carro a adicionar : ");
                String tipoPneus = ler.nextLine();
                System.out.println("Insira a potencia do motor eletrico do Carro a adicionar : ");
                int potencia_motor_eletrico = ler.nextInt();
                ler.nextLine();
                carr.createC1H(marca, modelo, cilindrada, potencia, fiabilidade, pac, tipoPneus, potencia_motor_eletrico);
                menuCarros();
            }

            if (classe.equals("C2")) {
                System.out.println("Insira a marca do Carro a adicionar : ");
                String marca = ler.nextLine();
                System.out.println("Insira o modelo do Carro a adicionar : ");
                String modelo = ler.nextLine();
                System.out.println("Insira a cilindrada do Carro a adicionar : ");
                int cilindrada = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira a potencia do Carro a adicionar : ");
                int potencia = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira a fiabilidade do Carro a adicionar : ");
                int fiabilidade = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira o PAC do Carro a adicionar : ");
                int pac = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira o tipo de pneus do Carro a adicionar : ");
                String tipoPneus = ler.nextLine();
                System.out.println("Insira a afinacao mecanica do Carro a adicionar : ");
                int afinacao_mecanica = ler.nextInt();
                ler.nextLine();
                carr.createC2(marca, modelo, cilindrada, potencia, fiabilidade, pac, tipoPneus, afinacao_mecanica);
                menuCarros();
            }

            if (classe.equals("C2H")) {
                System.out.println("Insira a marca do Carro a adicionar : ");
                String marca = ler.nextLine();
                System.out.println("Insira o modelo do Carro a adicionar : ");
                String modelo = ler.nextLine();
                System.out.println("Insira a cilindrada do Carro a adicionar : ");
                int cilindrada = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira a potencia do Carro a adicionar : ");
                int potencia = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira a fiabilidade do Carro a adicionar : ");
                int fiabilidade = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira o PAC do Carro a adicionar : ");
                int pac = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira o tipo de pneus do Carro a adicionar : ");
                String tipoPneus = ler.nextLine();
                System.out.println("Insira a afinacao mecanica do Carro a adicionar : ");
                int afinacao_mecanica = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira a potencia do motor eletrico do Carro a adicionar : ");
                int potencia_motor_eletrico = ler.nextInt();
                ler.nextLine();
                carr.createC2H(marca, modelo, cilindrada, potencia, fiabilidade, pac, tipoPneus, afinacao_mecanica, potencia_motor_eletrico);
                menuCarros();
            }

            if (classe.equals("GT")) {
                System.out.println("Insira a marca do Carro a adicionar : ");
                String marca = ler.nextLine();

                System.out.println("Insira o modelo do Carro a adicionar : ");
                String modelo = ler.nextLine();
                System.out.println("Insira a cilindrada do Carro a adicionar : ");
                int cilindrada = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira a potencia do Carro a adicionar : ");
                int potencia = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira a fiabilidade do Carro a adicionar : ");
                int fiabilidade = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira o PAC do Carro a adicionar : ");
                int pac = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira o tipo de pneus do Carro a adicionar : ");
                String tipoPneus = ler.nextLine();

                carr.createGT(marca, modelo, cilindrada, potencia, fiabilidade, pac, tipoPneus);
                menuCarros();
            }

            if (classe.equals("GTH")) {
                System.out.println("Insira a marca do Carro a adicionar : ");
                String marca = ler.nextLine();
                System.out.println("Insira o modelo do Carro a adicionar : ");
                String modelo = ler.nextLine();
                System.out.println("Insira a cilindrada do Carro a adicionar : ");
                int cilindrada = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira a potencia do Carro a adicionar : ");
                int potencia = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira a fiabilidade do Carro a adicionar : ");
                int fiabilidade = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira o PAC do Carro a adicionar : ");
                int pac = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira o tipo de pneus do Carro a adicionar : ");
                String tipoPneus = ler.nextLine();
                System.out.println("Insira a potencia do motor eletrico do Carro a adicionar : ");
                int potencia_motor_eletrico = ler.nextInt();
                ler.nextLine();
                carr.createGTH(marca, modelo, cilindrada, potencia, fiabilidade, pac, tipoPneus, potencia_motor_eletrico);
                menuCarros();
            }

            if (classe.equals("SC")) {
                System.out.println("Insira a marca do Carro a adicionar : ");
                String marca = ler.nextLine();
                System.out.println("Insira o modelo do Carro a adicionar : ");
                String modelo = ler.nextLine();
                System.out.println("Insira a cilindrada do Carro a adicionar : ");
                int cilindrada = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira a potencia do Carro a adicionar : ");
                int potencia = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira a fiabilidade do Carro a adicionar : ");
                int fiabilidade = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira o PAC do Carro a adicionar : ");
                int pac = ler.nextInt();
                ler.nextLine();
                System.out.println("Insira o tipo de pneus do Carro a adicionar : ");
                String tipoPneus = ler.nextLine();
                carr.createSC(marca, modelo, cilindrada, potencia, fiabilidade, pac, tipoPneus);
                menuCarros();
            }
            else System.out.println("Valor inválido");
        }
        if (p==2)
        {
            System.out.println(carr.getCarros());
            menuCarros();
        }
        if (p==3)
        {
            System.out.println("Insira o código do carro que pretende remover");
            String cod = ler.nextLine();

           if ( carr.removeCarro(cod))
               System.out.println("Carro removido com sucesso");
           else System.out.println("Carro inexistente");
           menuCarros();

        }
        else  menuadmin();
    }


    public static void menuGuest() throws SQLException, NonExistantKey {

        System.out.println("---------------Menu-Guest-----------------------------");
        System.out.println("|Insira as operações que pretende realizar  :        |");
        System.out.println("|1-> Entrar num campeonato                           |");
        System.out.println("|2-> Obter lista de Campeonatos                      |");
        System.out.println("|3-> Sair de um campeonato                           |");
        System.out.println("|Outro -> voltar                                     |");
        System.out.println("-----------------------------------------------------|");
        int p = ler.nextInt();
        ler.nextLine();
        if (p == 1) {
            System.out.println("entrar num campeonato");
        } else if (p == 2) {
            System.out.println("obter lista de camponatos");

        } else if (p == 3) {
            System.out.println("sair de um camponato");
        }
    }


    public static void menuCircuitos() throws SQLException, NonExistantKey {

        System.out.println("---------------Menu-Circuitos------------------------");
        System.out.println("|Insira as operações que pretende realizar  :        |");
        System.out.println("|1-> Adicionar Circuito                              |");
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
            menuCircuitos();
        }
        if (p==2){
            System.out.println(circ.getCircuitos().values());
            menuCircuitos();
        }
        if (p==3){
            System.out.println("Insira o codigo do circuito que pretente remover  ");
            String cod =ler.nextLine();
            boolean aux = circ.removeCircuito(cod);
            if (!aux) System.out.println("Circuito inexistente");
            else System.out.println("Circuito removido ");
            menuCircuitos();
        }
        else menuadmin();
    }

    public static void menuCampeonatos() throws SQLException, NonExistantKey {

        System.out.println("---------------Menu-Campeonatos-----------------------");
        System.out.println("|Insira as operações que pretende realizar:          |");
        System.out.println("|1-> Adicionar Campeonato                            |");
        System.out.println("|2-> Obter lista de Campeonatos                      |");
        System.out.println("|3-> Remover Campeonato                              |");
        System.out.println("|4-> Obter lista de Circuitos de um Campeonato       |");
        System.out.println("|5-> Obter lista de Jogadores de um Campeonato       |");
        System.out.println("|6-> Obter Classificações de um Campeonato           |");
        System.out.println("|7-> Adicionar Registo a Campeonato                  |");
        System.out.println("|8-> Adicionar Corrida a Campeonato                  |");
        System.out.println("|                                                    |");
        System.out.println("|Outro -> voltar                                     |");
        System.out.println("-----------------------------------------------------|");
        int p=ler.nextInt();
        ler.nextLine();
        if (p==1){
            System.out.println("Insira o nome do Campeonato a adicionar: ");
            String ncamp=ler.nextLine();
            System.out.println(ncamp);
            camp.createCampeonato(ncamp);
            menuCampeonatos();
        }
        if (p==2){
            System.out.println(camp.getCampeonatos().values());
            menuCampeonatos();
        }
        if (p==3){
            System.out.println("Insira o codigo do campeonato que pretente remover: ");
            String cod =ler.nextLine();
            boolean aux = camp.removeCampeonato(cod);
            if (!aux) System.out.println("Campeonato não existe!");
            else System.out.println("Campeonato removido!");
            menuCampeonatos();
        }
        if (p==4){
            System.out.println("Insira o codigo do campeonato  : ");
            String cod =ler.nextLine();
            System.out.println(camp.getCircuitos(cod));
            menuCampeonatos();
        }
        if (p==5){
            System.out.println("Insira o codigo do campeonato  : ");
            String cod =ler.nextLine();
            System.out.println(camp.getJogadores(cod));
            menuCampeonatos();
        }
        if (p==6){
            System.out.println("Classificação de carros Hibridos?(Sim/Nao): ");
            String ans = ler.nextLine();
            if(ans == "Nao") {
                System.out.println("Insira o codigo do campeonato  : ");
                String cod = ler.nextLine();
                System.out.println(camp.getClassificacaoC(cod));
                menuCampeonatos();
            }else{
                System.out.println("Insira o codigo do campeonato  : ");
                String cod = ler.nextLine();
                System.out.println(camp.getClassificacaoCH(cod));
                menuCampeonatos();
            }
        }
        if (p==7){
            System.out.println("Insira o código do Campeonato onde pertende adiconar o Registo : ");
            String cCamp=ler.nextLine();
            if (camp.existsCampeonato(cCamp)) {
                System.out.println("Insira o código do Jogador a adicionar : ");
                String cJog = ler.nextLine();
                if (users.existeUser(cJog)) {
                    System.out.println("Insira o código do Piloto a adicionar : ");
                    String cPl = ler.nextLine();
                    if (pil.existsPiloto(cPl) ){
                        System.out.println("Insira o código do Carro a adicionar : ");
                        String cCar = ler.nextLine();
                        camp.addRegisto(cJog, cPl, cCar, cCamp);
                    }
                    else {
                        System.out.println("Piloto inexistente");
                        menuCampeonatos();
                    }
                }
                else{
                    System.out.println("User inexistente");
                    menuCampeonatos();
                }
            }
            else{
                    System.out.println("Campeonato inexistente");

                    menuCampeonatos();
                }
            }
        if (p==8){
            System.out.println("Insira o código do Campeonato onde pertende adiconar a Corrida : ");
            String cCamp=ler.nextLine();
            System.out.println("Insira o código do Circuito da Corrida a adicionar : ");
            String cCir=ler.nextLine();
            camp.addCorrida(cCamp, cCir);
            menuCampeonatos();
        }
        else menuadmin();
    }

    public static void main(String[] args) throws SQLException, NonExistantKey, IOException {
        menuLogin();
        }

}