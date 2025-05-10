package model.servicos;

public interface AcoesCliente {

    String acessarConta();
    void adcionarAoCarrinho();
    void realizarCompra();
    void alterarEmail(String novoEmail);
    void alterarNome(String novoNome);
    void alterarSenha(String senhaAtual, String novaSenha);
    void alterarNumero(int novoNumero);
}
