public class Eletronicos extends Produtos implements Desconto{

    String modelo;
    String marca;
    String especificacoes;
    int ano;

    public Eletronicos(String nome, double preco, int estoque, String modelo, String marca, String especificacoes, int ano) {
        super(nome, preco, estoque);
        this.modelo = modelo;
        this.marca = marca;
        this.especificacoes = especificacoes;
        this.ano = ano;
    }


    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public String getEspecificacoes() {
        return especificacoes;
    }


}
