// Arquivo: App.java

import javax.swing.SwingUtilities;

/**
 * Ponto de entrada principal da aplicação.
 * Sua única responsabilidade agora é iniciar a interface gráfica.
 */
public class App {

    public static void main(String[] args) {
        // SwingUtilities.invokeLater garante que a UI seja criada e atualizada
        // na thread correta (Event Dispatch Thread - EDT). Isso é uma boa prática
        // para evitar problemas de concorrência na interface.
        SwingUtilities.invokeLater(() -> {
            JanelaCalculadora janela = new JanelaCalculadora();
            janela.setVisible(true); // Torna a janela visível!
        });
    }
}