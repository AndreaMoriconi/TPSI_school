import java.io.*;
import java.net.*;
import java.util.Scanner;
public class ClientStr {
  String nomeServer ="localhost";                  // indirizzo server locale  
  int portaServer   = 6789;                        // porta x servizio data e ora
  Socket miosocket;                                
  Scanner tastiera;                         // buffer per l'input da tastiera
  //String stringaUtente;                           // stringa inserita da utente
  int primoOperando;
  int secondoOperando;
  char operazione;

  //String stringaRicevutaDalServer;                 // stringa ricevuta dal server
  double operazioneCalcolataDalServer;
  DataOutputStream outVersoServer;                 // stream di output
  BufferedReader inDalServer;                      // stream di input 

  public void comunica() {
    try                      // leggo una riga
    {
      System.out.println("inserisci il primo oerando:");
      primoOperando = Integer.parseInt(tastiera.readLine());
      //la spedisco al server 
      System.out.println("5 ... invio la stringa al server e attendo ...");
      outVersoServer.writeInt(primoOperando);
      System.out.println(primoOperando);
      //leggo la risposta dal server 
      operazioneCalcolataDalServer=Double.parseDouble(inDalServer.readLine());
      System.out.println(operazioneCalcolataDalServer);     //2controlla sia tornato corretto
      System.out.println("8 ... risposta dal server "+'\n'+operazioneCalcolataDalServer);
      // chiudo la connessione
      System.out.println("9 CLIENT: termina elaborazione e chiude connessione" );
      miosocket.close(); 
    } 
    catch (Exception e) 
    {
      System.out.println(e.getMessage());
      System.out.println("Errore durante la comunicazione col server!");
      System.exit(1);
    }
  }
  public Socket connetti(){
    System.out.println("2 CLIENT partito in esecuzione ...");
    try 
    {
      // per l'input da tastiera
      tastiera = new Scanner
      // creo un socket  
      miosocket = new Socket(nomeServer,portaServer);
      // miosocket = new Socket(InetAddress.getLocalHost(), 6789);
      // associo due oggetti al socket per effettuare la scrittura e la lettura 
      outVersoServer = new DataOutputStream(miosocket.getOutputStream());
      inDalServer    = new BufferedReader(new InputStreamReader (miosocket.getInputStream()));
    } 
    catch (UnknownHostException e){
        System.err.println("Host sconosciuto"); } 
    catch (Exception e) 
    {
      System.out.println(e.getMessage());
      System.out.println("Errore durante la connessione!");
      System.exit(1);
    }
    return miosocket;
  }
  public static void main(String args[]) {
   ClientStr cliente = new ClientStr();
   cliente.connetti();
   cliente.comunica();
  }   
}


