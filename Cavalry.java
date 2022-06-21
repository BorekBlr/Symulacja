package Project;

//Klas statystyki kawalerija, dziedziczy podstawowy warrior
public class Cavalry extends Warrior {

	//statystyki
	public Cavalry(int x, int y, boolean Team) {
		super(x, y, Team);
		health=150;
		ms=3;
		damage=50;
		this.Team=Team;
		attackrange = 2;
	}

}
