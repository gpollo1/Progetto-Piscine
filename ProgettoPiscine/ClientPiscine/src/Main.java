//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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