public abstract class Produtos implements Desconto{

    public String nomeProduto;
    public double preco;
    public int estoque;

    public String getNomeProduto() {
        return nomeProduto;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public Produtos(String nomeProduto, double preco, int estoque) {
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        this.estoque = estoque;
    }

    public void reduzirEstoque(int quantidade) throws EstoqueInsuficienteException {
        if (quantidade > estoque) {
            throw new EstoqueInsuficienteException("Estoque insuficiente para o produto: " + nomeProduto);
        }
        estoque -= quantidade;
    }

    @Override
    public String toString() {
        return nomeProduto + " - Preço: R$" + preco + " - Estoque: " + estoque;
    }

    @Override
    public void descontoLetras(String descontoLetras) {

        boolean descontoLetrasB;
        if (descontoLetras != null && descontoLetras.length() == 3) {
            descontoLetrasB = Boolean.getBoolean(descontoLetras);
            descontoLetrasB = true;
        } else {
            descontoLetrasB = false;
        }
    }

    @Override
    public void descontoNumeros(String descontoNumeros){
        boolean descontoNumerosB;
        if (descontoNumeros != null && String.valueOf(descontoNumeros).length() == 3){
            descontoNumerosB = Boolean.getBoolean(String.valueOf(descontoNumeros));
            descontoNumerosB = true;
        } else{
            descontoNumerosB = false;
        }
    }

    public void descontoCalculo(String descontoLetras, String descontoNumeros, boolean descontoNumeroB, boolean descontoLetrasB) throws DescontoInvalidoException{
        double precoDesconto = 0;
        if(descontoLetrasB == true && descontoNumeroB == true){
            precoDesconto = precoDesconto - (precoDesconto * Integer.parseInt(descontoNumeros));
        } else {
            throw new DescontoInvalidoException("Este desconto é invalido");
        }
    }
}
