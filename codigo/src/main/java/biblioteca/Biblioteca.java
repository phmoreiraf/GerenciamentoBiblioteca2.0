package biblioteca;

import java.util.*;

// Classe Biblioteca
public class Biblioteca {
    private static Biblioteca instance = null;
    Map<String, Item> itens = new HashMap<>();
    Map<String, Usuario> usuarios = new HashMap<>();

    private Biblioteca() {
    }

    public static Biblioteca getInstance() {
        if (instance == null) {
            instance = new Biblioteca();
        }
        return instance;
    }

    public void adicionarItem(Item item) {
        itens.put(item.titulo, item);
    }

    public void removerItem(String titulo) {
        itens.remove(titulo);
    }

    public void editarItem(String titulo, Item novoItem) {
        removerItem(titulo);
        adicionarItem(novoItem);
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.put(usuario.nome, usuario);
    }

    public void removerUsuario(String nome) {
        usuarios.remove(nome);
    }

    public void editarUsuario(String nome, Usuario novoUsuario) {
        removerUsuario(nome);
        adicionarUsuario(novoUsuario);
    }

    public boolean emprestarItem(String titulo, String nomeUsuario) throws Exception {
        Item item = itens.get(titulo);
        Usuario usuario = usuarios.get(nomeUsuario);
        if (item != null && usuario != null && item instanceof Emprestavel && item.disponivel
                && usuario.podeEmprestar()) {
            item.disponivel = false;
            item.vezesEmprestado++;
            usuario.emprestimos.add(item);
            return true;
        }
        throw new Exception("The item is not available for borrowing.");
    }

    public void devolverItem(String titulo, String nomeUsuario) throws Exception {
        Item item = itens.get(titulo);
        Usuario usuario = usuarios.get(nomeUsuario);
        if (item != null && usuario != null && usuario.emprestimos.contains(item)) {
            item.devolver();
            usuario.emprestimos.remove(item);
        } else {
            throw new Exception("It was not possible to return the item.");
        }
    }

    public List<Item> listarItens() {
        List<Item> listaItens = new ArrayList<>(itens.values());
        listaItens.sort(Comparator.comparing(item -> item.titulo));
        return listaItens;
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> listaUsuarios = new ArrayList<>(usuarios.values());
        listaUsuarios.sort(Comparator.comparing(usuario -> usuario.nome));
        return listaUsuarios;
    }

    public List<Item> pesquisarItens(String termo, String tipoPesquisa) {
        List<Item> resultados = new ArrayList<>();
        for (Item item : itens.values()) {
            switch (tipoPesquisa) {
                case "titulo":
                    if (item.titulo.contains(termo)) {
                        resultados.add(item);
                    }
                    break;
                case "autor":
                    if (item.autor.contains(termo)) {
                        resultados.add(item);
                    }
                    break;
                case "ano":
                    if (Integer.toString(item.ano).equals(termo)) {
                        resultados.add(item);
                    }
                    break;
                case "tipo":
                    if (item.getTipo().equals(termo)) {
                        resultados.add(item);
                    }
                    break;
            }
        }
        resultados.sort(Comparator.comparing(item -> item.titulo));
        return resultados;
    }

    public void emitirRelatorioPorItem() {
        List<Item> listaItens = new ArrayList<>(itens.values());
        listaItens.sort(Comparator.comparing(item -> item.ano));
        for (Item item : listaItens) {
            System.out.println(item.titulo + " - " + item.ano + " - " + item.vezesEmprestado + " times borrowed");
        }
    }

    public void emitirRelatorioPorUsuario(String nomeUsuario) {
        Usuario usuario = usuarios.get(nomeUsuario);
        if (usuario != null) {
            List<Item> emprestimos = new ArrayList<>(usuario.emprestimos);
            emprestimos.sort(Comparator.comparing(item -> item.titulo));
            for (Item item : emprestimos) {
                System.out.println(item.titulo);
            }
        }
    }
}