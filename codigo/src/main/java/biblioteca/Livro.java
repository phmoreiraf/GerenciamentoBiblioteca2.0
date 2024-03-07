package biblioteca;

// Classe Livro
public class Livro extends Item implements Emprestavel {
    public Livro(String titulo, String autor, int ano) {
        super(titulo, autor, ano);
    }

    public boolean podeSerEmprestado() {
        return true;
    }
}
