package model.entities.carros;

public class Rolls_Royce extends carrosCondi {

    public Rolls_Royce(String modelo, String submodelo, int km, int ano, String conbustivel, int preço, String cor) {
        super(modelo, submodelo, km, ano, conbustivel, preço, cor);
    }

    @Override
    public String toString() {
        return "Rolls_Royce [modelo=" + getModelo() + "]";
    }
    
}
