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
    

    

    public String getName() {
        return modelo;
    }

    public void setName(String name) {
        this.modelo = name;
    }

    
}
