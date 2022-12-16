package users;

/**
 * Classe responsável pelas credenciais dos utilizadores
 */
public class Credenciais {

    private String username;
    private String password;

    /**
     * Contrutor da classe
     * @param username nome do usuário
     * @param password password do usuário
     */
    public Credenciais(String username, String password) {

        this.username = new String(username);
        this.password = new String(password);
    }

    /**
     * Método que altera a password
     * @param newpass nova password
     */
    public void changepassword(String newpass) {

        this.password = new String(newpass);
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
