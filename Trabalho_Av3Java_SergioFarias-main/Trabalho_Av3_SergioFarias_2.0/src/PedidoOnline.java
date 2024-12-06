public class PedidoOnline extends Pedidos implements InstrucoesDeEntrega{

    String enderecoCliente;

    public PedidoOnline(Pessoa pessoa, String enderecoCliente) {
        super(pessoa);
        this.enderecoCliente = enderecoCliente;
    }

    public String getEnderecoCliente() {
        return enderecoCliente;
    }

    @Override
    public void instrucoesDeEntrega(String instrucoesDeEntrega) {
        this.instrucoesDeEntrega = instrucoesDeEntrega;
    }
}
