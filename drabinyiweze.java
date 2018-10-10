import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.net.*;


class Przyciski extends JPanel {
	public static JButton nowagra,rzuckostka;
	public static JTextField napis;

	
	public Przyciski () {
		nowagra = new JButton("Nowa gra");
		rzuckostka=new JButton("Rzuc kostka");
		add(nowagra);
		add(rzuckostka);
		napis=new JTextField("Wyrzucono liczbe:                                                           ");
		add(napis);
	}
}


class Plansza extends JPanel {
	
	private Image img;
	public int pozycja=1;
	Random kostka=new Random(); 

	public Plansza() {
	
		Obslugarzutu obslugarzutu=new Obslugarzutu();
		Przyciski.rzuckostka.addActionListener(obslugarzutu);
		Obsluganowejgry obsluganowejgry=new Obsluganowejgry();
		Przyciski.nowagra.addActionListener(obsluganowejgry);

	}
	
	public void paintComponent(Graphics g) {
	super.paintComponent(g);
	try {
	img = ImageIO.read(new File("snakesandladders.png"));
	} catch (IOException e) {}
	g.drawImage(img, 0,0,this);
	g.setColor(Color.GREEN);
		if((pozycja/10)%2==0) {

			if(pozycja%10==0) {
				g.fillOval(50-10-25, 525-10-(pozycja/10)*50, 20, 20);    //pozycja=(i-1)*10+j;
			}
			else g.fillOval((pozycja%10)*50-10-25, 525-10-(pozycja/10+1)*50, 20, 20);  
		
		} 
		else if ((pozycja/10)%2==1){
			if(pozycja%10==0) {
			g.fillOval(10*50-10-25, 525-10-(pozycja/10)*50, 20, 20);    //pozycja=(i-1)*10+j;
			}
			else g.fillOval((11-pozycja%10)*50-10-25, 525-10-(pozycja/10+1)*50, 20, 20);  
		}
	}
	
	class Obslugarzutu implements ActionListener {
		
	public void actionPerformed (ActionEvent e) {
		int rzutkostka=kostka.nextInt(6)+1;
		pozycja=pozycja+rzutkostka;
		Przyciski.napis.setText("Wyrzucono liczbe: "+rzutkostka);
		repaint();
		if (pozycja>=100) {
			pozycja=100;
			Przyciski.napis.setText("Brawo! Wygrales!");
		}
		if (pozycja==3) {
			Przyciski.napis.setText("Wyrzucono liczbe: "+rzutkostka+" Trafiles na drabine!");
			pozycja=21;
		}
		if (pozycja==8) {
			Przyciski.napis.setText("Wyrzucono liczbe: "+rzutkostka+" Trafiles na drabine!");
			pozycja=30;
		}
		if (pozycja==28) {
			Przyciski.napis.setText("Wyrzucono liczbe: "+rzutkostka+" Trafiles na drabine!");
			pozycja=84;
		}
		if (pozycja==58) {
			Przyciski.napis.setText("Wyrzucono liczbe: "+rzutkostka+" Trafiles na drabine!");
			pozycja=77;
		}
		if (pozycja==75) {
			Przyciski.napis.setText("Wyrzucono liczbe: "+rzutkostka+" Trafiles na drabine!");
			pozycja=86;
		}
		if (pozycja==80) {
			Przyciski.napis.setText("Wyrzucono liczbe: "+rzutkostka+" Trafiles na drabine i wygrales!");
			pozycja=100;
		}
		if (pozycja==90) {
			Przyciski.napis.setText("Wyrzucono liczbe: "+rzutkostka+" Trafiles na drabine!");
			pozycja=91;
		}
		if (pozycja==17) {
			Przyciski.napis.setText("Wyrzucono liczbe: "+rzutkostka+" Trafiles na weza!");
			pozycja=13;
		}
		if (pozycja==52) {
			Przyciski.napis.setText("Wyrzucono liczbe: "+rzutkostka+" Trafiles na weza!");
			pozycja=29;
		}
		if (pozycja==57) {
			Przyciski.napis.setText("Wyrzucono liczbe: "+rzutkostka+" Trafiles na weza!");
			pozycja=40;
		}
		if (pozycja==62) {
			Przyciski.napis.setText("Wyrzucono liczbe: "+rzutkostka+" Trafiles na weza!");
			pozycja=22;
		}
		if (pozycja==88) {
			Przyciski.napis.setText("Wyrzucono liczbe: "+rzutkostka+" Trafiles na weza!");
			pozycja=18;
		}
		if (pozycja==95) {
			Przyciski.napis.setText("Wyrzucono liczbe: "+rzutkostka+" Trafiles na weza!");
			pozycja=51;
		}
		if (pozycja==97) {
			Przyciski.napis.setText("Wyrzucono liczbe: "+rzutkostka+" Trafiles na weza!");
			pozycja=79;
		}		
	}
}	

	class Obsluganowejgry implements ActionListener 
	{
		public void actionPerformed (ActionEvent e) 
		{
			Przyciski.napis.setText("Rozpoczeto nowa gre!");
			pozycja=1;
			repaint();
		
		}
	}
	
}

class Client extends Thread 
{
	static private Socket socket;
	public void run() 
	{
		try 
		{
			socket = new Socket("localhost", 2345);
			Przyciski przyciski = new Przyciski();
			Plansza plansza = new Plansza();

			plansza.setVisible(true);

			JFrame okno = new JFrame("Gra drabiny i weze");

			ImageIcon obrazek = new ImageIcon("snakesandladders.png");

			Dimension rozmiary = new Dimension(obrazek.getIconWidth()+15, obrazek.getIconHeight()+100);

			okno.setSize(rozmiary);
			okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			okno.setVisible(true);
			okno.add(przyciski,BorderLayout.NORTH);
			okno.add(plansza,BorderLayout.CENTER);
		} 
		catch (IOException e) 
		{
		}

	}
}

public class drabinyiweze 
{
	public static void main(String[] args) 
	{
		new Client().start();
	}
}