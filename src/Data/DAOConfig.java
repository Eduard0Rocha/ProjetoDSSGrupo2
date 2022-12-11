/*
 *  DISCLAIMER: Este cÃ³digo foi criado para discussÃ£o e ediÃ§Ã£o durante as aulas prÃ¡ticas de DSS, representando
 *  uma soluÃ§Ã£o em construÃ§Ã£o. Como tal, nÃ£o deverÃ¡ ser visto como uma soluÃ§Ã£o canÃ³nica, ou mesmo acabada.
 *  Ã‰ disponibilizado para auxiliar o processo de estudo. Os alunos sÃ£o encorajados a testar adequadamente o
 *  cÃ³digo fornecido e a procurar soluÃ§Ãµes alternativas, Ã  medida que forem adquirindo mais conhecimentos.
 */
package Data;

public class DAOConfig {
    static final String USERNAME = "admin";                       // Actualizar
    static final String PASSWORD = "SportingB16b";                       // Actualizar
    private static final String DATABASE = "f1manager";          // Actualizar
    //private static final String DRIVER = "jdbc:mariadb";        // Usar para MariaDB
    private static final String DRIVER = "jdbc:mysql";        // Usar para MySQL
    static final String URL = DRIVER+"://127.0.0.1:4000/"+DATABASE + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
}
