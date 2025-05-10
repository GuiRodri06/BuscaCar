package model.entities.carros;

public class carrosCondi {

    /* ---------- atributos ---------- */
    private String modelo;
    private String submodelo;
    private int km;
    private int ano;
    private String conbustivel;
    private int preçoCompra;          // mantive o nome original
    private double tarifaHoraBase;

    /* ---------- construtor ---------- */
    public carrosCondi(String modelo, String submodelo, int km,
                       int ano, String conbustivel, int preçoCompra) {
        this.modelo       = modelo;
        this.submodelo    = submodelo;
        this.km           = km;
        this.ano          = ano;
        this.conbustivel  = conbustivel;
        this.preçoCompra  = preçoCompra;

        /* conversão compra → hora */
        this.tarifaHoraBase = preçoCompra * 0.0005;
    }

    /* ---------- getters / setters originais ---------- */
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

    public double getTarifaHoraBase()       { return tarifaHoraBase; }

    /* ---------- regras de preço (agora atuam em tarifaHoraBase) ---------- */

    // >100 000 km  ⇒  –25 %
    public void calcularPrecoKm100000(int km) {
        if (km > 100_000) {
            tarifaHoraBase *= 0.75;   // sem cast, mantém centavos
        }
    }

    // >10 anos  ⇒  –10 %
    public void calcularPrecoAno2015(int ano) {
        if (ano > 2015) {
            tarifaHoraBase *= 0.90;
        }
    }

    // elétrico  ⇒  –20 %
    public void calcularPrecoEletrico(String conbustivel) {
        if (conbustivel.equalsIgnoreCase("eletrico")) {
            tarifaHoraBase *= 0.80;
        }
    }

    // híbrido  ⇒  –10 %
    public void calcularPrecoHybrido(String conbustivel) {
        if (conbustivel.equalsIgnoreCase("hybrido")) {
            tarifaHoraBase *= 0.90;
        }
    }

    // motorista <25 anos  ⇒  +15 %
    public void calcularPrecoPorIdadeMotorista(int idadeMotorista) {
        if (idadeMotorista < 25) {
            tarifaHoraBase *= 1.15;
        }
    }

    // marcas “caras”  ⇒  +20 %
    public void calcularPrecoPorMarcaCara(String marca) {
        String m = marca.toLowerCase();                 // normaliza
        if (m.equals("rolls-royce") || m.equals("porsche")
            || m.equals("lamborghini") || m.equals("audi")) {
            tarifaHoraBase *= 1.20;
        }
    }

    // marcas “econômicas”  ⇒  –20 %
    public void calcularPreçoMarcasBaixas(String marca) {
        String m = marca.toLowerCase();
        if (m.equals("fiat") || m.equals("renault") || m.equals("citroen")
            || m.equals("peugeot") || m.equals("opel") || m.equals("kia")
            || m.equals("dacia")) {
            tarifaHoraBase *= 0.80;
        }
    }
}


    



    

