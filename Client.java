import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Client {
    String hostName = "localhost";
    int serverPort = 6789;
    Socket mySocket;
    //Bomb bomb;
    int timer=10;
    DataOutputStream outVersoServer;                 
    DataInputStream inDalServer;

    public static void main(String[] args) {
        Client cliente = new Client();
        cliente.connetti();
        cliente.comunica();

    }

    public Socket connetti(){
        System.out.println("client started");
        //bomb = new Bomb(mysocket);
        
        try 
        {
            mySocket = new Socket(InetAddress.getLocalHost(), serverPort);
        // miosocket = new Socket(InetAddress.getLocalHost(), 6789);
        // associo due oggetti al socket per effettuare la scrittura e la lettura 
            outVersoServer = new DataOutputStream(mySocket.getOutputStream());
            inDalServer    = new DataInputStream(mySocket.getInputStream());
        } 
        catch (UnknownHostException e){
            System.err.println("Host sconosciuto"); } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione!");
            System.exit(1);
        }
    
        return mySocket;
    }

    public void comunica(){
        try{
        System.out.println(timer);
        outVersoServer.writeInt(timer);
        timer = inDalServer.readInt();
        System.out.println("returned"+timer);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server!");
            System.exit(1);
        }
    }
}

/*public class Bomb{
    DataOutputStream outVersoServer;                 
    BufferedReader inDalServer;
    int timer;
    public Bomb (Socket mysocket){
        outVersoServer = new DataOutputStream(mysocket.getOutputStream());
        inDalServer    = new BufferedReader(new InputStreamReader (mysocket.getInputStream()));
    }
    public void getBombFromServer(){
        timer = inDalServer.readInt();
    }
    public void launchBombToServer(){
        outVersoServer.writeInt(timer);
    }
}*/
