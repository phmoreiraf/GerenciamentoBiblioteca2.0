package biblioteca;

// Classe EmprestimoException
public class EmprestimoException extends Exception {
    public EmprestimoException(String message) {
        super("Erro de empréstimo: " + message);
    }
}