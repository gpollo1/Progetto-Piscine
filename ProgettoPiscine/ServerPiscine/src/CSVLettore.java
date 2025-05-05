import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Classe per la lettura di un file CSV contenente informazioni sulle piscine.
 *
 * Legge il file e crea una lista di oggetti `Piscina` con i dati letti.
 */
public class CSVLettore {
    /**
     * Carica le piscine da un file CSV delimitato da punto e virgola.
     *
     * @param percorso percorso al file CSV.
     * @return lista di oggetti Piscina.
     * @throws IOException se si verifica un errore nella lettura del file.
     */
    public static ArrayList<Piscina> caricaPiscine(String percorso) throws IOException {
        ArrayList<Piscina> piscine = new ArrayList<>();
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

            piscine.add(piscina);
        }

        lettore.close();
        return piscine;
    }
}
