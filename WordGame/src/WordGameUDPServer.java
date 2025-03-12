import java.net.*;

public class WordGameUDPServer {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(1234)) {
            byte[] buffer = new byte[1024];
            String word = "java";

            System.out.println("UDP Server is running...");
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);

            while (true) {
                // Receive the client's guess
                socket.receive(request);
                String guess = new String(request.getData(), 0, request.getLength()).trim();

                // Determine response
                String response = guess.equalsIgnoreCase(word) ? "Correct! You win!" : "Wrong! Try again!";
                
                // Send the response
                DatagramPacket reply = new DatagramPacket(
                    response.getBytes(), 
                    response.getBytes().length, 
                    request.getAddress(), 
                    request.getPort()
                );
                socket.send(reply);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
