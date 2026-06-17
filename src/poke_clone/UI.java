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
	public void drawTitleScreen(Graphics2D g2) {
	    // sfondo
	    g2.setColor(new Color(20, 80, 40));
	    g2.fillRect(0, 0, gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT);
	
	    // titolo
	    g2.setColor(Color.WHITE);
	    g2.setFont(new Font("Arial", Font.BOLD, 60));
	    String text = "PokeClone";
	    int x = getXForCenteredText(g2, text);
	    int y = gp.TILE_SIZE * 3;
	    g2.drawString(text, x, y);
	
	    // istruzioni
	    g2.setFont(new Font("Arial", Font.PLAIN, 24));
	    text = "PREMI INVIO PER INIZIARE";
	    x = getXForCenteredText(g2, text);
	    y = gp.SCREEN_HEIGHT - gp.TILE_SIZE * 3;
	    g2.drawString(text, x, y);
	}
	public int getXForCenteredText(Graphics2D g2, String text) {
	    int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
	    return gp.SCREEN_WIDTH / 2 - length / 2;
	}

	public void drawPauseScreen(Graphics2D g2) {
	    drawSubWindow(g2, gp.SCREEN_WIDTH/2 - 100, gp.SCREEN_HEIGHT/2 - 50, 200, 100);
	    g2.setFont(new Font("Arial", Font.BOLD, 32));
	    g2.setColor(Color.WHITE);
	    String text = "PAUSA";
	    int x = getXForCenteredText(g2, text);
	    g2.drawString(text, x, gp.SCREEN_HEIGHT/2 + 10);
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
		if (gp.gameState == GamePanel.PAUSE_STATE) {
		    drawPauseScreen(g2);
		}
	}
}
