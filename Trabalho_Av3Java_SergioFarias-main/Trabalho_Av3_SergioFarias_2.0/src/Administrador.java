public class Administrador extends Pessoa{

    public Administrador(boolean autorizacao, String nomePessoa) {
        super(autorizacao, nomePessoa);
    }


    @Override
    public boolean isAutorizacao(){
        return super.isAutorizacao() == true;
    }
}
