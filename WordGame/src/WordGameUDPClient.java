import java.net.*;
import java.io.*;

public class WordGameUDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("localhost");
        byte[] buffer = new byte[1024];
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Connected to the UDP server. Guess the word!");

        while (true) {
            String guess = userInput.readLine();
            DatagramPacket request = new DatagramPacket(guess.getBytes(), guess.length(), serverAddress, 1234);
            socket.send(request);

            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            socket.receive(reply);
            String response = new String(reply.getData(), 0, reply.getLength());
            System.out.println(response);

            if (response.contains("Correct")) {
                break;
            }
        }
        socket.close();
    }
}
