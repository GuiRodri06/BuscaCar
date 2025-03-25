package model.entities.contas;

import java.util.Scanner;

public abstract class Usuario {

    private String name;
    private String usuario;
    private String senha;
    private String email;

    public static Usuario userLogado;

    public Usuario() {
    }

    public Usuario(String name, String usuario, String senha, String email) {
        this.name = name;
        this.usuario = usuario;
        this.senha = senha;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    /* Metodo para fazer login */
    public static void login() {
        Scanner txt = new Scanner(System.in);

        System.out.print("Insira seu nome: ");
        String nome = txt.nextLine();

        System.out.print("Insira seu nome de usuario: ");
        String nomeUsuario = txt.nextLine();

        System.out.print("Insira seu email: ");
        String email = txt.nextLine();

        System.out.print("Insira sua senha: ");
        String senha = txt.nextLine();

        if (nomeUsuario.trim().equals("adm") && email.trim().equals("adm@gmail.com") && senha.trim().equals("adm123")) {
            System.out.println("Seja bem-vindo adm!");

            userLogado = new Administrador(nome, nomeUsuario, senha, email);

        } else {
            System.out.println("Seja bem-vindo ao BuscaCar! Para completar preencha com as utimas informacoes");

            System.out.print("Insira seu nif: ");
            int nif = txt.nextInt();

            System.out.print("Insira seu numero de telemóvel: ");
            int telemovel = txt.nextInt();

            userLogado = new Cliente(nome, nomeUsuario, senha, email, nif, telemovel);

        }
    }

}