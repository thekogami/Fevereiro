import java.time.LocalDate;

public class Produto {
    private int identificador;
    private String nome;
    private double valor;
    private int estoque;
    private String dataLancamento;

    private boolean ativo = true;

    public Produto(int identificador, String nome, double valor, int estoque, LocalDate dataLancamento) {
        this.identificador = identificador;
        this.nome = nome;
        this.valor = valor;
        this.estoque = estoque;
        this.dataLancamento = dataLancamento.toString();
    }

    public int getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public int getEstoque() {
        return estoque;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }
}