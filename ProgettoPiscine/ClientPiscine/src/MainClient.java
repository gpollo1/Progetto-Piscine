//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;
/**
 * Classe principale per l'avvio del client con interfaccia grafica.
 *
 * Crea un'interfaccia utente che permette di selezionare comandi,
 * inviarli al server tramite socket, ricevere risposte e mostrarle all'utente.
 */
public class MainClient {
    public static final String SERVER_ADDRESS = "127.0.0.1";
    public static final int SERVER_PORT = 1050;
    /**
     * Metodo principale. Avvia l'interfaccia grafica del client.
     *
     * @param args argomenti da linea di comando (non utilizzati)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PiscineClientGUI client = new PiscineClientGUI();
            client.setVisible(true);
        });
    }
}