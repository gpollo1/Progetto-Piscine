import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
/**
 * Classe principale per l'avvio del server.
 *
 * Legge i dati dal file CSV delle piscine, li carica in una struttura dati (ArrayList),
 * apre una socket in ascolto su una porta specifica, riceve richieste dai client,
 * le elabora tramite la classe GestioneServer e restituisce le risposte.
 */
public class MainServer {
    public static final int PORT = 1050;

    public static void main(String[] args) {
        try {
            ArrayList<Piscina> piscine = CSVLettore.caricaPiscine("src/Mappa-delle-piscine-in-Italia.csv");
            GestioneServer gestioneServer = new GestioneServer(piscine);

            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                System.out.println("Server delle Piscine in Italia attivato");

                while (true) {
                    try {
                        Socket clientSocket = serverSocket.accept();
                        new Thread(() -> gestisciCliente(clientSocket, gestioneServer)).start();
                    } catch (IOException e) {
                        System.err.println("Errore di connessione con il client.");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Errore nell'avvio del server", e);
        }
    }

    /**
     * Gestisce la comunicazione con un singolo client.
     *
     * @param clientSocket socket della connessione col client.
     * @param gestioneServer oggetto contenente la logica per l'elaborazione dei comandi.
     */
    private static void gestisciCliente(Socket clientSocket, GestioneServer gestioneServer) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            out.println("Benvenuto nel server delle piscine!");
            out.println("Comandi disponibili:");
            out.println("- SELEZIONA-COMUNE");
            out.println("- SELEZIONA-PROVINCIA");
            out.println("- SELEZIONA-REGIONE");
            out.println("- SELEZIONA-NOME");
            out.println("- SELEZIONA-ANNO");
            out.println("- SELEZIONA-ANNOMAGGIORE");
            out.println("- SELEZIONA-ANNOMINORE");
            out.println("- SELEZIONA-DATA");
            out.println("- SELEZIONA-MAP");
            out.println("- SELEZIONA-LONGITUDINE");
            out.println("- SELEZIONA-LATITUDINE");
            out.println("- NUMERO-TOT");
            out.println("- EXIT");
            out.println("Digita un comando e premi INVIO.");
            out.println("Benvenuto nel server delle piscine!");
            out.println("Digita un comando e premi INVIO.");
            out.println("FINE_RISPOSTA");

            while (true) {
                String comando = in.readLine();
                if (comando == null || comando.equalsIgnoreCase("EXIT")) {
                    out.println("Chiusura connessione...");
                    break;
                }

                // Chiedere il parametro se necessario
                if (!comando.equalsIgnoreCase("NUMERO-TOT")) {
                    out.println("Inserisci il parametro richiesto:");
                }

                String parametro = in.readLine();
                String risposta = gestioneServer.gestisciRichiesta(comando, parametro);

                for (String linea : risposta.split("\n")) {
                    out.println(linea);
                }
                out.println("FINE_RISPOSTA");  // Segnale di fine risposta
            }
        } catch (IOException e) {
            System.err.println("Errore nella gestione del client.");
        }
    }
}
