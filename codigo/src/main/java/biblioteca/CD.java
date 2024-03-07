package biblioteca;

// Classe CD
public class CD extends Item implements Emprestavel {
    public CD(String titulo, String autor, int ano) {
        super(titulo, autor, ano);
    }

    public boolean podeSerEmprestado() {
        return true;
    }
}