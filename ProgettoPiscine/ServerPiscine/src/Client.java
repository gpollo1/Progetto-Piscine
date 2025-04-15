import java.io.*;
import java.net.*;

public class Client implements Runnable {
    final String EXIT = "EXIT";
    final String END = "END";



    private Socket clientSocket;
    private int serverId;


    public Client(Socket socket, int serverId) {
        this.clientSocket = socket;
        this.serverId = serverId;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);

            out.print("EXIT per chiudere la connessione, END per chiudere il server: ");
            out.flush();

            while (true) {
                String str = in.readLine();
                if (str.equals(END)) {
                    System.out.println("Client disconnesso: " + clientSocket);
                    break;
                } else if (str.equals(EXIT)) {
                    System.out.println("Ricevuto EXIT: chiusura del server...");
                    out.close();
                    in.close();
                    clientSocket.close();
                    System.exit(0);
                }
                //Metto il menu
                mostraFunzioniHome();
                out.println(str.toUpperCase());
                System.out.println("Server " + serverId + " Echoing: " + str.toUpperCase());
            }

            out.close();
            in.close();
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Error with client communication");
        }
    }

        public void mostraFunzioniHome(){
            System.out.println("Comandi dalla HOME: ");
            System.out.println("Seleziona-Comune: info piscine del comune");
            System.out.println("Seleziona-Provincia: info piscine della provincia");
            System.out.println("Seleziona-Regione: info piscine della regione");
            System.out.println("Seleziona-Nome: info piscine con nome");
            System.out.println("Seleziona-Anno: info piscine anno");
            System.out.println("Seleziona-AnnoMaggiore: info piscine ....");
            System.out.println("Seleziona-AnnoMinore: info piscine ....");
            System.out.println("Seleziona-Data: info piscine data");
            System.out.println("Seleziona-Map: info piscine nella mappa");
            System.out.println("Seleziona-Longitudine: info piscine nella longitudine");
            System.out.println("Seleziona-Latitudine: info piscine nella latitudine");
            System.out.println();
            System.out.println("EXIT: chiude informazione con il server");
            System.out.println("END: chiude il server");
        }

    public void mostraFunzioniCaratteristica(String s){
        System.out.println("Comandi all'interno" + s + ":" );
        System.out.println("Indietro: torna indietro");
        System.out.println("Numero: numero piscine di una caratteristica scelta");
        System.out.println("EXIT: chiude informazione con il server");
        System.out.println("END: chiude il server");
    }

    public void menu(String scelta){
        switch (scelta.toUpperCase()){
            default:
                System.out.println("L'opzione selezionata non è disponibile, perchè non è presente");
                break;
        }
    }
}
