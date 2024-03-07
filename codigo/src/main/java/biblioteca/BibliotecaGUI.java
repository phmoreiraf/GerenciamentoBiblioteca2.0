package biblioteca;

import biblioteca.Item;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BibliotecaGUI {
        private final Biblioteca biblioteca;
        private final ItemFactory itemFactory;
        private JFrame frame;
        private JTextField nomeUsuarioField;

        public BibliotecaGUI(Biblioteca biblioteca, ItemFactory itemFactory) {
            this.biblioteca = biblioteca;
            this.itemFactory = itemFactory;
            initialize();
        }

        private void initialize() {
            frame = new JFrame("Biblioteca");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            frame.getContentPane().add(panel, BorderLayout.CENTER);
            placeComponents(panel);

            frame.setSize(400, 200);
            frame.setVisible(true);
        }

        private void placeComponents(JPanel panel) {
            // ... (código anterior)

            JButton recomendarButton = new JButton("Recomendar Itens");
            recomendarButton.setBounds(10, 80, 150, 25);
            panel.add(recomendarButton);

            // Adiciona um ouvinte de ação ao botão "Recomendar Itens"
            recomendarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    recomendarButtonClicked();
                }
            });
        }

        private void recomendarButtonClicked() {
            String nomeUsuario = nomeUsuarioField.getText();
            if (nomeUsuario == null || nomeUsuario.trim().isEmpty()) {
                mostrarMensagem("Digite o nome do usuário.");
                return;
            }

            Usuario usuario = encontrarUsuarioPorNome(biblioteca.listarUsuarios(), nomeUsuario);

            if (usuario != null) {
                List<Item> recomendacoes = usuario.recomendarItens();
                mostrarRecomendacoes(recomendacoes);
            } else {
                mostrarMensagem("Usuário não encontrado.");
            }
        }

    private Usuario encontrarUsuarioPorNome(List<Usuario> usuarios, String nomeUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equals(nomeUsuario)) {
                return usuario;
            }
        }
        return null;
    }

    private void mostrarRecomendacoes(List<Item> recomendacoes) {
        StringBuilder recomendacoesTexto = new StringBuilder("Itens Recomendados:\n");
        for (Item item : recomendacoes) {
            recomendacoesTexto.append(item.titulo).append(" - ").append(item.autor).append(" - ").append(item.ano)
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, recomendacoesTexto.toString());
    }

    private void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem);
    }

    public void mostrarJanelaRecomendacao() {
        frame.setVisible(true);
    }
}