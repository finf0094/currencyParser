import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

// https://api.currencyfreaks.com/v2.0/rates/latest?apikey=0c940eb9b9334ca488b0db09606d1cf0
public class Server {
    public static void main(String[] args)  {

        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;


        try {
            ServerSocket serverSocket = new ServerSocket(7500);
            System.out.println("Server Starts");

            while (true) {
                Socket clientSocket = serverSocket.accept(); // wait clients
                System.out.println("Client connected");

                objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

                ObjectInputStream finalObjectInputStream = objectInputStream;
                ObjectOutputStream finalObjectOutputStream = objectOutputStream;
                new Thread(() -> {
                    try {
                        ApiJsonData apiJsonData = (ApiJsonData) finalObjectInputStream.readObject();
                        finalObjectOutputStream.writeObject(apiJsonData.getResult());
                        finalObjectOutputStream.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }).start();

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
