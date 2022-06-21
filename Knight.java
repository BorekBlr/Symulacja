package Project;

//Klas statystyki szermierza, dziedziczy podstawowy warrior
public class Knight extends Warrior {

	//statystyki
	public Knight(int x, int y, boolean Team) {
		super(x, y, Team);
		health=100;
		ms=2;
		damage=30;
		this.Team=Team;
		attackrange = 1;
	}


}
