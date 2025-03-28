package model.entities.carros;

public class Volvo extends carrosCondi {

    public Volvo(String modelo, String submodelo, int km, int ano, String conbustivel, int preço, String cor) {
        super(modelo, submodelo, km, ano, conbustivel, preço, cor);
    }

    @Override
    public String toString() {
        return "Volvo [modelo=" + getModelo() + "]";
    }   
    
}
