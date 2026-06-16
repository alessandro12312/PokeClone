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

	// STEP 10.2: Trasforma le coordinate dell'entità in coordinate del Mondo (World Coordinates).
	// Ora che la mappa è più grande di una schermata, ogni entità deve conoscere la propria posizione
	// assoluta nell'intera mappa (worldX, worldY), separata dalla posizione in cui viene disegnata
	// sullo schermo (che dipenderà dalla telecamera).
	// TODO:
	// - Rinomina i campi `x` e `y` in `worldX` e `worldY`.
	// - Rinomina i metodi getX/setX/getY/setY (più sotto in questa classe) in
	//   getWorldX/setWorldX/getWorldY/setWorldY.
	// - Aggiorna tutti i riferimenti a questi campi/metodi nelle altre classi
	//   (Player, CollisionChecker, AssetSetter — vedi i TODO collegati in quei file).
	
	protected int worldX, worldY;
	protected int speed;
	protected String direction = ""; 
	protected boolean collision = false; 
	protected BufferedImage image; 
	protected String name; 

	
	// STEP 9.1: Inizializza l'area solida di default per tutte le entità (inclusi gli oggetti statici).
	// Ogni entità deve avere un'area solida predefinita (es. 0, 0, 48, 48) e due variabili per memorizzare
	// la posizione di default dell'area solida (solidAreaDefaultX e solidAreaDefaultY).
	// TODO: Dichiara ed inizializza:
	// - public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	// - public int solidAreaDefaultX;
	// - public int solidAreaDefaultY;
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

	// STEP 10.4: Disegna gli oggetti statici (Key, Door, Chest) in base alla telecamera.
	// La posizione sullo schermo di un oggetto dipende da dove si trova il giocatore nel mondo:
	//   screenX = worldX (oggetto) - worldX (player) + screenX (player)
	//   screenY = worldY (oggetto) - worldY (player) + screenY (player)
	// TODO:
	// - Calcola screenX e screenY a partire da getWorldX()/getWorldY() di questa entità e da
	//   gp.player.getWorldX()/getWorldY()/screenX/screenY.
	// - Disegna l'immagine in (screenX, screenY) invece che in (worldX, worldY).
	// - (Opzionale) Disegna l'oggetto solo se ricade nell'area visibile dello schermo (con un
	//   margine di una tessera), per evitare disegni inutili fuori camera.
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
