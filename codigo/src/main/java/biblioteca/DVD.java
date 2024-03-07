package biblioteca;

// Classe DVD
public class DVD extends Item implements Emprestavel {
    public DVD(String titulo, String autor, int ano) {
        super(titulo, autor, ano);
    }

    public boolean podeSerEmprestado() {
        return true;
    }
}