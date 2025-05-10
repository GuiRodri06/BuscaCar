package model.entities.carros;

public class Mercedes extends carrosCondi {

    public Mercedes(String modelo, String submodelo, int km, int ano, String conbustivel, int preçoCompra) {
        super(modelo, submodelo, km, ano, conbustivel, preçoCompra);
    }

    @Override
    public String toString() {
        return "Mercedes [modelo=" + getModelo() + "]";
    }
    
}
