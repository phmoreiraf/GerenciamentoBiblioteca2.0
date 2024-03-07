package biblioteca;

import java.util.*;

// Classe Usuario
class Usuario {
    private int id = 0;
    private static int proxID = 0;
    public int cpf;
    public String nome;
    List<Item> emprestimos = new ArrayList<>();
    private Set<String> categoriasDeInteresse = new HashSet<>();

    public Usuario(String nome, int cpf) {
        this.id = proxID++;
        this.nome = nome;
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public boolean podeEmprestar() {
        return emprestimos.size() < 3;
    }
    
    public void adicionarCategoriaDeInteresse(String categoria) {
        this.categoriasDeInteresse.add(categoria);
    }

    public void emprestarItem(Item item) throws EmprestimoException {
        if (this.podeEmprestar() && !this.emprestimos.contains(item)) {
            item.emprestar();
            this.emprestimos.add(item);
        } else {
            throw new EmprestimoException("Não foi possível emprestar o item.");
        }
    }

    // Adicionar um novo método na classe Usuario
    public List<Item> recomendarItens() {
        List<Item> recomendacoes = new ArrayList<>();
        for (Item item : Biblioteca.getInstance().listarItens()) {
            if (this.categoriasDeInteresse.contains(item.getTipo())) {
                recomendacoes.add(item);
            }
        }
        return recomendacoes;
    }

}
