package poke_clone.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import poke_clone.GamePanel;

/**
 * TileManager gestisce il caricamento delle immagini delle tessere,
 * la lettura della mappa da file e il disegno dello sfondo del gioco.
 */
public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int[][] mapTileNum;

	// STEP 10.5: Adatta TileManager alla World Map (50x50) e disegna in base alla telecamera.
	// TODO: ridimensiona mapTileNum a [gp.MAX_WORLD_COL][gp.MAX_WORLD_ROW] (vedi STEP 10.1 in
	// GamePanel.java per le nuove costanti).

	public TileManager(GamePanel gp) {
		this.gp = gp;

		tile = new Tile[10];
		mapTileNum = new int[gp.MAX_WORLD_COL][gp.MAX_WORLD_ROW];

		getTileImage();
		loadMap();
	}

	public void getTileImage() {
		try {
			tile[0] = new Tile(); 
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass.png")); 

			tile[1] = new Tile(); 
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/wall.png")); 
			tile[1].collision = true; 

			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water.png")); 
			tile[2].collision = true; 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// STEP 10.5 (continua): map01.txt è già stato espanso a 50 righe x 50 colonne.
	// TODO: aggiorna i due cicli per leggere gp.MAX_WORLD_ROW righe da gp.MAX_WORLD_COL numeri
	// (al posto di MAX_SCREEN_ROW/MAX_SCREEN_COL).
	public void loadMap() {
		try {
			InputStream is = getClass().getResourceAsStream("/res/maps/map01.txt");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			for (int row = 0; row < gp.MAX_WORLD_ROW; row++) {
				String line = br.readLine();
				String numbers[] = line.split(" ");

				for (int col = 0; col < gp.MAX_WORLD_COL; col++) {
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// STEP 10.5 (continua): disegna la mappa 50x50 centrata sul giocatore (telecamera).
	// TODO:
	// - Itera su gp.MAX_WORLD_ROW x gp.MAX_WORLD_COL (l'intera mappa), non più solo
	//   MAX_SCREEN_ROW/MAX_SCREEN_COL.
	// - Per ogni tessera calcola la posizione nel mondo (come ora): worldX = col * TILE_SIZE,
	//   worldY = row * TILE_SIZE.
	// - Calcola la posizione sullo schermo in base alla telecamera:
	//     screenX = worldX - gp.player.getWorldX() + gp.player.screenX;
	//     screenY = worldY - gp.player.getWorldY() + gp.player.screenY;
	// - Disegna la tessera in (screenX, screenY) solo se è (almeno parzialmente) visibile,
	//   per non sprecare risorse disegnando l'intera mappa ogni frame. Esempio di condizione
	//   di visibilità (con un margine di una tessera in ogni direzione):
	//     worldX + TILE_SIZE > gp.player.getWorldX() - gp.player.screenX &&
	//     worldX - TILE_SIZE < gp.player.getWorldX() + gp.player.screenX &&
	//     worldY + TILE_SIZE > gp.player.getWorldY() - gp.player.screenY &&
	//     worldY - TILE_SIZE < gp.player.getWorldY() + gp.player.screenY
	public void draw(Graphics2D g2) {
		for (int row = 0; row < gp.MAX_WORLD_ROW; row++) {
			for (int col = 0; col < gp.MAX_WORLD_COL; col++) {
				int tileNum = mapTileNum[col][row];
				int worldX = col * gp.TILE_SIZE;
				int worldY = row * gp.TILE_SIZE;
				int screenX = worldX - gp.player.getWorldX() + gp.player.screenX;
				int screenY = worldY - gp.player.getWorldY() + gp.player.screenY;
				if (worldX + gp.TILE_SIZE > gp.player.getWorldX() - gp.player.screenX
 					&& worldX - gp.TILE_SIZE < gp.player.getWorldX() + gp.player.screenX
 					&& worldY + gp.TILE_SIZE > gp.player.getWorldY() - gp.player.screenY
 					&& worldY - gp.TILE_SIZE < gp.player.getWorldY() + gp.player.screenY) {

    				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
				}
			}
		}
	}
}
