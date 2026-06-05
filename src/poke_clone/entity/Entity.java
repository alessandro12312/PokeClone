package poke_clone.entity;

import java.awt.Rectangle;

/**
 * Entity rappresenta la classe base (superclasse) per tutti gli elementi mobili o interattivi del gioco.
 * È chiara astratta perché non vogliamo istanziarla direttamente.
 */
public abstract class Entity {

	protected int x, y; 
	protected int speed; 
	protected String direction;
	protected Rectangle solidArea ;
	protected boolean collision = false ; 
	// STEP 6.1: Dichiara le variabili per la collisione.
	// - Un oggetto Rectangle chiamato 'solidArea' per definire l'area solida di collisione.
	// - Un booleano 'collisionOn' impostato a false di default.
	// TODO: Dichiara 'public Rectangle solidArea;' e 'public boolean collisionOn = false;'
	
	// STEP 6.2: Aggiungi i relativi getter e setter se preferisci mantenere l'incapsulamento, 
	// oppure lasciali pubblici per comodità. Nel nostro caso le lasciamo pubbliche in modo che 
	// il CollisionChecker possa accedervi direttamente.

	public Rectangle getSolidArea() {
		return solidArea;
	}
	public void setSolidArea(Rectangle solidArea) {
		this.solidArea = solidArea;
	}
	public boolean isCollision() {
		return collision;
	}
	public void setCollision(boolean collision) {
		this.collision = collision;
	}
	public int getX() {
		return x; 
	}
	public void setX(int x) {
		this.x = x; 
	}
	public int getY() {
		return y; 
	}
	public void setY(int y) {
		this.y = y; 
	}
	public int getSpeed() {
		return speed; 
	}
	public void setSpeed(int speed) {
		this.speed = speed; 
	}
	public String getDirection() {
		return direction; 
	}
	public void setDirection(String direction) {
		this.direction = direction; 
	}
}
