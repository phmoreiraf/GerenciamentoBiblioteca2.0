package biblioteca;

// Classe Revista
public class Revista extends Item {
    public Revista(String titulo, String autor, int ano) {
        super(titulo, autor, ano);
    }

    public boolean podeSerEmprestado() {
        return false;
    }
}