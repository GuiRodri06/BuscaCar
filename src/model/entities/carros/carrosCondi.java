//Guilherme Lucas
package model.entities.carros;

public class carrosCondi {

    /* ---------- atributos ---------- */
    private int id;
    private String modelo;
    private String submodelo;
    private int km;
    private int ano;
    private String conbustivel;
    private int preçoCompra;          // mantive o nome original
    private double tarifaDiaBase;
    

    /* ---------- construtor ---------- */
    public carrosCondi(int id, String modelo, String submodelo, int km,
                       int ano, String conbustivel, int preçoCompra) {
        this.id = id;
        this.modelo       = modelo;
        this.submodelo    = submodelo;
        this.km           = km;
        this.ano          = ano;
        this.conbustivel  = conbustivel;
        this.preçoCompra  = preçoCompra;
        

        /* conversão compra → dia */
        this.tarifaDiaBase = preçoCompra * 0.002; 
    }

    /* ---------- getters / setters originais ---------- */

    public int getId() {
        return id;
    }

    public String getModelo()               { return modelo; }
    public void   setModelo(String name)    { this.modelo = name; }

    public String getSubmodelo()            { return submodelo; }
    public void   setSubmodelo(String sm)   { this.submodelo = sm; }

    public int  getKm()                     { return km; }
    public void setKm(int km)               { this.km = km; }

    public int  getAno()                    { return ano; }
    public void setAno(int ano)             { this.ano = ano; }

    public String getConbustivel()          { return conbustivel; }
    public void   setConbustivel(String c)  { this.conbustivel = c; }

    public int  getPreço()                  { return preçoCompra; }
    public void setPreço(int p)             { this.preçoCompra = p; }

   



    public double getTarifaDiaBase()       { return tarifaDiaBase; }

    /* ---------- regras de preço(agora atuam em tarifaHoraBase) ---------- */

    // >100000km  ⇒  –25%
    public void calcularPrecoKm100000(int km) {
        if (km > 100_000) {
            tarifaDiaBase *= 0.75;   // sem cast, mantém centavos
        }
    }

    // <10anos  ⇒  –10%
    public void calcularPrecoAno2015(int ano) {
        if (ano < 2015) {
            tarifaDiaBase *= 0.90;
        }
    }

    // elétrico  ⇒  –20%
    public void calcularPrecoEletrico(String conbustivel) {
        if (conbustivel.equalsIgnoreCase("Elétrico")) {
            tarifaDiaBase *= 0.80;  
        }
    }

    // híbrido  ⇒  –10%
    public void calcularPrecoHybrido(String conbustivel) {
        if (conbustivel.equalsIgnoreCase("Híbrido")) {
            tarifaDiaBase *= 0.90;
        }
    }

    

    // marcas “caras”  ⇒  +20%
    public void calcularPrecoPorMarcaCara(String marca) {
        String m = marca.toLowerCase();                 // normaliza
        if (m.equals("Rolls-Royce") || m.equals("Porsche")
            || m.equals("Lamborghini") || m.equals("Audi")) {
                tarifaDiaBase *= 1.20;  
        }
    }

    // marcas “econômicas”  ⇒  –20%
    public void calcularPreçoMarcasBaixas(String marca) {
        String m = marca.toLowerCase();
        if (m.equals("Fiat") || m.equals("Renault") || m.equals("Citroen")  
            || m.equals("Peugeot") || m.equals("Opel") || m.equals("Kia")
            || m.equals("Dacia")) {
                tarifaDiaBase *= 0.80;
        }
    }
    // Diesel ⇒ +5%
public void calcularPrecoDiesel(String combustivel) {
    if (combustivel.equalsIgnoreCase("Diesel")) {
        tarifaDiaBase *= 1.05;
    }
}

public void calcularDescontoLongaDuracao(int dias) {
    if (dias >= 14) {
        tarifaDiaBase *= 0.85;
    }
}
}


    



    

