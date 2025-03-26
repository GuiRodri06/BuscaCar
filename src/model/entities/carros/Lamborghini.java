package model.entities.carros;

public class Lamborghini extends carrosCondi {

    public Lamborghini(String modelo, String submodelo, int km, int ano, String conbustivel, int preço, String cor) {
        super(modelo, submodelo, km, ano, conbustivel, preço, cor);
    }

    @Override
    public String toString() {
        return "Lamborghini [modelo=" + getModelo() + "]";
    }

}