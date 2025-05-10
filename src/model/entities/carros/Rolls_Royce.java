package model.entities.carros;

public class Rolls_Royce extends carrosCondi {

    public Rolls_Royce(String modelo, String submodelo, int km, int ano, String conbustivel, int preçoCompra) {
        super(modelo, submodelo, km, ano, conbustivel, preçoCompra);
    }

    @Override
    public String toString() {
        return "Rolls_Royce [modelo=" + getModelo() + "]";
    }
    
}
