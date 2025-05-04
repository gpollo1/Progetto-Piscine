import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
/**
 * Interfaccia grafica del client che permette di inviare comandi al server
 * e visualizzare i risultati.
 *
 * La GUI contiene un menu a tendina per selezionare i comandi, un campo di testo
 * per inserire parametri e un'area di testo per visualizzare le risposte.
 */
public class PiscineClientGUI extends JFrame {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 1050;

    private JComboBox<String> comandoBox;
    private JTextField parametroField;
    private JTextArea outputArea;
    private JButton inviaButton, esciButton;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public PiscineClientGUI() {
        setTitle("Client Piscine Italia");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Font più grande
        Font fontGrande = new Font("SansSerif", Font.PLAIN, 16);

        String[] comandi = {
                "SELEZIONA-COMUNE", "SELEZIONA-PROVINCIA", "SELEZIONA-REGIONE", "SELEZIONA-NOME",
                "SELEZIONA-ANNO", "SELEZIONA-ANNOMAGGIORE", "SELEZIONA-ANNOMINORE",
                "SELEZIONA-DATA", "SELEZIONA-MAP", "SELEZIONA-LONGITUDINE", "SELEZIONA-LATITUDINE",
                "NUMERO-TOT", "EXIT"
        };

        comandoBox = new JComboBox<>(comandi);
        comandoBox.setFont(fontGrande);

        parametroField = new JTextField(15);
        parametroField.setFont(fontGrande);

        outputArea = new JTextArea();
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        inviaButton = new JButton("Invia");
        inviaButton.setFont(fontGrande);
        esciButton = new JButton("Esci");
        esciButton.setFont(fontGrande);

        JLabel comandoLabel = new JLabel("Comando:");
        comandoLabel.setFont(fontGrande);
        JLabel parametroLabel = new JLabel("Parametro:");
        parametroLabel.setFont(fontGrande);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
        topPanel.add(comandoLabel);
        topPanel.add(comandoBox);
        topPanel.add(parametroLabel);
        topPanel.add(parametroField);
        topPanel.add(inviaButton);
        topPanel.add(esciButton);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        inviaButton.addActionListener(e -> inviaComando());
        esciButton.addActionListener(e -> {
            comandoBox.setSelectedItem("EXIT");
            inviaComando();
        });

        connettiAlServer();
    }

    private void connettiAlServer() {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String linea;
            while (!(linea = in.readLine()).equals("FINE_RISPOSTA")) {
                outputArea.append(linea + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Errore di connessione al server.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void inviaComando() {
        String comando = comandoBox.getSelectedItem().toString();
        String parametro = parametroField.getText().trim();

        outputArea.setText("");

        try {
            out.println(comando);

            if (!comando.equals("NUMERO-TOT") && !comando.equals("EXIT")) {
                out.println(parametro);
            } else {
                out.println(); // parametro vuoto
            }

            String risposta;
            while ((risposta = in.readLine()) != null && !risposta.equals("FINE_RISPOSTA")) {
                outputArea.append(risposta + "\n");
            }

            if (comando.equals("EXIT")) {
                chiudiConnessione();
                System.exit(0);
            }
        } catch (IOException e) {
            outputArea.setText("Errore durante l'invio del comando.");
        }
    }

    private void chiudiConnessione() {
        try {
            if (socket != null) socket.close();
        } catch (IOException e) {
        }
    }
}
