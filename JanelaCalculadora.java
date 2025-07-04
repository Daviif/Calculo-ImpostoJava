// Arquivo: JanelaCalculadora.java (com ícone personalizado)

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL; // Import necessário para carregar o ícone
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class JanelaCalculadora extends JFrame {
    
    // ... (declaração de componentes permanece a mesma) ...
    private JTextField campoValorBruto;
    private JComboBox<String> seletorTipoOrgao;
    private JButton botaoLimpar;
    private JLabel labelValorLiquido;
    private JLabel labelValorIR;
    private JLabel labelValorPIS;
    private JLabel labelValorCOFINS;
    private JLabel labelValorCSLL;
    private JLabel labelAviso;
    private final CalculadoraImpostos calculadora;

    public JanelaCalculadora() {
        // --- Configurações da Janela ---
        setTitle("Calculadora de Impostos");
        setSize(480, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // --- NOVO: CÓDIGO PARA ADICIONAR O ÍCONE ---
        // Usar getResource garante que o ícone seja encontrado mesmo dentro de um .jar
        URL urlIcone = getClass().getResource("icone.png");
        if (urlIcone != null) {
            setIconImage(new ImageIcon(urlIcone).getImage());
        } else {
            System.err.println("Erro: Não foi possível encontrar o arquivo do ícone: icone.png");
        }
        // --- FIM DO BLOCO DO ÍCONE ---

        this.calculadora = new CalculadoraImpostos();
        
        // ... (o resto do código do construtor e dos outros métodos permanece o mesmo) ...
        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        painelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 5, 8, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font fonteDisplay = new Font("Arial", Font.BOLD, 42);
        Font fontePadrao = new Font("Arial", Font.PLAIN, 16);
        Font fonteBotao = new Font("Arial", Font.BOLD, 16);
        Font fonteResultadoLabel = new Font("Arial", Font.PLAIN, 18);
        Font fonteResultadoValor = new Font("Arial", Font.BOLD, 18);
        Font fonteResultadoFinal = new Font("Arial", Font.BOLD, 22);

        campoValorBruto = new JTextField("0");
        campoValorBruto.setFont(fonteDisplay);
        campoValorBruto.setHorizontalAlignment(JTextField.RIGHT);
        campoValorBruto.setBorder(null);
        campoValorBruto.setBackground(painelPrincipal.getBackground());

        seletorTipoOrgao = new JComboBox<>(new String[]{"Privado", "Órgão Público"});
        seletorTipoOrgao.setFont(fontePadrao);

        botaoLimpar = new JButton("Limpar");
        botaoLimpar.setFont(fonteBotao);

        labelValorLiquido = new JLabel("R$ 0,00");
        labelValorLiquido.setFont(fonteResultadoFinal);
        labelValorLiquido.setForeground(new Color(0, 100, 0));
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
        
        labelAviso = new JLabel(" ");
        labelAviso.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridwidth = 2; gbc.gridx = 0; gbc.gridy = 0; painelPrincipal.add(campoValorBruto, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = 1;
        JLabel labelTipoEntidade = new JLabel("Tipo de Entidade:");
        labelTipoEntidade.setFont(fontePadrao);
        painelPrincipal.add(labelTipoEntidade, gbc);
        
        gbc.gridx = 1; gbc.gridy = 1; painelPrincipal.add(seletorTipoOrgao, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        painelPrincipal.add(botaoLimpar, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; painelPrincipal.add(new JSeparator(), gbc);
        gbc.gridwidth = 1;
        
        gbc.gridx = 0; gbc.gridy = 4; painelPrincipal.add(new JLabel("IR Retido:"){ { setFont(fonteResultadoLabel); } }, gbc);
        gbc.gridx = 1; gbc.gridy = 4; painelPrincipal.add(labelValorIR, gbc);
        gbc.gridx = 0; gbc.gridy = 5; painelPrincipal.add(new JLabel("PIS Retido:"){ { setFont(fonteResultadoLabel); } }, gbc);
        gbc.gridx = 1; gbc.gridy = 5; painelPrincipal.add(labelValorPIS, gbc);
        gbc.gridx = 0; gbc.gridy = 6; painelPrincipal.add(new JLabel("COFINS Retido:"){ { setFont(fonteResultadoLabel); } }, gbc);
        gbc.gridx = 1; gbc.gridy = 6; painelPrincipal.add(labelValorCOFINS, gbc);
        gbc.gridx = 0; gbc.gridy = 7; painelPrincipal.add(new JLabel("CSLL Retido:"){ { setFont(fonteResultadoLabel); } }, gbc);
        gbc.gridx = 1; gbc.gridy = 7; painelPrincipal.add(labelValorCSLL, gbc);
        
        gbc.gridx = 0; gbc.gridy = 8;
        JLabel labelLiquidoTitulo = new JLabel("Valor Líquido:");
        labelLiquidoTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        painelPrincipal.add(labelLiquidoTitulo, gbc);
        
        gbc.gridx = 1; gbc.gridy = 8; painelPrincipal.add(labelValorLiquido, gbc);
        
        gbc.gridx = 0; gbc.gridy = 9; gbc.gridwidth = 2; painelPrincipal.add(labelAviso, gbc);

        this.add(painelPrincipal);
        botaoLimpar.addActionListener(e -> campoValorBruto.setText("0"));
        configurarAtalhos(painelPrincipal);
    }

    private void configurarAtalhos(JPanel painel) {
        InputMap inputMap = painel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = painel.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "calcularAction");
        actionMap.put("calcularAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executarCalculo();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("DELETE"), "limparAction");
        actionMap.put("limparAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoValorBruto.setText("0");
            }
        });
    }

    private void executarCalculo() {
        try {
            String valorTexto = campoValorBruto.getText();
            if (valorTexto.isEmpty() || valorTexto.equals("0")) {
                return; 
            }
            NumberFormat format = NumberFormat.getNumberInstance(Locale.of("pt", "BR"));
            double valorBruto = format.parse(valorTexto).doubleValue();
            
            String tipoSelecionado = (String) seletorTipoOrgao.getSelectedItem();
            boolean isPublico = "Órgão Público".equals(tipoSelecionado);
            
            ResultadoCalculo resultado = calculadora.calcular(valorBruto, isPublico);
            atualizarResultados(resultado);

        } catch (ParseException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "O valor inserido não é um número válido.",
                    "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarResultados(ResultadoCalculo res) {
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"));
        
        labelValorLiquido.setText(formatoMoeda.format(res.getValorLiquido()));
        labelValorIR.setText(formatoMoeda.format(res.getValorIR()));
        labelValorPIS.setText(formatoMoeda.format(res.getValorPIS()));
        labelValorCOFINS.setText(formatoMoeda.format(res.getValorCOFINS()));
        labelValorCSLL.setText(formatoMoeda.format(res.getValorCSLL()));
        
        labelAviso.setText(" "); 
        if (res.isDispensaRetencaoTotal()) {
            labelAviso.setText("Aviso: Retenção totalmente dispensada (< R$ 10,00).");
        } else if (res.isDispensaRetencaoIR()) {
            labelAviso.setText("Aviso: Retenção de IR dispensada (< R$ 10,00).");
        }
    }
}