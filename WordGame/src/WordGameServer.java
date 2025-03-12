import java.io.*;
import java.net.*;

public class WordGameServer {
    public static void main(String[] args) {
        int port = 1234; // Port to listen on
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Waiting for a client...");
            Socket clientSocket = serverSocket.accept(); // Accept client connection
            System.out.println("Client connected!");

            // Create input/output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Word to guess
            String word = "java";
            out.println("Guess the programming language:");

            String guess;
            while ((guess = in.readLine()) != null) {
                if (guess.equalsIgnoreCase(word)) {
                    out.println("Correct! You win!");
                    break;
                } else {
                    out.println("Wrong! Try again:");
                }
            }

            clientSocket.close(); // Close client connection
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

