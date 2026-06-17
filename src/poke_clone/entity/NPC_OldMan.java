package poke_clone.entity;

import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;
import poke_clone.GamePanel;

/**
 * NPC_OldMan è il primo personaggio non giocante del gioco.
 * Quando il giocatore gli parla, mostra un dialogo a schermo.
 */
public class NPC_OldMan extends Entity {

	public String dialogue = "benvenuto avventuriero";

	public NPC_OldMan(GamePanel gp) {
		name = "OldMan" ; 
		collision = false ; 
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/res/npc/oldman.png")) ;
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		solidArea= new Rectangle(8,16,32,32) ; 
		solidAreaDefaultX = 8 ; 
		solidAreaDefaultY=16 ; 
	}
}
