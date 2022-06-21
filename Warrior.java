package Project;

import java.util.Random;

//Podstawowa klasa Warrior
public abstract class Warrior {
	protected int health;
    protected int ms;
    protected int damage;
    protected int x;
    protected int y;
    protected int attackrange;
    protected boolean Alive;
    protected boolean Team;
//stat
    public Warrior(int x, int y, boolean Team) {
            this.x = x;
            this.y = y;
            Alive = true;
            this.Team=Team;
    }
//przemieszczenie    
    public void movement(HowToMove move) {
    	if(move.x != 0 || move.y != 0) {
    		if((Math.abs(move.x) > ms)&& (Math.abs(move.y)> ms)) {
    			if((move.x < 0)&& (move.y>0)) {
    				x += -ms;
    				y += ms;		
    			}
    			if((move.x < 0)&& (move.y<0)) {
    				x += -ms;
    				y += -ms;
    			}
    			else {
    				x += ms;
    				y += ms;
    			}
    		}
    		else if((Math.abs(move.x) <= ms)&& (Math.abs(move.y) <= ms)) {
    			x += move.x;
        		y += move.y;
    		}
    		else if((Math.abs(move.x) > ms)&& (Math.abs(move.y) <= ms)) {
    			if((move.x < 0)) {
    				x += -ms;
    				y += move.y;
    			}
    			else {
    				x += ms;
            		y += move.y;
    			}
    			
    		}
    		else if((Math.abs(move.x) <= ms)&& (Math.abs(move.y) > ms)) {
    		if(move.y<0) {
    			x += move.x;
        		y += -ms;
    		}
    		else {
    			x += move.x;
        		y += ms;
    		}
    	
    		}	
    	}
    	
    }
//Attack
    public void attack(Warrior warrior) {
    	if(warrior != null) {
    		Random ran = new Random();
    		int dmg= ran.nextInt(damage/10, damage);
    		warrior.health -= dmg;
    	}
    }
//Death
    public void death(){
    	if(health<0) Alive = false;
    }
    
    
}

