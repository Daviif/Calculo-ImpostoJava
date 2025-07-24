// Arquivo: ResultadoCalculo.java

public class ResultadoCalculo {
    private double valorOriginal;
    private double valorLiquido;
    private double valorIR;
    private double valorPIS;
    private double valorCOFINS;
    private double valorCSLL;
    private boolean dispensaTotal; // Este campo armazena se a retenção total foi dispensada
    private boolean dispensaIR;    // Este campo armazena se a retenção de IR foi dispensada

    public ResultadoCalculo(double valorOriginal, double valorLiquido, double valorIR, double valorPIS, double valorCOFINS, double valorCSLL, boolean dispensaTotal, boolean dispensaIR) {
        this.valorOriginal = valorOriginal;
        this.valorLiquido = valorLiquido;
        this.valorIR = valorIR;
        this.valorPIS = valorPIS;
        this.valorCOFINS = valorCOFINS;
        this.valorCSLL = valorCSLL;
        this.dispensaTotal = dispensaTotal;
        this.dispensaIR = dispensaIR;
    }

    // Getters para acessar os valores
    public double getValorOriginal() {
        return valorOriginal;
    }

    public double getValorLiquido() {
        return valorLiquido;
    }

    public double getValorIR() {
        return valorIR;
    }

    public double getValorPIS() {
        return valorPIS;
    }

    public double getValorCOFINS() {
        return valorCOFINS;
    }

    public double getValorCSLL() {
        return valorCSLL;
    }

    // Novos métodos públicos para verificar as flags de dispensa
    public boolean isDispensaTotal() {
        return dispensaTotal;
    }

    public boolean isDispensaIR() {
        return dispensaIR;
    }
}