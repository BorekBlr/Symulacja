package Project;

//Klas statystyki lucznika, dziedziczy podstawowy warrior
public class Archer extends Warrior { 

	//statystyki
	public Archer(int x, int y, boolean Team) {
		super(x, y, Team);
		health=50;
		ms=2;
		damage=20;
		this.Team=Team;
		attackrange = 10;
	}



}
