package Project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Math;

import javax.swing.JPanel;
import javax.swing.Timer;

//Klasa glowna
public class Battlefield  extends JPanel implements ActionListener{
	//tablice przechowujace wojownikow obu druzyn
	public static Warrior[] array1 = new Warrior[90]; //druzyna 1
	public static Warrior[] array2 = new Warrior[90]; //druzyna 2
	
	static Summary summary = new Summary();

	//metoda glowna
	public static void main(String[] args) {
		Battlefield battle = new Battlefield();
		battle.warinit();
	}

	//Summary
	public static class Summary {
		private int x=5;
		
		public void increment() {
			x += 1;
		}
		
		public void CasualitiesCounter() {
			int y1=0, y2=0;
			if(checkif1alive()==false) {
				for(Warrior warrior:array2) {
					if(warrior.Alive == true) {
						y1 +=1;					
					}

				}
				
			}
			else if (checkif2alive()==false) {
				for(Warrior warrior:array1) {
					if(warrior.Alive == true) {
						y2 +=1;
					}
				}
				
			}
			
			if ( (y1+y2) != 0 ) {
				if (y1 > y2) {
					System.out.println("Wygrala druzyna 2");
					System.out.println("Przy zyciu utrzymalo sie: "+y1+" zolnierzy");
				}
				if (y1 < y2) {
					System.out.println("Wygrala druzyna 1");
					System.out.println("Przy zyciu utrzymalo sie: "+y2+" zolnierzy");
				}
			} else
				System.out.println("Nikt nie przezyl");
		}	
	}
	
	//		funkcja ktora ustawi zolnierzy na polu dla obydwu druzyn
	public Battlefield() {	
		
		/////Team 1   (false)/////
		
		//Luczniki
		for(int j = 10; j < 40; j++) {
			Archer A = new Archer(0,j,false);
			array1[j-10]=A;
		}
		
		//Rycerze
		for(int j = 10; j < 40; j++) {
			Knight K = new Knight(1,j,false);
			array1[j+20] = K;
		}
		
		//Kawalerja
		for(int j = 10; j < 40; j++) {
			Cavalry C = new Cavalry(2,j,false);
			array1[j+50] = C;
		}
		
		/////Team 2   (true)/////
		
		//Archers 
		for(int j = 10; j < 40; j++) {
			Archer a = new Archer(49,j,true);
			array2[j-10]= a;
		}
		
		//Knights		
		for(int j = 10; j < 40; j++) {
			Knight k = new Knight(48,j,true);
			array2[j+20] = k;
		}
		
		//Cavalry
		for(int j = 10; j < 40; j++) {
			Cavalry c = new Cavalry(47,j,true);
			array2[j+50] = c;
		}
	}
	
	//pomaga ustalic czy obiekt z druzyny a ma isc/atakowac
	public static Warrior CheckingForTeam1Attack(Warrior warrior) {
		for(Warrior warrior2 : array2) {
			if((warrior2.Alive == true) && ((Math.abs(warrior2.x - warrior.x))<warrior.attackrange) && (Math.abs(warrior2.y - warrior.y)<warrior.attackrange)){
				return warrior2;
			}
			else continue;
		}
		return null;
	}
	
	// funkcja która mówi w jakim kierunku ma poruszac siê obiekt, specjalnie utworzy³em dodatkowa klase HowToMove mowi ona w ktora strone ma sie sie przemieszczac obiekt
	public static HowToMove CheckingForTeam1Move(Warrior warrior) {
		HowToMove a = new HowToMove();
		Warrior closestWarrior=null;
		a.x = 0;
		a.y = 0;
		int distance = 1000;
		for(Warrior warrior2 : array2) {
			if(warrior2.Alive == true) {
				if((Math.abs(warrior2.x - warrior.x)+ Math.abs(warrior2.y - warrior.y))<distance) {
					distance = Math.abs(warrior2.x - warrior.x)+ Math.abs(warrior2.y - warrior.y);
					closestWarrior = warrior2;
				}
				else continue;
			}
		}
		if(closestWarrior != null) {
			a.y = closestWarrior.y - warrior.y;
			a.x = closestWarrior.x - warrior.x;
		}
		return a;
	}
	
