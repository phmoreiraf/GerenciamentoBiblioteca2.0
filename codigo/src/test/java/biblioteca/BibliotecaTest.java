package biblioteca;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BibliotecaTest {

    @Test
    public void testEmprestarItem() {
        Biblioteca biblioteca = Biblioteca.getInstance();
        Usuario usuario = new Usuario("Pedro", 123456);
        Livro livro = new Livro("Livro 1", "Joao", 1954);
        biblioteca.adicionarUsuario(usuario);
        biblioteca.adicionarItem(livro);

        try {
            assertTrue(biblioteca.emprestarItem("Livro 1", "Pedro"));
        } catch (Exception e) {
            fail("Exceção inesperada: " + e.getMessage());
        }
    }

    @Test
    public void testDevolverItem() {
        Biblioteca biblioteca = Biblioteca.getInstance();
        Usuario usuario = new Usuario("Pedro", 123456);
        Livro livro = new Livro("Livro 1", "Joao", 1954);
        biblioteca.adicionarUsuario(usuario);
        biblioteca.adicionarItem(livro);

        try {
            biblioteca.emprestarItem("Livro 1", "Pedro");
            assertTrue(biblioteca.devolverItem("Livro 1", "Pedro"));
        } catch (Exception e) {
            fail("Exceção inesperada: " + e.getMessage());
        }
    }

    @Test
    public void testAdicionarItem() {
        Biblioteca biblioteca = Biblioteca.getInstance();
        Livro livro = new Livro("Livro 1", "Joao", 1999);
        biblioteca.adicionarItem(livro);
        assertEquals(1, biblioteca.itens.size());
    }

    @Test
    public void testRemoverItem() {
        Biblioteca biblioteca = Biblioteca.getInstance();
        Livro livro = new Livro("Livro 1", "Joao", 1999);
        biblioteca.adicionarItem(livro);
        biblioteca.removerItem("Livro 1");
        assertEquals(0, biblioteca.itens.size());
    }

    @Test
    public void testAdicionarUsuario() {
        Biblioteca biblioteca = Biblioteca.getInstance();
        Usuario usuario = new Usuario("Pedro", 123456);
        biblioteca.adicionarUsuario(usuario);
        assertEquals(1, biblioteca.usuarios.size());
    }

    @Test
    public void testRemoverUsuario() {
        Biblioteca biblioteca = Biblioteca.getInstance();
        Usuario usuario = new Usuario("Pedro", 123456);
        biblioteca.adicionarUsuario(usuario);
        biblioteca.removerUsuario("Pedro");
        assertEquals(0, biblioteca.usuarios.size());
    }

    @Test
    public void testEditarUsuario() {
        Biblioteca biblioteca = Biblioteca.getInstance();
        Usuario usuario = new Usuario("Pedro", 123456);
        biblioteca.adicionarUsuario(usuario);
        Usuario novoUsuario = new Usuario("Mateus", 654321);
        biblioteca.editarUsuario("Pedro", novoUsuario);
        assertEquals("Mateus", biblioteca.usuarios.get("Mateus").nome);
    }

    @Test
    public void testEditarItem() {
        Biblioteca biblioteca = Biblioteca.getInstance();
        Livro livro = new Livro("Livro 1", "Joao", 1999);
        biblioteca.adicionarItem(livro);
        Livro novoLivro = new Livro("Livro 2", "Joao", 1998);
        biblioteca.editarItem("Livro 1", novoLivro);
        assertEquals("Livro 2", biblioteca.itens.get("Livro 2").titulo);
    }

}
