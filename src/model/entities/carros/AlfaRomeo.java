package model.entities.carros;

public class AlfaRomeo extends carrosCondi {

    public AlfaRomeo(String modelo, String submodelo, int km, int ano, String conbustivel, int preçoCompra, String cor) {
        super(modelo, submodelo, km, ano, conbustivel, preçoCompra);
    }

    @Override
    public String toString() {
        return "AlfaRomeo [modelo=" + getModelo() + "]";
    }
    
    




}
