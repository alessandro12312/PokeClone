package poke_clone.entity;

import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;
import poke_clone.GamePanel;

/**
 * NPC_OldMan è il primo personaggio non giocante del gioco.
 * Quando il giocatore gli parla, mostra un dialogo a schermo. Si muove a caso sulla mappa.
 */
public class NPC_OldMan extends Entity {

	GamePanel gp;
	public int actionLockCounter = 0;

	public String dialogue = "benvenuto avventuriero";

	public NPC_OldMan(GamePanel gp) {
		this.gp = gp ;
		speed=1; 
		direction = "down";
		name = "OldMan" ;
		collision = false ;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/res/npc/oldman.png")) ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		solidArea= new Rectangle(8,16,32,32) ;
		solidAreaDefaultX = 8 ;
		solidAreaDefaultY=16 ;
	}

	@Override
	public void update() {
	    actionLockCounter++;
	    if (actionLockCounter == 120) {  // ogni 120 frame (2 secondi a 60 FPS) sceglie una nuova direzione
	        java.util.Random random = new java.util.Random();
	        int i = random.nextInt(4);  // 0..3
	        switch (i) {
	            case 0: direction = "up"; break;
	            case 1: direction = "down"; break;
	            case 2: direction = "left"; break;
	            case 3: direction = "right"; break;
	        }
	        actionLockCounter = 0;
	    }
	
	    setCollision(false);
	    gp.cChecker.checkTile(this);
	
	    if (!isCollision()) {
	        switch (direction) {
	            case "up": worldY -= speed; break;
	            case "down": worldY += speed; break;
	            case "left": worldX -= speed; break;
	            case "right": worldX += speed; break;
	        }
	    }
	}
}
