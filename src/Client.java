import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;

        Scanner scanner = new Scanner(System.in);
        try {
            Socket socket = new Socket("127.0.0.1", 7500);

            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

           while (true) {

               System.out.println("Choice your currency");
               System.out.println("[1] tenge");
               System.out.println("[2] rub");
               System.out.println("[3] eur");
               System.out.println("[4] EXIT");
               int choice = scanner.nextInt();
               String currency = null;
               ApiJsonData apiJsonData = null;
               if (choice == 1) {
                   currency = "KZT";
               } else if (choice == 2) {
                   currency = "RUB";
               } else if (choice == 3) {
                   currency = "EUR";
               } else if (choice == 4) {
                   break;
               }
               apiJsonData = new ApiJsonData(currency);

               objectOutputStream.writeObject(apiJsonData);

               objectOutputStream.flush();
               objectOutputStream.reset();

               ReqAndResponse reqAndResponse = (ReqAndResponse) objectInputStream.readObject();

               System.out.println(currency + " " + reqAndResponse.getMoney());

           }
           socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