	public static Warrior CheckingForTeam2Attack(Warrior warrior) {
		for(Warrior warrior2 : array1) {
			if((warrior2.Alive == true) && ((Math.abs(warrior2.x - warrior.x))<warrior.attackrange) && (Math.abs(warrior2.y - warrior.y)<warrior.attackrange)){
				return warrior2;
			}
			else continue;
		}
		return null;
	}
	
	public static HowToMove CheckingForTeam2Move(Warrior warrior) {
		HowToMove a = new HowToMove();
		Warrior closestWarrior=null;
		a.x = 0;
		a.y = 0;
		int distance = 1000;
		for(Warrior warrior2 : array1) {
			if(warrior2.Alive == true) {
				if((Math.abs(warrior2.x - warrior.x)+ Math.abs(warrior2.y - warrior.y))<distance) {
					distance = Math.abs(warrior2.x - warrior.x)+ Math.abs(warrior2.y - warrior.y);
					closestWarrior = warrior2;
				}
				else continue;
			}
		}
		if(closestWarrior != null) {
			a.y = closestWarrior.y - warrior.y;
			a.x = closestWarrior.x - warrior.x;
		}
		return a;
	}
	
	//sprawdza czy ktos zyje w druzynie 2
	public static boolean checkif2alive() {
		boolean check = false;
		for(Warrior warrior : array2) {
			if(warrior.Alive == true) check = true;
		}
		return check;
	}
	//sprawdza czy ktos zyje w druzynie 1
	public static boolean checkif1alive() {
		boolean check = false;
		for(Warrior warrior : array1) {
			if(warrior.Alive == true) check = true;
		}
		return check;
	}
	
	Timer timer = new Timer(300,this);
	
	public void warinit() {
		MyFrame frame = new MyFrame();
		this.setPreferredSize(new Dimension(1000, 1000));
		this.setBackground(Color.black);
		this.setFocusable(true);

		frame.add(this);

		frame.pack();
		timer.start();	
	}
	
//grafika
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	public void draw(Graphics g) {
		for (Warrior warrior : array1) {
			if(warrior.Alive==true) {
				g.setColor(Color.red);
				g.fillOval(warrior.x * 20, warrior.y * 20, 15, 15);
			}
		}
		for (Warrior warrior : array2) {
			if(warrior.Alive==true) {
				g.setColor(Color.blue);
				g.fillOval(warrior.x * 20, warrior.y * 20, 15, 15);;
			}
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean running = true;
		if(checkif1alive() == false) running = false;
		if(checkif2alive() == false) running = false;
		if(running == true) {
			
			summary.increment();
			
			for(Warrior warrior : array1) {
				warrior.death();
			}
			
			for(Warrior warrior : array1) {
				warrior.death();
				Warrior warriorToAttac = CheckingForTeam1Attack(warrior);
				if(warriorToAttac != null) warrior.attack(warriorToAttac);
				else {
					HowToMove a = CheckingForTeam1Move(warrior);
					warrior.movement(a);
				}	
			}		
			for(Warrior warrior : array2) {
				warrior.death();
			}
			
			for(Warrior warrior : array2) {
				warrior.death();
				Warrior warriorToAttac = CheckingForTeam2Attack(warrior);
				if(warriorToAttac != null) warrior.attack(warriorToAttac);
				else {
					HowToMove a = CheckingForTeam2Move(warrior);
					warrior.movement(a);
				}	
			}
			repaint();
		}
		if(running == false) {
			System.out.println("Bitwa trwala: "+summary.x+" iteracji");
			summary.CasualitiesCounter();
			timer.stop();
		}
			
			
		
	}
	
	
}