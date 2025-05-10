package model.entities.carros;

public class Porsche extends carrosCondi {

    public Porsche(String modelo, String submodelo, int km, int ano, String conbustivel, int preçoCompra, String cor) {
            super(modelo, submodelo, km, ano, conbustivel, preçoCompra);
        }

    @Override
    public String toString() {
        return "Porsche [modelo=" + getModelo() + "]";
    }
    
}
