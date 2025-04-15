import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static final int PORT = 1050;
    private static int serverCount = 1;
    public static void main(String[] args) {

        boolean serverRunning = true;
        try {
            Map<String, ArrayList<Piscina>> piscine = CSVLettore.caricaPiscine("src/Mappa-delle-piscine-in-Italia.csv");
            GestioneServer gestioneServer = new GestioneServer(piscine);


            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                System.out.println("Server delle Piscine in Italia attivato");


                while (serverRunning) {
                    try {
                        Socket clientSocket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);


                        out.println("Inserisci il comando: ");
                        String comando = in.readLine();
                        out.println("Inserisci il parametro: ");
                        String parametro = in.readLine();


                        String risposta = gestioneServer.gestisciRichiesta(comando, parametro);
                        out.println(risposta);


                        if (comando.equalsIgnoreCase("EXIT")) {
                            clientSocket.close();
                        }


                    } catch (IOException e) {
                        System.err.println("Errore di connessione");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
