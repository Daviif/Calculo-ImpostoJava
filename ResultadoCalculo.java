// Arquivo: ResultadoCalculo.java

public class ResultadoCalculo {

    private final double valorBruto;
    private final double valorLiquido;
    private final double valorIR;
    private final double valorPIS;
    private final double valorCOFINS;
    private final double valorCSLL;
    private final boolean dispensaRetencaoTotal;
    private final boolean dispensaRetencaoIR;

    public ResultadoCalculo(double valorBruto, double valorLiquido, double valorIR, double valorPIS, double valorCOFINS, double valorCSLL, boolean dispensaTotal, boolean dispensaIR) {
        this.valorBruto = valorBruto;
        this.valorLiquido = valorLiquido;
        this.valorIR = valorIR;
        this.valorPIS = valorPIS;
        this.valorCOFINS = valorCOFINS;
        this.valorCSLL = valorCSLL;
        this.dispensaRetencaoTotal = dispensaTotal;
        this.dispensaRetencaoIR = dispensaIR;
    }

    public double getValorBruto() { return valorBruto; }
    public double getValorLiquido() { return valorLiquido; }
    public double getValorIR() { return valorIR; }
    public double getValorPIS() { return valorPIS; }
    public double getValorCOFINS() { return valorCOFINS; }
    public double getValorCSLL() { return valorCSLL; }
    public boolean isDispensaRetencaoTotal() { return dispensaRetencaoTotal; }
    public boolean isDispensaRetencaoIR() { return dispensaRetencaoIR; }
}