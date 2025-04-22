import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {
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

    private static void gestisciCliente(Socket clientSocket, GestioneServer gestioneServer) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            while (true) {
                String comando = in.readLine();
                if (comando == null || comando.equalsIgnoreCase("EXIT")) {
                    out.println("Chiusura connessione...");
                    break;
                }

                String parametro = in.readLine();
                String risposta = gestioneServer.gestisciRichiesta(comando, parametro);

                for (String linea : risposta.split("\n")) {
                    out.println(linea);
                }
                out.println("FINE_RISPOSTA"); // Token esplicito per indicare la fine della risposta
            }
        } catch (IOException e) {
            System.err.println("Errore nella gestione del client.");
        }
    }
}
