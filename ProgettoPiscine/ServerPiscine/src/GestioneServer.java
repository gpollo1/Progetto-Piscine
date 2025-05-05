import java.util.ArrayList;
import java.util.List;
/**
 * Gestisce le richieste del server in base ai comandi ricevuti dai client.
 *
 * Analizza i comandi, interroga la lista di piscine e restituisce una risposta formattata.
 */
public class GestioneServer {
    private ArrayList<Piscina> piscine;

    public GestioneServer(ArrayList<Piscina> piscine) {
        this.piscine = piscine;
    }

    /**
     * Elabora il comando ricevuto dal client e restituisce la risposta.
     *
     * @param comando comando richiesto (es. SELEZIONA-COMUNE).
     * @param parametro parametro associato al comando.
     * @return risposta formattata da inviare al client.
     */
    public String gestisciRichiesta(String comando, String parametro) {
        switch (comando.toUpperCase()) {
            case "SELEZIONA-COMUNE":
                return selezionaComune(parametro);
            case "SELEZIONA-PROVINCIA":
                return selezionaProvincia(parametro);
            case "SELEZIONA-REGIONE":
                return selezionaRegione(parametro);
            case "SELEZIONA-NOME":
                return selezionaNome(parametro);
            case "SELEZIONA-ANNO":
                return selezionaAnno(Integer.parseInt(parametro));
            case "SELEZIONA-ANNOMAGGIORE":
                return selezionaAnnoMaggiore(Integer.parseInt(parametro));
            case "SELEZIONA-ANNOMINORE":
                return selezionaAnnoMinore(Integer.parseInt(parametro));
            case "SELEZIONA-DATA":
                return selezionaData(parametro);
            case "SELEZIONA-MAP":
                return selezionaMap(parametro);
            case "SELEZIONA-LONGITUDINE":
                return selezionaLongitudine(Double.parseDouble(parametro));
            case "SELEZIONA-LATITUDINE":
                return selezionaLatitudine(Double.parseDouble(parametro));
            case "NUMERO-TOT":
                return "Numero totale di piscine: " + contaPiscine();
            case "EXIT":
                return "Chiusura connessione...";
            default:
                return "Comando non riconosciuto.";
        }
    }

    private String selezionaComune(String comune) {
        return filtraPiscine(p -> p.getComune().equalsIgnoreCase(comune));
    }

    private String selezionaProvincia(String provincia) {
        return filtraPiscine(p -> p.getProvincia().equalsIgnoreCase(provincia));
    }

    private String selezionaRegione(String regione) {
        return filtraPiscine(p -> p.getRegione().equalsIgnoreCase(regione));
    }

    private String selezionaNome(String nome) {
        return filtraPiscine(p -> p.getNome().equalsIgnoreCase(nome));
    }

    private String selezionaAnno(int anno) {
        return filtraPiscine(p -> p.getAnnoInserimento() == anno);
    }

    private String selezionaAnnoMaggiore(int anno) {
        return filtraPiscine(p -> p.getAnnoInserimento() > anno);
    }

    private String selezionaAnnoMinore(int anno) {
        return filtraPiscine(p -> p.getAnnoInserimento() < anno);
    }

    private String selezionaData(String data) {
        return filtraPiscine(p -> p.getDataOraInserimento().contains(data));
    }

    private String selezionaMap(String mapId) {
        return filtraPiscine(p -> p.getIdOSM().equalsIgnoreCase(mapId));
    }

    private String selezionaLongitudine(double longitudine) {
        return filtraPiscine(p -> p.getLongitudine() == longitudine);
    }

    private String selezionaLatitudine(double latitudine) {
        return filtraPiscine(p -> p.getLatitudine() == latitudine);
    }

    //è un filtro prende una piscina e resituisce true o false
    private String filtraPiscine(java.util.function.Predicate<Piscina> criterio) {
        List<Piscina> risultato = new ArrayList<>();
        for (Piscina piscina : piscine) {
            if (criterio.test(piscina)) {
                risultato.add(piscina);
            }
        }
        if (risultato.isEmpty()) {
            return "Nessuna piscina trovata.";
        } else {
            return formattaLista(risultato);
        }
    }

    public int contaPiscine() {
        return piscine.size();
    }

    private String formattaLista(List<Piscina> lista) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (Piscina p : lista) {
            sb.append("Piscina ").append(count).append(": [")
                    .append(p.stampaCompatta()).append("]\n");
            count++;
        }
        return sb.toString().trim();
    }
}
