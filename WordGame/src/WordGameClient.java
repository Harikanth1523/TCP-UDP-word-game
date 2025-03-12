import java.io.*;
import java.net.*;

public class WordGameClient {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Server IP address
        int port = 1234; // Port of the server

        try (Socket socket = new Socket(serverAddress, port)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Connected to the server.");
            System.out.println(in.readLine()); // Read server's message

            String guess;
            while ((guess = userInput.readLine()) != null) {
                out.println(guess); // Send guess to server
                String response = in.readLine(); // Read server's response
                System.out.println(response);

                if (response.contains("Correct")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
