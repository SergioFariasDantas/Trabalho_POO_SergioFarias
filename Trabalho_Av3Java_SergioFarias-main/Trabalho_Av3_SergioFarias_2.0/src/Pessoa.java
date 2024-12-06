public abstract class Pessoa implements Autorizacao{

    static String nomePessoa;
    boolean autorizacao;

    public boolean isAutorizacao() {
        return autorizacao;
    }

    public Pessoa(boolean autorizacao, String nomePessoa) {
        this.autorizacao = autorizacao;
        this.nomePessoa = nomePessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    @Override
    public String toString(){
        return "Nome: " + nomePessoa;
    }


    public void isAutorizacaoDeMudanca(boolean autorizacao) throws PermissaoNaoAutorizadaException{
        if(isAutorizacao() == false){
            throw new PermissaoNaoAutorizadaException("Você não tem autorização para realizar essa ação");
        }
    }
}
