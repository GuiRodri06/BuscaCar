package model.entities.carros;

public class carrosCondi {

    private String modelo;
    private String submodelo;
    private int km;
    private int ano;
    private String conbustivel;
    private int preço;
    private String cor;

    public carrosCondi(String modelo, String submodelo, int km, int ano, String conbustivel, int preço, String cor) {
        this.modelo = modelo;
        this.submodelo = submodelo;
        this.km = km;
        this.ano = ano;
        this.conbustivel = conbustivel;
        this.preço = preço;
        this.cor = cor;
    
        }
    
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String name) {
        this.modelo = name;
    }

    public String getSubmodelo() {
        return submodelo;
    }

    public void setSubmodelo(String submodelo) {
        this.submodelo = submodelo;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getConbustivel() {
        return conbustivel;
    }

    public void setConbustivel(String conbustivel) {
        this.conbustivel = conbustivel;
    }

    public int getPreço() {
        return preço;
    }

    public void setPreço(int preço) {
        this.preço = preço;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void 


    



    
}
