// Arquivo: CalculadoraImpostos.java

public class CalculadoraImpostos {

    private static final double ALIQUOTA_IR = 0.015;
    private static final double ALIQUOTA_IR_OP = 0.012;
    private static final double ALIQUOTA_PIS = 0.0065;
    private static final double ALIQUOTA_COFINS = 0.03;
    private static final double ALIQUOTA_CSLL = 0.01;

    public ResultadoCalculo calcular(double valorParaCalcular, boolean isOrgaoPublico) {
        double valorIR = 0, valorPIS = 0, valorCOFINS = 0, valorCSLL = 0;
        boolean dispensaTotal = false, dispensaIR = false;

        if (isOrgaoPublico) {
            valorIR = valorParaCalcular * ALIQUOTA_IR_OP;
        } else {
            double somaAliquotas = ALIQUOTA_IR + ALIQUOTA_PIS + ALIQUOTA_COFINS + ALIQUOTA_CSLL;
            double totalImpostosCalculado = valorParaCalcular * somaAliquotas;

            if (totalImpostosCalculado < 10.0) {
                dispensaTotal = true;
            } else {
                valorPIS = valorParaCalcular * ALIQUOTA_PIS;
                valorCOFINS = valorParaCalcular * ALIQUOTA_COFINS;
                valorCSLL = valorParaCalcular * ALIQUOTA_CSLL;

                double valorIRCalculado = valorParaCalcular * ALIQUOTA_IR;
                if (valorIRCalculado < 10.0) {
                    dispensaIR = true;
                    valorIR = 0;
                } else {
                    valorIR = valorIRCalculado;
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