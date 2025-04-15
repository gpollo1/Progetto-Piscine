import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GestioneServer {
    private Map<String, ArrayList<Piscina>> piscine;

    public GestioneServer(Map<String, ArrayList<Piscina>> piscine){
        this.piscine = piscine;
    }

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
            case "NUMERO":
                return "Numero totale di piscine: " + contaPiscine();
            case "EXIT":
                return "Chiusura connessione...";
            default:
                return "Comando non riconosciuto.";
        }
    }

        public String selezionaComune(String comune) {
            if (!piscine.containsKey(comune)) return "Nessuna piscina trovata.";
            return piscine.get(comune).toString();
        }

        public String selezionaProvincia(String provincia) {
            List<Piscina> risultato = new ArrayList<>();
            for (ArrayList<Piscina> lista : piscine.values()) {
                for (Piscina piscina : lista) {
                    if (piscina.getProvincia().equalsIgnoreCase(provincia)) {
                        risultato.add(piscina);
                    }
                }
            }
            return risultato.isEmpty() ? "Nessuna piscina trovata." : risultato.toString();
        }

        private String selezionaRegione(String regione) {
            List<Piscina> risultato = new ArrayList<>();
            for (ArrayList<Piscina> lista : piscine.values()) {
                for (Piscina piscina : lista) {
                    if (piscina.getRegione().equalsIgnoreCase(regione)) {
                        risultato.add(piscina);
                    }
                }
            }
            return risultato.isEmpty() ? "Nessuna piscina trovata." : risultato.toString();
        }


        public String selezionaNome(String nome){
            List<Piscina> risultato = new ArrayList<>();
            for (ArrayList<Piscina> lista : piscine.values()) {
                for (Piscina piscina : lista) {
                    if (piscina.getNome().equalsIgnoreCase(nome)) {
                        risultato.add(piscina);
                    }
                }
            }
            return risultato.isEmpty() ? "Nessuna piscina trovata." : risultato.toString();
        }

        public String selezionaAnno(int anno){
            List<Piscina> risultato = new ArrayList<>();
            for (ArrayList<Piscina> lista : piscine.values()) {
                for (Piscina piscina : lista) {
                    if (piscina.getAnnoInserimento() == anno) {
                        risultato.add(piscina);
                    }
                }
            }
            return risultato.isEmpty() ? "Nessuna piscina trovata." : risultato.toString();
        }

        public String selezionaAnnoMaggiore(int anno) {
            List<Piscina> risultato = new ArrayList<>();
            for (ArrayList<Piscina> lista : piscine.values()) {
                for (Piscina piscina : lista) {
                    if (piscina.getAnnoInserimento() > anno) {
                        risultato.add(piscina);
                    }
                }
            }
            return risultato.isEmpty() ? "Nessuna piscina trovata." : risultato.toString();
        }


    private String selezionaAnnoMinore(int anno) {
        List<Piscina> risultato = new ArrayList<>();
        for (ArrayList<Piscina> lista : piscine.values()) {
            for (Piscina piscina : lista) {
                if (piscina.getAnnoInserimento() < anno) {
                    risultato.add(piscina);
                }
            }
        }
        return risultato.isEmpty() ? "Nessuna piscina trovata." : risultato.toString();
    }


    public String selezionaData(String data){
            List<Piscina> risultato = new ArrayList<>();
            for (ArrayList<Piscina> lista : piscine.values()) {
                for (Piscina piscina : lista) {
                    if (piscina.getDataOraInserimento().equalsIgnoreCase(data)) {
                        risultato.add(piscina);
                    }
                }
            }
            return risultato.isEmpty() ? "Nessuna piscina trovata." : risultato.toString();
        }

        public String selezionaMap(String coordinate){
            List<Piscina> risultato = new ArrayList<>();
            for (ArrayList<Piscina> lista : piscine.values()) {
                for (Piscina piscina : lista) {
                    if (piscina.getIdOSM().equalsIgnoreCase(coordinate)) {
                        risultato.add(piscina);
                    }
                }
            }
            return risultato.isEmpty() ? "Nessuna piscina trovata." : risultato.toString();
        }

        public String selezionaLongitudine(Double longitudine){
            List<Piscina> risultato = new ArrayList<>();
            for (ArrayList<Piscina> lista : piscine.values()) {
                for (Piscina piscina : lista) {
                    if (piscina.getLongitudine() == longitudine) {
                        risultato.add(piscina);
                    }
                }
            }
            return risultato.isEmpty() ? "Nessuna piscina trovata." : risultato.toString();
        }

        public String selezionaLatitudine(Double latitudine) {
            List<Piscina> risultato = new ArrayList<>();
            for (ArrayList<Piscina> lista : piscine.values()) {
                for (Piscina piscina : lista) {
                    if (piscina.getLatitudine() == latitudine) {
                        risultato.add(piscina);
                    }
                }
            }
            return risultato.isEmpty() ? "Nessuna piscina trovata." : risultato.toString();
        }


        public int contaPiscine() {
            int count = 0;
            for (ArrayList<Piscina> lista : piscine.values()) {
                count += lista.size();
            }
            return count;
        }

}
