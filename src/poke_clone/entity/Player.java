package poke_clone.entity;

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
	BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	int spriteCounter = 0;
	int spriteNum = 1;
	int hasKey = 0;
	public int screenX , screenY ; 
	// STEP 10.3: Aggiungi le coordinate dello schermo (Screen Coordinates) per il giocatore.
	// Il giocatore rimane sempre al centro dello schermo: la telecamera si muove con lui, non lui
	// con la telecamera. worldX/worldY (ex x/y, vedi STEP 10.2 in Entity) continuano a muoversi
	// liberamente su tutta la mappa 50x50; screenX/screenY restano invece fissi.
	// TODO:
	// - Dichiara due campi `public int screenX;` e `public int screenY;`.
	// - In setDefaultValues(), inizializzali al centro dello schermo:
	//     screenX = gp.SCREEN_WIDTH / 2 - gp.TILE_SIZE / 2;
	//     screenY = gp.SCREEN_HEIGHT / 2 - gp.TILE_SIZE / 2;

	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;

		solidArea = new Rectangle(8, 16, 32, 32);

		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		// STEP 10.2 (continua): worldX/worldY sono la posizione di partenza del giocatore
		// nella mappa 50x50 (in pixel, come prima erano x/y). 100,100 resta una posizione valida
		// vicino all'angolo in alto a sinistra, dentro le mura di confine.
		worldX = 100;
		worldY = 100;
		speed = 4;
		direction = "down";
		screenX = gp.SCREEN_WIDTH / 2 - gp.TILE_SIZE / 2 ; 
		screenY = gp.SCREEN_HEIGHT/ 2 - gp.TILE_SIZE / 2 ; 
	}

	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/up2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/down2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/left2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/right2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		if (keyH.upPressed) {
			direction = "up";
		} else if (keyH.downPressed) {
			direction = "down";
		} else if (keyH.leftPressed) {
			direction = "left";
		} else if (keyH.rightPressed) {
			direction = "right";
		} else {
			direction = "";
		}

		setCollision(false);
		if (!direction.equals("")) {
			gp.cChecker.checkTile(this);

			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);

			// STEP 10.2 (continua): aggiorna questi spostamenti per usare worldX/worldY
			// (rinominati da x/y, vedi TODO in Entity.java) al posto di x/y.
			if (isCollision() == false) {
				switch (direction) {
					case "up": worldY -= speed; break;
					case "down": worldY += speed; break;
					case "right": worldX += speed; break;
					case "left": worldX -= speed; break;
				}
			}

			spriteCounter++;
			if (spriteCounter > 12) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}

	public void pickUpObject(int i) {
		if (i != 999) {
			String objName = gp.obj[i].getName(); 
			switch (objName) {
				case "Key":
					hasKey++;
					gp.obj[i] = null; 
					System.out.println("Chiave raccolta! Chiavi: " + hasKey);
					break;
				case "Door":
					if (hasKey > 0) {
						hasKey--; 
						System.out.println("Chiave utilizzata. Chiavi rimaste: " + hasKey);
						gp.obj[i] = null; 
					}
					break;
				case "Chest":
					
					System.out.println("Vittoria! Hai aperto il forziere.");
					gp.gameThread = null; 
					break;
				default:
					break;
			}
		}
	}

	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		switch (direction) {
			case "up":
				if (spriteNum == 1) image = up1;
				if (spriteNum == 2) image = up2;
				break;
			case "down":
				if (spriteNum == 1) image = down1;
				if (spriteNum == 2) image = down2;
				break;
			case "left":
				if (spriteNum == 1) image = left1;
				if (spriteNum == 2) image = left2;
				break;
			case "right":
				if (spriteNum == 1) image = right1;
				if (spriteNum == 2) image = right2;
				break;
			default:
				image = down1;
		}
		// STEP 10.3 (continua): il player va disegnato sempre nella stessa posizione fissa
		// dello schermo (screenX, screenY), non in worldX/worldY: è la mappa (e gli oggetti)
		// a scorrere intorno a lui, non il contrario.
		// TODO: sostituisci x, y con screenX, screenY.
		g2.drawImage(image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
	}
}
