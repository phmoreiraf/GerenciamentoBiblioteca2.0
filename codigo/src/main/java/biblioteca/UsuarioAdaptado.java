package biblioteca;

import java.util.List;

// Classe UsuarioAdaptado
public class UsuarioAdaptado extends Usuario {
    private String curso;
    private List<String> categoriasDeInteresse;

    public UsuarioAdaptado(String nome, int cpf, String curso, List<String> categoriasDeInteresse) {
        super(nome, cpf);
        this.curso = curso;
        this.categoriasDeInteresse = categoriasDeInteresse;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public List<String> getCategoriasDeInteresse() {
        return categoriasDeInteresse;
    }

    public void setCategoriasDeInteresse(List<String> categoriasDeInteresse) {
        this.categoriasDeInteresse = categoriasDeInteresse;
    }

}