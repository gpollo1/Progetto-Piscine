import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVLettore {
    //ho deciso di utilizzare per la memorizzazione delle piscine una hash map con la chiave il comune e all'interno una lista di oggetti piscine
    public static Map<String, ArrayList<Piscina>> caricaPiscine(String percorso) throws IOException {
        Map<String, ArrayList<Piscina>> mappaPiscine = new HashMap<>();
        BufferedReader lettore = new BufferedReader(new FileReader(percorso));
        String linea;

        // Leggi la prima riga (intestazione) e ignorala
        lettore.readLine();

        while ((linea = lettore.readLine()) != null) {
            String[] campi = linea.split(";");

            // Crea un oggetto Piscina
            Piscina piscina = new Piscina(
                    campi[0],  // Comune
                    campi[1],  // Provincia
                    campi[2],  // Regione
                    campi[3],  // Nome
                    campi[4].isEmpty() ? 0 : Integer.parseInt(campi[4]),  // Anno, se vuoto imposta 0
                    campi[5],  // Data e ora
                    campi[6],  // Identificatore OpenStreetMap
                    Double.parseDouble(campi[7]),  // Longitudine
                    Double.parseDouble(campi[8])   // Latitudine
            );

            // Aggiungi la piscina alla mappa sotto il Comune
            if (!mappaPiscine.containsKey(campi[0])) {
                mappaPiscine.put(campi[0], new ArrayList<>());
            }
            mappaPiscine.get(campi[0]).add(piscina);
        }

        lettore.close();
        return mappaPiscine;
    }
}
