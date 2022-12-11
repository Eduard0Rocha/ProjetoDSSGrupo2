import carro.*;
import Data.*;
import circuito.*;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        CarroDAO a = new CarroDAO();
        CircuitoDAO c = new CircuitoDAO();
        System.out.println("Hello world!");
        a.get("10");
        System.out.println(c.get("1").toString());

        System.out.println("---------------TP-DSS-GRUPO2-FASE3-------------------");
        System.out.println("Insira as operações que pretende realizar  : ");
        System.out.println("1-> Adicionar um circuito à BD  : ");
        System.out.println("---------------T-------------------");
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
            System.out.println("Insira a reta para a qual pretende permitir a utilização do DRS ");
            int reta =ler.nextInt();
            ler.nextLine();

            Random ll= new Random();
            String cod =Integer.toString(ll.nextInt());
            Ciruito C = new Ciruito(cod,voltas,ncirc,local,dist,reta);
            c.put(C.getCodCircuito(),C);
        }
    }
}