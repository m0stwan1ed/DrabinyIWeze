import java.io.*;
import java.net.*;

public class drabinyiwezeserwer 
{
	private static ServerSocket server;
	private static final int PORT = 2345;
	public static void main(String[] args ) 
	{
		int nrPolaczenia = 1;
		try 
		{
			server = new ServerSocket(PORT);
			System.out.println("Serwer uruchomiony na porcie: " + PORT);
			while (true) 
			{
				Socket socket = server.accept();
				System.out.println("Polaczenie numer: " + nrPolaczenia);
				new Obsluga(socket, nrPolaczenia).start();
				nrPolaczenia++;
			}
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
}

class Obsluga extends Thread 
{
	private Socket socket;
	private int nrPolaczenia;
	
	public Obsluga(Socket socket, int nrPolaczenia) 
	{
		this.socket = socket;
		this.nrPolaczenia = nrPolaczenia;
	}
	
	public void run() 
	{
		try 
		{
			BufferedReader wejscie = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter wyjscie = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			wyjscie.println("Serwer wita uzytkownika! Komenda /end konczy polaczenie!");
			socket.close();
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
}