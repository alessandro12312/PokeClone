package poke_clone.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import poke_clone.GamePanel;

/**
 * Entity rappresenta la classe base (superclasse) per tutti gli elementi mobili, interattivi o statici del gioco.
 * È chiara astratta perché non vogliamo istanziarla direttamente.
 */
public abstract class Entity {

	protected int x, y; 
	protected int speed; 
	protected String direction = ""; // Inizializzato vuoto per default
	protected Rectangle solidArea;
	protected boolean collision = false; 
	protected BufferedImage image; // Immagine per oggetti statici
	protected String name; // Nome dell'entità/oggetto

	public void draw(Graphics2D g2, GamePanel gp) {
		if (image != null) {
			g2.drawImage(image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
		}
	}



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
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
