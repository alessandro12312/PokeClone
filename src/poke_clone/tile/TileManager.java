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

	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[10];
		mapTileNum = new int[gp.MAX_SCREEN_COL][gp.MAX_SCREEN_ROW]; 
		
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

	public void loadMap() {
		try {
			InputStream is = getClass().getResourceAsStream("/res/maps/map01.txt"); 
			InputStreamReader isr = new InputStreamReader(is); 
			BufferedReader br = new BufferedReader(isr);
			
			for (int row = 0; row < gp.MAX_SCREEN_ROW; row++) {
				String line = br.readLine(); 
				String numbers[] = line.split(" "); 

				for (int col = 0; col < gp.MAX_SCREEN_COL; col++) {
					int num = Integer.parseInt(numbers[col]); 
					mapTileNum[col][row] = num; 
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g2) {
		for (int row = 0; row < gp.MAX_SCREEN_ROW; row++) {
			for (int col = 0; col < gp.MAX_SCREEN_COL; col++) {
				int tileNum = mapTileNum[col][row]; 
				int x = col * gp.TILE_SIZE; 
				int y = row * gp.TILE_SIZE; 

				g2.drawImage(tile[tileNum].image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null); 
			}
		}
	}
}
