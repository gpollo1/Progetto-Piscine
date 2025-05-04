//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;
/**
 * Classe principale per l'avvio del client con interfaccia grafica.
 *
 * Crea un'interfaccia utente che permette di selezionare comandi,
 * inviarli al server tramite socket, ricevere risposte e mostrarle all'utente.
 */
public class Main {
    public static final String SERVER_ADDRESS = "localhost";
    public static final int SERVER_PORT = 1050;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PiscineClientGUI client = new PiscineClientGUI();
            client.setVisible(true);
        });
    }
}