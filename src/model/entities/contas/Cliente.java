package model.entities.contas;

public class Cliente extends Usuario{

    private Integer nif;
    private Integer telemovel;

    public Cliente(String name, String usuario, String senha, String email, Integer nif, Integer telemovel) {
        super(name, usuario, senha, email);
        this.nif = nif;
        this.telemovel = telemovel;
    }

    public Integer getNif() {
        return nif;
    }

    public void setNif(Integer nif) {
        this.nif = nif;
    }

    public Integer getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(Integer telemovel) {
        this.telemovel = telemovel;
    }

    /* Metodo para verificar se o usuário e a senha estão corretos */
    @Override
    public boolean autenticar() {
        return false;
    }
}
