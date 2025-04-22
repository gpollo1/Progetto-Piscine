//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {
    public static final String SERVER_ADDRESS = "localhost";
    public static final int SERVER_PORT = 1050;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Benvenuto nel client delle piscine.");

            while (true) {
                mostraComandiDisponibili();
                System.out.print("\nInserisci il comando: ");
                String comando = userInput.readLine().toUpperCase();
                out.println(comando);

                if (comando.equals("EXIT")) {
                    System.out.println("Chiusura connessione...");
                    break;
                }

                System.out.print("Inserisci il parametro (se richiesto): ");
                String parametro = userInput.readLine();
                out.println(parametro);

                System.out.println("\nRisultato:");
                String risposta;
                while ((risposta = in.readLine()) != null && !risposta.equals("FINE_RISPOSTA")) {
                    System.out.println(risposta);
                }
            }
        } catch (IOException e) {
            System.err.println("Errore di connessione al server.");
            e.printStackTrace();
        }
    }

    private static void mostraComandiDisponibili() {
        System.out.println("\nComandi disponibili:");
        System.out.println("- SELEZIONA-COMUNE      → Piscine in un Comune specifico");
        System.out.println("- SELEZIONA-PROVINCIA   → Piscine in una Provincia specifica");
        System.out.println("- SELEZIONA-REGIONE     → Piscine in una Regione specifica");
        System.out.println("- SELEZIONA-NOME        → Piscine con un nome specifico");
        System.out.println("- SELEZIONA-ANNO        → Piscine fondate in un determinato anno");
        System.out.println("- SELEZIONA-ANNOMAGGIORE → Piscine fondate prima di un certo anno");
        System.out.println("- SELEZIONA-ANNOMINORE  → Piscine fondate dopo un certo anno");
        System.out.println("- SELEZIONA-DATA        → Piscine fondate in una data specifica");
        System.out.println("- SELEZIONA-MAP         → Piscine in una determinata mappa");
        System.out.println("- SELEZIONA-LONGITUDINE → Piscine con una certa longitudine");
        System.out.println("- SELEZIONA-LATITUDINE  → Piscine con una certa latitudine");
        System.out.println("- NUMERO-TOT                → Numero totale di piscine (o con specifica caratteristica)");
        System.out.println("- EXIT                  → Chiudi la connessione");
    }
}