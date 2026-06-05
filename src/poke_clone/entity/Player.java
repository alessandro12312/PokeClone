package poke_clone.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import poke_clone.GamePanel;
import poke_clone.KeyHandler;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;

	// STEP 7.1: Dichiara le variabili per gli sprite del giocatore.
	// - BufferedImage: up1, up2, down1, down2, left1, left2, right1, right2;
	// - int spriteCounter = 0; (per contare i frame trascorsi)
	// - int spriteNum = 1; (per alternare lo sprite 1 e lo sprite 2)
	// TODO: Dichiara le variabili membro per gli sprite e per il controllo dell'animazione.

	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		solidArea = new Rectangle(8, 16, 32, 32);
		
		setDefaultValues();
		
		// STEP 7.2: Chiama il metodo per caricare gli sprite.
		// TODO: Chiama 'getPlayerImage();'
	}
	
	public void setDefaultValues() {
		x = 100; 
		y = 100; 
		speed = 4; 
		direction = "down"; 
	}

	// STEP 7.3: Crea il metodo 'public void getPlayerImage()' per caricare i file PNG degli sprite.
	// Usa ImageIO.read(getClass().getResourceAsStream("/res/player/nome_file.png")) all'interno di un blocco try-catch.
	// Carica i file: down1.png, down2.png, up1.png, up2.png, left1.png, left2.png, right1.png, right2.png nelle rispettive variabili.
	// TODO: Implementa il metodo getPlayerImage()

	public void update() {
		if (keyH.upPressed) {
			direction = "up"; 
		}
		else if (keyH.downPressed) {
			direction = "down"; 
		}
		else if (keyH.leftPressed) {
			direction = "left"; 
		}
		else if (keyH.rightPressed) {
			direction = "right"; 
		}
		else {
			direction = ""; 
		}

		setCollision(false);
		if (!direction.equals("")) {
			gp.cChecker.checkTile(this);
			
			if (isCollision() == false) {
				switch (direction) {
					case "up": y -= speed; break;
					case "down": y += speed; break;
					case "right": x += speed; break;
					case "left": x -= speed; break;
				}
			}
			
			// STEP 7.4: Gestisci il cambio di frame dell'animazione (solo quando il giocatore si sta effettivamente muovendo).
			// - Incrementa spriteCounter.
			// - Se spriteCounter supera una certa soglia (es. 12 frame), alterna spriteNum tra 1 e 2, e resetta spriteCounter a 0.
			// TODO: Implementa il cambio di frame per l'animazione.
		}
	}

	public void draw(Graphics2D g2) {
		// STEP 7.5: Seleziona l'immagine corretta da disegnare in base a 'direction' e 'spriteNum'.
		// Esempio:
		// BufferedImage image = null;
		// switch(direction) {
		//     case "up":
		//         if (spriteNum == 1) image = up1;
		//         if (spriteNum == 2) image = up2;
		//         break;
		//     ...
		// }
		// Se il giocatore è fermo (direction = ""), disegna lo sprite down1 (o l'ultimo sprite di sosta).
		// Disegna l'immagine con: g2.drawImage(image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
		// TODO: Implementa la selezione dell'immagine e disegnala al posto del fillRect bianco.
		
		g2.setColor(Color.WHITE); 
		g2.fillRect(x, y, gp.TILE_SIZE, gp.TILE_SIZE);
	}
}
