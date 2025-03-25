package model.entities.carros;

public class carrosCondi {

    private String modelo;
    private String submodelo;
    private int km;
    private int ano;
    private String conbustivel;
    private int preço;
    private String cor;

    public carrosCondi(String modelo, String submodelo, int km, int ano, String conbustivel, int preço, String cor) {
        this.modelo = modelo;
        this.submodelo = submodelo;
        this.km = km;
        this.ano = ano;
        this.conbustivel = conbustivel;
        this.preço = preço;
        this.cor = cor;
    
        }
    
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String name) {
        this.modelo = name;
    }

    public String getSubmodelo() {
        return submodelo;
    }

    public void setSubmodelo(String submodelo) {
        this.submodelo = submodelo;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getConbustivel() {
        return conbustivel;
    }

    public void setConbustivel(String conbustivel) {
        this.conbustivel = conbustivel;
    }

    public int getPreço() {
        return preço;
    }

    public void setPreço(int preço) {
        this.preço = preço;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }


    //para carros com mais 100'000 km, o preço é reduzido em 25%
    public void calcularPrecoKm100000(int km) {
        if (km > 100000) {
            preço = (int) (preço / 1.25); 
            
        }
    }


    //para carros com mais de 10 anos, o preço é reduzido em 10%
    public void calcularPrecoAno10(int ano) {
        if (ano > 10) {
            preço = (int) (preço / 1.10); 
            
        }
    }

    //para carros electricos, o preço é reduzido em 20%
    public void calcularPrecoEletrico(String conbustivel) {
        if (conbustivel.equalsIgnoreCase("eletrico")) {
            preço = (int) (preço / 1.20); 
            
        }
    }

    //para carros Hybridos, o preço é reduzido em 10%
    public void calcularPrecoHybrido(String conbustivel) {
        if (conbustivel.equalsIgnoreCase("hybrido")) {
            preço = (int) (preço / 1.10); 
            
        }
    }


    //para clientes com menos de 25 anos, o preço é aumentado em 15%
    public void calcularPrecoPorIdadeMotorista(int idadeMotorista) {
        if (idadeMotorista < 25) {
            preço = (int) (preço * 1.15);
        }
    }

    public void calcularPrecoPorMarcaCara(String marca) {
        // Comparação ignorando maiúsculas/minúsculas
        marca = marca.toLowerCase();
        if (marca.equals("Rolls-Royce") || marca.equals("Porsche") || marca.equals("Lamborghini")) {
            preço *= 1.20; // +20%
        }
    }


    



    
}
