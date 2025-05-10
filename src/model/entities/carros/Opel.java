package model.entities.carros;

public class Opel extends carrosCondi {

    public Opel(String modelo, String submodelo, int km, int ano, String conbustivel, int preçoCompra) {
        super(modelo, submodelo, km, ano, conbustivel, preçoCompra);
    }

    @Override
    public String toString() {
        return "Opel [modelo=" + getModelo() + "]";
    }
    
}
