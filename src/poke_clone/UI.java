package poke_clone;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

import poke_clone.entity.NPC_OldMan;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * UI gestisce il disegno dell'interfaccia utente (HUD) sopra la scena di gioco:
 * contatore chiavi, messaggi temporanei a schermo, schermata di vittoria.
 */
public class UI {

	GamePanel gp;
	public String message = "" ; 
	public int messageCounter = 0 ; 
	public boolean messageOn = false ; 
	BufferedImage keyImage;

	public UI(GamePanel gp) {
		this.gp = gp;
		try {
			keyImage = ImageIO.read(getClass().getResourceAsStream("/res/objects/key.png")) ;
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
			

	public String currentDialogue="";
	public void startDialogue(NPC_OldMan npc){
		currentDialogue = npc.dialogue; 
	}
	public void showMessage(String text) {
		message= text ; 
		messageOn = true ; 
		messageCounter = 0 ; 
	}

	public void drawDialogueScreen(Graphics2D g2) {
	    int x = 24;
	    int y = (int)(gp.SCREEN_HEIGHT * 0.75);
	    int width = gp.SCREEN_WIDTH - 48;
	    int height = gp.SCREEN_HEIGHT / 4 - 24;
	    drawSubWindow(g2, x, y, width, height);
	    // Testo del dialogo
	    g2.setFont(new Font("Arial", Font.PLAIN, 18));
	    g2.setColor(Color.WHITE);
	    g2.drawString(currentDialogue, x + 24, y + 48);
	}
	public void drawSubWindow(Graphics2D g2, int x, int y, int w, int h) {
	    Color c = new Color(0, 0, 0, 210);   // nero semitrasparente
	    g2.setColor(c);
	    g2.fillRoundRect(x, y, w, h, 35, 35);
	    c = new Color(255, 255, 255);
	    g2.setColor(c);
	    g2.setStroke(new BasicStroke(5));
	    g2.drawRoundRect(x+5, y+5, w-10, h-10, 25, 25);
	}
	public void draw(Graphics2D g2) {
		g2.setFont(new Font("Arial",Font.BOLD, 16));
		g2.setColor(Color.RED);
		g2.drawImage(keyImage,24,24, gp.TILE_SIZE,gp.TILE_SIZE,null) ;
		g2.drawString("x "+gp.player.hasKey,80, 56); 
		if(messageOn){
			g2.drawString(message,gp.SCREEN_WIDTH/2-100,gp.SCREEN_HEIGHT/2);
			messageCounter++ ;
			if(messageCounter>120){
				messageOn=false;
			}
		}
		if (gp.gameState==gp.DIALOGUE_STATE) {
			drawDialogueScreen(g2);
		}
	}
}
