package model.entities.carros;

public class carrosCondi {

    private String modelo;
    private String submodelo;
    private int km;
    private int ano;
    private String conbustivel;
    private int preço;
    private String cor;

    public carrosCondi(String modelo) {
        this.modelo = modelo;
    }

    public String getName() {
        return modelo;
    }

    public void setName(String name) {
        this.modelo = name;
    }

    
}
