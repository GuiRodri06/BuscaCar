package model.entities.contas;

import model.servicos.AcoesCliente;

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
    public String acessarConta() {
        return String.format("Usuario = %s \nE-mail = %s \nSenha = %s \nNome completo = %s \nNúmero de telemóvel = %d \nNIF = %d", usuario, email, senha, name, telemovel, nif);
    }

    @Override
    public void adcionarAoCarrinho() {

    }

    @Override
    public void realizarCompra() {

    }

    // metodo para alterar o usuario
    @Override
    public void alterarUsuario(String novoUsuario) {
        if (novoUsuario != null && !novoUsuario.isEmpty()) {
            this.usuario = novoUsuario;
            System.out.println("Nome de usuario alterado com sucesso!");
        } else {
            System.out.println("Nome de usuario inválido!");
        }
    }

    @Override
    public void alterarEmail(String novoEmail) {
        if (novoEmail != null && novoEmail.contains("@")) {
            this.email = novoEmail;
            System.out.println("E-mail alterado com sucesso!");
        } else {
            System.out.println("E-mail inválido.");
        }
    }

    @Override
    public void alterarNome(String novoNome) {
        if (novoNome != null && !novoNome.isEmpty()) {
            this.name = novoNome;
            System.out.println("Nome alterado com sucesso!");
        } else {
            System.out.println("Nome inválido.");
        }
    }

    @Override
    public void alterarSenha(String senhaAtual, String novaSenha) {
        if (this.senha.equals(senhaAtual)) {
            this.senha = novaSenha;
            System.out.println("Senha alterada com sucesso!");
        } else {
            System.out.println("A senha atual está incorreta.");
        }
    }

    @Override
    public void alterarNumero(int novoNumero) {
        if (novoNumero > 0) {
            this.telemovel = novoNumero;
            System.out.println("Número de telefone alterado com sucesso!");
        } else {
            System.out.println("Número inválido! O número deve ser maior que 0.");
        }
    }

}