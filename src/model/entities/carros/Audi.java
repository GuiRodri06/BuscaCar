package model.entities.carros;

public class Audi extends carrosCondi {

    public Audi(String modelo, String submodelo, int km, int ano, String conbustivel, int preçoCompra) {
        super(modelo, submodelo, km, ano, conbustivel, preçoCompra);
    }

    @Override
    public String toString() {
        return "Audi [modelo=" + getModelo() + "]";
    }



    



    
}
