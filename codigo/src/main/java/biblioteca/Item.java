package biblioteca;

//import java.util.*;

// Classe Item
public abstract class Item {
    String titulo;
    String autor;
    int ano;
    boolean disponivel;
    int vezesEmprestado;

    public Item(String titulo, String autor, int ano) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.disponivel = true;
        this.vezesEmprestado = 0;
    }

    public String getTipo() {
        return this.getClass().getSimpleName();
    }

    public void emprestar() throws EmprestimoException {
        if (this.disponivel) {
            this.disponivel = false;
            this.vezesEmprestado++;
        } else {
            throw new EmprestimoException("O item não está disponível para empréstimo.");
        }
    }

    public void devolver() {
        this.disponivel = true;
    }
}
