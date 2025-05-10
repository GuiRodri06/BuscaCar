package model.entities.carros;

public class Mini extends carrosCondi {

    public Mini(String modelo, String submodelo, int km, int ano, String conbustivel, int preçoCompra) {
        super(modelo, submodelo, km, ano, conbustivel, preçoCompra);
    }

    @Override
    public String toString() {
        return "Mini [modelo=" + getModelo() + "]";
    }
    
}
