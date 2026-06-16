package poke_clone;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
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
			

	public void showMessage(String text) {
		message= text ; 
		messageOn = true ; 
		messageCounter = 0 ; 
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
	}
}
