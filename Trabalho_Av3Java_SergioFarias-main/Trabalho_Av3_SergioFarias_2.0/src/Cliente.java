public class Cliente extends Pessoa{

    public Cliente(boolean autorizacao, String nomePessoa) {
        super(autorizacao, nomePessoa);
    }

    @Override
    public boolean isAutorizacao() {
        return super.isAutorizacao() == false;
    }
}
