package model.entities.carros;

public class Peugeot extends carrosCondi {

    public Peugeot(String modelo, String submodelo, int km, int ano, String conbustivel, int preço, String cor) {
        super(modelo, submodelo, km, ano, conbustivel, preço, cor);
    }

    @Override
    public String toString() {
        return "Peugeot [modelo=" + getModelo() + "]";
    }
    
}
