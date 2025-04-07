import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            // Carica i dati dal file CSV
            Map<String, ArrayList<Piscina>> piscine = CSVLettore.caricaPiscine("src/Mappa-delle-piscine-in-Italia.csv");

            // Visualizza tutte le piscine per ogni Comune
            for (Map.Entry<String, ArrayList<Piscina>> entry : piscine.entrySet()) {
                String comune = entry.getKey();
                ArrayList<Piscina> piscineNelComune = entry.getValue();

                System.out.println("Comune: " + comune);
                for (Piscina piscina : piscineNelComune) {
                    System.out.println("  - " + piscina.getNome() + " (" + piscina.getAnnoInserimento() + ")");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}