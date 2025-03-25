package model.entities.carros;

public class AlfaRomeo extends carrosCondi {

    public AlfaRomeo(String modelo) {
        super(modelo);
    }

    @Override
    public String toString() {
        return "AlfaRomeo [modelo=" + getName() + "]";
    }
    
}
