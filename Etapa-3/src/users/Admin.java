package src.users;

/**
 * Classe responsável armazenar a informação sobre um admin e operar sobre a informação
 */
public class Admin {

    private String nome;
    private String contactoTLM;
    private String email;
    private String codAdmin;
    private Credenciais credenciais;

    /**
     * Contrutor da classe Admin
     * @param nome Nome do admin
     * @param contactoTLM Contacto telefońico do admin
     * @param email Email do admin
     * @param codAdmin Código identificador do admin
     * @param username Username do admin
     * @param password Password do admin
     */
    public Admin(String nome, String contactoTLM, String email, String codAdmin, String username, String password) {

        this.nome = new String(nome);
        this.contactoTLM = new String(contactoTLM);
        this.email = new String(email);
        this.codAdmin = new String(codAdmin);
        this.credenciais = new Credenciais(username, password);
    }
}
