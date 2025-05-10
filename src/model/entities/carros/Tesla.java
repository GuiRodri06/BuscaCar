package model.entities.carros;

public class Tesla extends carrosCondi {

    public Tesla(String modelo, String submodelo, int km, int ano, String conbustivel, int preçoCompra, String cor) {
        super(modelo, submodelo, km, ano, conbustivel, preçoCompra);
    }

    @Override
    public String toString() {
        return "Tesla [modelo=" + getModelo() + "]";
    }

}
