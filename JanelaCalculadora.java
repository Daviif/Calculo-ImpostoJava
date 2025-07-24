// Arquivo: JanelaCalculadora.java

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class JanelaCalculadora extends JFrame {

    private JTextField campoValorBruto;
    private JComboBox<String> seletorTipoOrgao;
    private JCheckBox seletorBradesco;
    private JButton botaoLimpar;
    private JLabel labelValorLiquido;
    private JLabel labelValorIR;
    private JLabel labelValorPIS;
    private JLabel labelValorCOFINS;
    private JLabel labelValorCSLL;
    private JLabel labelAviso;
    private final CalculadoraImpostos calculadora; // Instância da CalculadoraImpostos

    public JanelaCalculadora() {
        setTitle("Calculadora de Impostos");
        setSize(480, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Carregar e configurar o ícone da janela
        URL urlIcone = getClass().getResource("icone.png");
        if (urlIcone != null) {
            setIconImage(new ImageIcon(urlIcone).getImage());
        } else {
            System.err.println("Erro: Não foi possível encontrar o arquivo do ícone: icone.png");
        }

        this.calculadora = new CalculadoraImpostos(); // Instancia a calculadora de impostos

        // Configuração do Painel Principal
        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        painelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20)); // Preenchimento interno
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 5, 8, 5); // Espaçamento entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Preenche horizontalmente

        // Definição de Fontes
        Font fonteDisplay = new Font("Arial", Font.BOLD, 42);
        Font fontePadrao = new Font("Arial", Font.PLAIN, 16);
        Font fonteBotao = new Font("Arial", Font.BOLD, 16);
        Font fonteResultadoLabel = new Font("Arial", Font.PLAIN, 18);
        Font fonteResultadoValor = new Font("Arial", Font.BOLD, 18);
        Font fonteResultadoFinal = new Font("Arial", Font.BOLD, 22);

        // Inicialização dos Componentes
        campoValorBruto = new JTextField(""); // Campo para o valor a ser calculado
        campoValorBruto.setFont(fonteDisplay);
        campoValorBruto.setHorizontalAlignment(JTextField.RIGHT);
        campoValorBruto.setBorder(null); // Remove a borda padrão
        campoValorBruto.setBackground(painelPrincipal.getBackground()); // Cor de fundo transparente

        seletorTipoOrgao = new JComboBox<>(new String[]{"Privado", "Órgão Público"});
        seletorTipoOrgao.setFont(fontePadrao);

        seletorBradesco = new JCheckBox("Bradesco?");
        seletorBradesco.setFont(fontePadrao);

        botaoLimpar = new JButton("Limpar");
        botaoLimpar.setFont(fonteBotao);

        // Labels de resultado
        labelValorLiquido = new JLabel("R$ 0,00");
        labelValorLiquido.setFont(fonteResultadoFinal);
        labelValorLiquido.setForeground(new Color(0, 100, 0)); // Cor verde para o valor líquido
        labelValorLiquido.setHorizontalAlignment(SwingConstants.RIGHT);

        labelValorIR = new JLabel("R$ 0,00");
        labelValorIR.setFont(fonteResultadoValor);
        labelValorIR.setHorizontalAlignment(SwingConstants.RIGHT);

        labelValorPIS = new JLabel("R$ 0,00");
        labelValorPIS.setFont(fonteResultadoValor);
        labelValorPIS.setHorizontalAlignment(SwingConstants.RIGHT);

        labelValorCOFINS = new JLabel("R$ 0,00");
        labelValorCOFINS.setFont(fonteResultadoValor);
        labelValorCOFINS.setHorizontalAlignment(SwingConstants.RIGHT);

        labelValorCSLL = new JLabel("R$ 0,00");
        labelValorCSLL.setFont(fonteResultadoValor);
        labelValorCSLL.setHorizontalAlignment(SwingConstants.RIGHT);

        labelAviso = new JLabel(" "); // Label para exibir avisos
        labelAviso.setHorizontalAlignment(SwingConstants.CENTER);

        // --- Adição dos Componentes ao Painel Principal usando GridBagLayout ---

        // Campo Valor Bruto (ocupa 2 colunas)
        gbc.gridwidth = 2; // Ocupa duas células de largura
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelPrincipal.add(campoValorBruto, gbc);

        // Criar painel auxiliar para combo + checkbox lado a lado (melhora o layout)
        JPanel painelTipoECheckbox = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        painelTipoECheckbox.setBackground(painelPrincipal.getBackground()); // Cor de fundo para combinar
        painelTipoECheckbox.add(seletorTipoOrgao);
        painelTipoECheckbox.add(seletorBradesco);

        // Label Tipo de Entidade
        gbc.gridwidth = 1; // Volta a ocupar 1 coluna
        gbc.gridy = 1;
        gbc.gridx = 0;
        JLabel labelTipoEntidade = new JLabel("Tipo de Entidade:");
        labelTipoEntidade.setFont(fontePadrao);
        painelPrincipal.add(labelTipoEntidade, gbc);

        // Adiciona painel auxiliar no lugar do combo original
        gbc.gridx = 1;
        painelPrincipal.add(painelTipoECheckbox, gbc);

        // Linha do botão Limpar (ocupa 2 colunas, centralizado)
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE; // Não preenche horizontalmente
        gbc.anchor = GridBagConstraints.CENTER; // Ancorado ao centro
        painelPrincipal.add(botaoLimpar, gbc);

        // Resetar para as configurações de resultados
        gbc.fill = GridBagConstraints.HORIZONTAL; // Volta a preencher horizontalmente
        gbc.gridwidth = 1; // Volta a ocupar 1 coluna
        gbc.anchor = GridBagConstraints.CENTER; // Ancorado ao centro

        // Separador (linha 3)
        gbc.gridy = 3;
        gbc.gridx = 0; // Começa na coluna 0
        gbc.gridwidth = 2; // Ocupa as duas colunas
        painelPrincipal.add(new JSeparator(), gbc);

        // Resetar gbc.gridwidth para as labels de resultado
        gbc.gridwidth = 1;

        // Resultados (começando na linha 4)
        gbc.gridy = 4;
        gbc.gridx = 0;
        painelPrincipal.add(new JLabel("IR Retido:") {{ setFont(fonteResultadoLabel); }}, gbc);
        gbc.gridx = 1;
        painelPrincipal.add(labelValorIR, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        painelPrincipal.add(new JLabel("PIS Retido:") {{ setFont(fonteResultadoLabel); }}, gbc);
        gbc.gridx = 1;
        painelPrincipal.add(labelValorPIS, gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        painelPrincipal.add(new JLabel("COFINS Retido:") {{ setFont(fonteResultadoLabel); }}, gbc);
        gbc.gridx = 1;
        painelPrincipal.add(labelValorCOFINS, gbc);

        gbc.gridy = 7;
        gbc.gridx = 0;
        painelPrincipal.add(new JLabel("CSLL Retido:") {{ setFont(fonteResultadoLabel); }}, gbc);
        gbc.gridx = 1;
        painelPrincipal.add(labelValorCSLL, gbc);

        // Valor Líquido (destaque)
        gbc.gridy = 8;
        gbc.gridx = 0;
        JLabel labelLiquidoTitulo = new JLabel("Valor Líquido:");
        labelLiquidoTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        painelPrincipal.add(labelLiquidoTitulo, gbc);
        gbc.gridx = 1;
        painelPrincipal.add(labelValorLiquido, gbc);

        // Label de aviso (ocupa 2 colunas)
        gbc.gridy = 9;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        painelPrincipal.add(labelAviso, gbc);

        this.add(painelPrincipal); // Adiciona o painel principal à janela

        // --- Configuração dos Listeners e Atalhos ---

        // Ação do botão Limpar
        botaoLimpar.addActionListener(e -> limparCampos());

        // Ação do campoValorBruto ao digitar (para calcular automaticamente)
        // Isso executa o cálculo cada vez que o texto muda (com um pequeno delay para performance)
        campoValorBruto.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { executarCalculo(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { executarCalculo(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { executarCalculo(); }
        });

        // Ação dos seletores ao mudar a opção (para recalcular)
        seletorTipoOrgao.addActionListener(e -> executarCalculo());
        seletorBradesco.addActionListener(e -> executarCalculo());

        configurarAtalhos(painelPrincipal); // Configura atalhos de teclado (ENTER e DELETE)
    }

    // Método para limpar todos os campos e resultados
    private void limparCampos() {
        campoValorBruto.setText("");
        labelValorLiquido.setText("R$ 0,00");
        labelValorIR.setText("R$ 0,00");
        labelValorPIS.setText("R$ 0,00");
        labelValorCOFINS.setText("R$ 0,00");
        labelValorCSLL.setText("R$ 0,00");
        labelAviso.setText(" "); // Limpa o aviso
        campoValorBruto.requestFocusInWindow(); // Coloca o foco de volta no campo de valor
    }

    // Configura atalhos de teclado (ENTER para calcular, DELETE para limpar)
    private void configurarAtalhos(JPanel painel) {
        InputMap inputMap = painel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = painel.getActionMap();

        // Atalho para ENTER (executar cálculo)
        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "calcularAction");
        actionMap.put("calcularAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executarCalculo();
            }
        });

        // Atalho para DELETE (limpar campos)
        inputMap.put(KeyStroke.getKeyStroke("DELETE"), "limparAction");
        actionMap.put("limparAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
    }

    // Método principal para executar o cálculo e atualizar a GUI
    private void executarCalculo() {
        try {
            String valorTexto = campoValorBruto.getText().trim();
            if (valorTexto.isEmpty()) {
                limparCampos(); // Limpa se o campo estiver vazio
                return;
            }

            // Usar NumberFormat para lidar com vírgulas e pontos (formato PT-BR)
            NumberFormat format = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
            double valorBruto = format.parse(valorTexto).doubleValue();

            // Obter as seleções da interface
            String tipoSelecionado = (String) seletorTipoOrgao.getSelectedItem();
            boolean isPublico = "Órgão Público".equals(tipoSelecionado);
            boolean isBradescoSelecionado = seletorBradesco.isSelected();

            // Chamar a lógica de cálculo da CalculadoraImpostos
            ResultadoCalculo resultado = calculadora.calcular(valorBruto, isPublico, isBradescoSelecionado);
            
            // Atualizar os labels da GUI com os resultados
            atualizarResultados(resultado);

        } catch (ParseException | NumberFormatException ex) {
            // Tratar erros de entrada (valor não numérico)
            JOptionPane.showMessageDialog(this,
                    "O valor inserido não é um número válido.\nPor favor, digite apenas números.",
                    "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            limparCampos(); // Limpa os campos em caso de erro
        }
    }

    // Atualiza os labels de resultado e o aviso com base no ResultadoCalculo
    private void atualizarResultados(ResultadoCalculo res) {
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        labelValorLiquido.setText(formatoMoeda.format(res.getValorLiquido()));
        labelValorIR.setText(formatoMoeda.format(res.getValorIR()));
        labelValorPIS.setText(formatoMoeda.format(res.getValorPIS()));
        labelValorCOFINS.setText(formatoMoeda.format(res.getValorCOFINS()));
        labelValorCSLL.setText(formatoMoeda.format(res.getValorCSLL()));

        // Lógica para exibir os avisos de dispensa
        labelAviso.setText(" "); // Limpa qualquer aviso anterior
        if (res.isDispensaTotal()) { // Alterado para o método correto
            labelAviso.setText("Aviso: Retenção TOTALMENTE dispensada (< R$ 10,00).");
            labelAviso.setForeground(Color.ORANGE.darker()); // Cor para destaque do aviso
        } else if (res.isDispensaIR()) { // Alterado para o método correto
            labelAviso.setText("Aviso: Retenção de IR dispensada (< R$ 10,00).");
            labelAviso.setForeground(Color.GRAY.darker()); // Cor para destaque do aviso
        } else {
             labelAviso.setText(" "); // Garante que o aviso esteja vazio se não houver dispensa
        }
    }

    // Método main para iniciar a aplicação
    public static void main(String[] args) {
        // Garante que a GUI seja construída na Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            JanelaCalculadora frame = new JanelaCalculadora();
            frame.setVisible(true);
        });
    }
}