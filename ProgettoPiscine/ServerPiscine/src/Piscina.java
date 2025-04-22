public class Piscina {
    private String comune;
    private String provincia;
    private String regione;
    private String nome;
    private int annoInserimento;
    private String dataOraInserimento;
    private String idOSM;
    private double longitudine;
    private double latitudine;

    public Piscina(String comune, String provincia, String regione, String nome,
                   int annoInserimento, String dataOraInserimento,
                   String idOSM, double longitudine, double latitudine) {
        this.comune = comune;
        this.provincia = provincia;
        this.regione = regione;
        this.nome = nome;
        this.annoInserimento = annoInserimento;
        this.dataOraInserimento = dataOraInserimento;
        this.idOSM = idOSM;
        this.longitudine = longitudine;
        this.latitudine = latitudine;
    }

    // Getters and Setters
    public String getComune() { return comune; }
    public String getProvincia() { return provincia; }
    public String getRegione() { return regione; }
    public String getNome() { return nome; }
    public int getAnnoInserimento() { return annoInserimento; }
    public String getDataOraInserimento() { return dataOraInserimento; }
    public String getIdOSM() { return idOSM; }
    public double getLongitudine() { return longitudine; }
    public double getLatitudine() { return latitudine; }

    @Override
    public String toString() {
        return "Comune: " + comune +
                ", Provincia: " + provincia +
                ", Regione: " + regione +
                ", Nome: " + nome +
                ", Anno Inserimento: " + annoInserimento +
                ", Data/Ora: " + dataOraInserimento +
                ", ID OSM: " + idOSM +
                ", Longitudine: " + longitudine +
                ", Latitudine: " + latitudine;
    }

    public String stampaCompatta() {
        return comune + ";" +
                provincia + ";" +
                regione + ";" +
                nome + ";" +
                annoInserimento + ";" +
                dataOraInserimento + ";" +
                idOSM + ";" +
                longitudine + ";" +
                latitudine;
    }
}
