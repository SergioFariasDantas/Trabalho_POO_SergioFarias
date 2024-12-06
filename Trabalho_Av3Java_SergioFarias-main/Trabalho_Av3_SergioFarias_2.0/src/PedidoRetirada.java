public class PedidoRetirada extends Pedidos{
    public PedidoRetirada(Pessoa pessoa) {
        super(pessoa);
    }

    String enderecoLoja;

    public void setEnderecoLoja(){
        this.enderecoLoja = "Rua dos Clientes 123 - Bairro Das Lojas";
    }

    public String getEnderecoLoja() {
        return enderecoLoja;
    }
}
