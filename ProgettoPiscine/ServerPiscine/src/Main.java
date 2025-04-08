import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static final int PORT = 1050;
    private static int serverCount = 1;
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



        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server delle Piscine in Italia attivato ");
            System.out.println("Server Socket: " + serverSocket);
            boolean serverRunning = true;

            while (serverRunning) {
                Socket clientSocket = null;
                try {
                    clientSocket = serverSocket.accept();
                    System.out.println("Connessione accettata: " + clientSocket);

                    Client clientThread = new Client(clientSocket, serverCount++);
                    new Thread(clientThread).start();
                } catch (IOException e) {
                    System.err.println("Accettazione fallita");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}