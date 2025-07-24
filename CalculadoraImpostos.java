// Arquivo: CalculadoraImpostos.java

public class CalculadoraImpostos {

    private static final double ALIQUOTA_IR = 0.015;
    private static final double ALIQUOTA_IR_OP = 0.012;
    private static final double ALIQUOTA_PIS = 0.0065;
    private static final double ALIQUOTA_COFINS = 0.03;
    private static final double ALIQUOTA_CSLL = 0.01;

    public ResultadoCalculo calcular(double valorParaCalcular, boolean isOrgaoPublico, boolean isBradesco) {
        double valorIR = 0;
        double valorPIS = 0;
        double valorCOFINS = 0;
        double valorCSLL = 0;

        boolean dispensaTotal = false;
        boolean dispensaIR = false;

        if (isOrgaoPublico) {
            // Lógica específica para Órgãos Públicos
            valorIR = valorParaCalcular * ALIQUOTA_IR_OP;
            // PIS, COFINS, CSLL não são calculados para órgãos públicos com base no código original.
            // Se eles devem ser, esta seção precisa de ajuste.
        } else {
            // Lógica para organizações não-públicas (incluindo Bradesco e outros)
            double somaAliquotas = ALIQUOTA_IR + ALIQUOTA_PIS + ALIQUOTA_COFINS + ALIQUOTA_CSLL;
            double totalImpostosCalculado = valorParaCalcular * somaAliquotas;

            if (totalImpostosCalculado < 10.0) {
                dispensaTotal = true;
                // Todos os valores de impostos permanecem 0 como inicializados, se dispensaTotal for verdadeiro
            } else {
                // Calcular PIS, COFINS, CSLL para Bradesco e casos gerais
                valorPIS = valorParaCalcular * ALIQUOTA_PIS;
                valorCOFINS = valorParaCalcular * ALIQUOTA_COFINS;
                valorCSLL = valorParaCalcular * ALIQUOTA_CSLL;

                if (isBradesco) {
                    // Para Bradesco, o IR é sempre calculado se totalImpostosCalculado >= 10.0
                    valorIR = valorParaCalcular * ALIQUOTA_IR;
                } else {
                    // Para organizações não-Bradesco e não-públicas, verificar a dispensa de IR
                    double valorIRCalculado = valorParaCalcular * ALIQUOTA_IR;
                    if (valorIRCalculado < 10.0) {
                        dispensaIR = true;
                        valorIR = 0; // IR é dispensado
                    } else {
                        valorIR = valorIRCalculado;
                    }
                }
            }
        }
        
        double totalDescontos = valorIR + valorPIS + valorCOFINS + valorCSLL;
        double valorLiquido = valorParaCalcular - totalDescontos;
        
        return new ResultadoCalculo(
            valorParaCalcular, valorLiquido, valorIR, valorPIS, valorCOFINS, valorCSLL, dispensaTotal, dispensaIR
        );
    }
}

