import java.util.*;

public abstract class Pedidos {

    String instrucoesDeEntrega;

    private Pessoa pessoa;
    private Map<Produtos, Integer> produtos = new HashMap<>();

    public Pedidos (Pessoa pessoa){
        this.pessoa = pessoa;
    }

    public Pedidos(String instrucoesDeEntrega) {
        this.instrucoesDeEntrega = instrucoesDeEntrega;
    }

    public String getInstrucoesDeEntrega() {
        return instrucoesDeEntrega;
    }

    public void adicionarProduto(Produtos produtos, int quantidade) throws EstoqueInsuficienteException{
        produtos.reduzirEstoque(quantidade);
        this.produtos.put(produtos, quantidade);
    }

    public double calcularTotal() {
        double total = 0.0;
        for (Map.Entry<Produtos, Integer> entry : produtos.entrySet()) {
            Produtos produto = entry.getKey();
            int quantidade = entry.getValue();
            total += produto.getPreco() * quantidade;
        }
        return total;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Map<Produtos, Integer> getProdutos() {
        return produtos;
    }
}
