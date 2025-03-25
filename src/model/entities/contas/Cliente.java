package model.entities.contas;

import model.entities.servicos.AcoesCliente;

public class Cliente extends Usuario implements AcoesCliente {

    private Integer nif;
    private Integer telemovel;

    public Cliente() {
    }

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

    @Override
    public void adcionarAoCarrinho() {

    }

    @Override
    public void realizarCompra() {

    }

    @Override
    public void acessarConta() {

    }
}
