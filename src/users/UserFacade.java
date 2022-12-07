package users;

import java.util.HashMap;

public class UserFacade implements SGestaoUser {

    private HashMap<String,Admin> admins;
    private HashMap<String,Jogador> jogadores;

    public UserFacade() {

        this.admins = new HashMap<String,Admin>();
        this.jogadores = new HashMap<String, Jogador>();
    }
}
