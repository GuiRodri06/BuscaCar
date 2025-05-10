package model.entities.carros;

public class Honda extends carrosCondi {

    public Honda(String modelo, String submodelo, int km, int ano, String conbustivel, int preçoCompra) {
        super(modelo, submodelo, km, ano, conbustivel, preçoCompra);
    }

    @Override
    public String toString() {
        return "Honda [modelo=" + getModelo() + "]";
    }

}
