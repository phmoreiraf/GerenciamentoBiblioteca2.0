package biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Biblioteca biblioteca = Biblioteca.getInstance();
        ItemFactory itemFactory = new ItemFactory();
        BibliotecaGUI bibliotecaGUI = new BibliotecaGUI(biblioteca, itemFactory);

        while (true) {
            try {
                System.out.println("1. Adicionar usuário");
                System.out.println("2. Remover usuário");
                System.out.println("3. Editar usuário");
                System.out.println("4. Adicionar item");
                System.out.println("5. Remover item");
                System.out.println("6. Editar item");
                System.out.println("7. Emprestar item");
                System.out.println("8. Devolver item");
                System.out.println("9. Listar itens");
                System.out.println("10. Listar usuários");
                System.out.println("11. Pesquisar itens");
                System.out.println("12. Emitir relatório por item");
                System.out.println("13. Emitir relatório por usuário");
                System.out.println("14. Recomendar itens");
                System.out.println("0. Sair");

                System.out.println("Escolha uma opção:");
                int opcao = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Biblioteca",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                        new Object[]{"Adicionar usuário", "Remover usuário", "Editar usuário", "Adicionar item",
                                "Remover item", "Editar item", "Emprestar item", "Devolver item", "Listar itens",
                                "Listar usuários", "Pesquisar itens", "Emitir relatório por item",
                                "Emitir relatório por usuário", "Recomendar itens", "Sair"},
                        "Sair");

                switch (opcao) {
                    case 0:
                        adicionarUsuario(biblioteca);
                        break;
                    case 1:
                        removerUsuario(biblioteca);
                        break;
                    case 2:
                        editarUsuario(biblioteca);
                        break;
                    case 3:
                        adicionarItem(biblioteca, itemFactory);
                        break;
                    case 4:
                        removerItem(biblioteca);
                        break;
                    case 5:
                        editarItem(biblioteca, itemFactory);
                        break;
                    case 6:
                        emprestarItem(biblioteca);
                        break;
                    case 7:
                        devolverItem(biblioteca);
                        break;
                    case 8:
                        listarItens(biblioteca);
                        break;
                    case 9:
                        listarUsuarios(biblioteca);
                        break;
                    case 10:
                        pesquisarItens(biblioteca);
                        break;
                    case 11:
                        emitirRelatorioPorItem(biblioteca);
                        break;
                    case 12:
                        emitirRelatorioPorUsuario(biblioteca);
                        break;
                    case 13:
                        recomendarItens(biblioteca, bibliotecaGUI);
                        break;
                    case 14:
                        sair();
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, tente novamente.");
                        break;
                }
            } catch (EmprestimoException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void adicionarUsuario(Biblioteca biblioteca) {
        try {
            String nome = JOptionPane.showInputDialog("Digite o nome do usuário:");
            if (nome == null || nome.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome do usuário não pode ser vazio");
            }

            int cpf = Integer.parseInt(JOptionPane.showInputDialog("Digite o CPF do usuário:"));
            if (!validarCpf(cpf)) {
                throw new IllegalArgumentException("CPF inválido");
            }

            biblioteca.adicionarUsuario(new Usuario(nome, cpf));
        } catch (NumberFormatException e) {
            mostrarMensagem("CPF deve ser um número inteiro.");
        } catch (IllegalArgumentException e) {
            mostrarMensagem(e.getMessage());
        }
    }

    private static void removerUsuario(Biblioteca biblioteca) {
        String nome = JOptionPane.showInputDialog("Digite o nome do usuário:");
        biblioteca.removerUsuario(nome);
    }

    private static void editarUsuario(Biblioteca biblioteca) {
        String nome = JOptionPane.showInputDialog("Digite o nome do usuário:");
        String novoNome = JOptionPane.showInputDialog("Digite o novo nome do usuário:");
        int cpf = Integer.parseInt(JOptionPane.showInputDialog("Digite o CPF do usuário:"));
        biblioteca.editarUsuario(nome, new Usuario(novoNome, cpf));
    }

    private static void adicionarItem(Biblioteca biblioteca, ItemFactory itemFactory) {
        int selecaoTipo = Integer.parseInt(JOptionPane.showInputDialog(
                "Selecione qual tipo de item deseja cadastrar: (1 - Livro, 2 - Tese, 3 - Revista, 4 - CD, 5 - DVD)"));
        String titulo = JOptionPane.showInputDialog("Digite o título do item:");
        String autor = JOptionPane.showInputDialog("Digite o autor do item:");
        int ano = Integer.parseInt(JOptionPane.showInputDialog("Digite o ano do item:"));
        biblioteca.adicionarItem(itemFactory.criarItem(selecaoTipo, titulo, autor, ano));
    }

    private static void removerItem(Biblioteca biblioteca) {
        String titulo = JOptionPane.showInputDialog("Digite o título do item:");
        biblioteca.removerItem(titulo);
    }

    private static void editarItem(Biblioteca biblioteca, ItemFactory itemFactory) {
        String titulo = JOptionPane.showInputDialog("Digite o título do item:");
        String novoTitulo = JOptionPane.showInputDialog("Digite o novo título do item:");
        String novoAutor = JOptionPane.showInputDialog("Digite o novo autor do item:");
        int novoAno = Integer.parseInt(JOptionPane.showInputDialog("Digite o novo ano do item:"));
        int selecaoTipo = Integer.parseInt(JOptionPane.showInputDialog(
                "Selecione qual tipo de item deseja cadastrar: (1 - Livro, 2 - Tese, 3 - Revista, 4 - CD, 5 - DVD)"));
        biblioteca.editarItem(titulo, itemFactory.criarItem(selecaoTipo, novoTitulo, novoAutor, novoAno));
    }

    private static void emprestarItem(Biblioteca biblioteca) throws Exception {
        String titulo = JOptionPane.showInputDialog("Digite o título do item:");
        String nomeUsuario = JOptionPane.showInputDialog("Digite o nome do usuário:");
        biblioteca.emprestarItem(titulo, nomeUsuario);
        JOptionPane.showMessageDialog(null, "Item emprestado com sucesso!");
    }

    private static void devolverItem(Biblioteca biblioteca) throws Exception {
        String titulo = JOptionPane.showInputDialog("Digite o título do item:");
        String nomeUsuario = JOptionPane.showInputDialog("Digite o nome do usuário:");
        biblioteca.devolverItem(titulo, nomeUsuario);
        JOptionPane.showMessageDialog(null, "Item devolvido com sucesso!");
    }

    private static void listarItens(Biblioteca biblioteca) {
        List<Item> listaItens = biblioteca.listarItens();
        StringBuilder itens = new StringBuilder("Lista de Itens:\n");
        for (Item item : listaItens) {
            itens.append(item.titulo).append(" - ").append(item.autor).append(" - ").append(item.ano).append("\n");
        }
        JOptionPane.showMessageDialog(null, itens.toString());
    }

    private static void listarUsuarios(Biblioteca biblioteca) {
        List<Usuario> listaUsuarios = biblioteca.listarUsuarios();
        StringBuilder usuarios = new StringBuilder("Lista de Usuários:\n");
        for (Usuario usuario : listaUsuarios) {
            usuarios.append(usuario.nome).append("\n");
        }
        JOptionPane.showMessageDialog(null, usuarios.toString());
    }

    private static void pesquisarItens(Biblioteca biblioteca) {
        String termo = JOptionPane.showInputDialog("Digite o termo de pesquisa:");
        String tipoPesquisa = JOptionPane.showInputDialog("Digite o tipo de pesquisa (titulo, autor, ano, tipo):");
        List<Item> resultados = biblioteca.pesquisarItens(termo, tipoPesquisa);
        StringBuilder itensEncontrados = new StringBuilder("Resultados da Pesquisa:\n");
        for (Item item : resultados) {
            itensEncontrados.append(item.titulo).append(" - ").append(item.autor).append(" - ").append(item.ano)
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, itensEncontrados.toString());
    }

    private static void emitirRelatorioPorItem(Biblioteca biblioteca) {
        biblioteca.emitirRelatorioPorItem();
    }

    private static void emitirRelatorioPorUsuario(Biblioteca biblioteca) {
        String nomeUsuario = JOptionPane.showInputDialog("Digite o nome do usuário:");
        biblioteca.emitirRelatorioPorUsuario(nomeUsuario);
    }

    private static boolean validarCpf(int cpf) {
        String cpfStr = String.valueOf(cpf);
    
        // CPF deve ter 11 dígitos
        if (cpfStr.length() != 11) {
            return false;
        }
    
        // Verifica se todos os dígitos são iguais (caso especial)
        boolean todosDigitosIguais = cpfStr.matches("(\\d)\\1{10}");
        if (todosDigitosIguais) {
            return false;
        }
    
        // Calcula o primeiro dígito verificador
        int digito1 = calcularDigitoVerificador(cpfStr.substring(0, 9));
    
        // Calcula o segundo dígito verificador
        int digito2 = calcularDigitoVerificador(cpfStr.substring(0, 9) + digito1);
    
        // Verifica se os dígitos verificadores calculados coincidem com os fornecidos
        return cpfStr.endsWith(String.valueOf(digito1) + String.valueOf(digito2));
    }
    
    private static int calcularDigitoVerificador(String parteCpf) {
        int soma = 0;
        int peso = parteCpf.length() + 1;
    
        for (int i = 0; i < parteCpf.length(); i++) {
            soma += Character.getNumericValue(parteCpf.charAt(i)) * peso--;
        }
    
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }

    private static void recomendarItens(Biblioteca biblioteca, BibliotecaGUI bibliotecaGUI) {
        bibliotecaGUI.mostrarJanelaRecomendacao();
    }

    private static void sair() {
        mostrarMensagem("Saindo...");
        System.exit(0);
    }

    private static void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem);
    }

}