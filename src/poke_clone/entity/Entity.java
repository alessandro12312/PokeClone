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

	protected int worldX, worldY;
	protected int speed;
	protected String direction = ""; 
	protected boolean collision = false; 
	protected BufferedImage image; 
	protected String name; 

	
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	protected int solidAreaDefaultX = 0;
	protected int solidAreaDefaultY = 0 ; 
	public int getSolidAreaDefaultX() {
		return solidAreaDefaultX;
	}

	public void setSolidAreaDefaultX(int solidAreaDefaultX) {
		this.solidAreaDefaultX = solidAreaDefaultX;
	}

	public int getSolidAreaDefaultY() {
		return solidAreaDefaultY;
	}

	public void setSolidAreaDefaultY(int solidAreaDefaultY) {
		this.solidAreaDefaultY = solidAreaDefaultY;
	}

	public void draw(Graphics2D g2, GamePanel gp) {
		int screenX =  worldX-gp.player.getWorldX() + gp.player.screenX ; 
		int screenY = worldY-gp.player.getWorldY() + gp.player.screenY ; 
		if (image != null &&(worldX + gp.TILE_SIZE > gp.player.getWorldX() - gp.player.screenX
 			&& worldX - gp.TILE_SIZE < gp.player.getWorldX() + gp.player.screenX
 			&& worldY + gp.TILE_SIZE > gp.player.getWorldY() - gp.player.screenY
 			&& worldY - gp.TILE_SIZE < gp.player.getWorldY() + gp.player.screenY)) {
				
			g2.drawImage(image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
		}
	}

	public void update(){}
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
	public int getWorldX() {
		return worldX; 
	}
	public void setWorldX(int x) {
		this.worldX = x; 
	}
	public int getWorldY() {
		return worldY; 
	}
	public void setWorldY(int y) {
		this.worldY = y; 
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
