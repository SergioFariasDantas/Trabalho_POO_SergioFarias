public class Roupas extends Produtos {

    String tamanho;
    String tipoDeTecido;
    String fabricadora;

    public Roupas(String nome, double preco, int estoque, String tamanho, String tipoDeTecido, String fabricadora) {
        super(nome, preco, estoque);
        this.tamanho = tamanho;
        this.tipoDeTecido = tipoDeTecido;
        this.fabricadora = fabricadora;
    }

    public String getTamanho() {
        return tamanho;
    }

    public String getTipoDeTecido() {
        return tipoDeTecido;
    }

    public String getFabricadora() {
        return fabricadora;
    }
}
