package model.entities.carros;

public class AlfaRomeo extends carrosCondi {

    public AlfaRomeo(String model) {
        super(model);
    }

    @Override
    public String toString() {
        return "AlfaRomeo [modelo=" + getName() + "]";
    }
    
}
