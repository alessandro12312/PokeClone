package poke_clone;

import poke_clone.entity.Entity;

/**
 * CollisionChecker si occupa di controllare se un'entità (es. il Player)
 * sta collidendo con una tessera solida della mappa (es. Muro, Acqua).
 */
public class CollisionChecker {

	GamePanel gp;

	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}

	public void checkTile(Entity entity) {
		// Calcola i quattro lati dell'area solida dell'entità in pixel nel mondo di gioco.
		int entityLeftX = entity.getX() + entity.getSolidArea().x; 
		int entityRightX = entity.getX() + entity.getSolidArea().x + entity.getSolidArea().width; 
		int entityTopY = entity.getY() + entity.getSolidArea().y; 
		int entityBottomY = entity.getY() + entity.getSolidArea().y + entity.getSolidArea().height; 

		// Trova a quali colonne e righe corrispondono i lati dell'entità
		int entityLeftCol = entityLeftX / gp.TILE_SIZE; 
		int entityRightCol = entityRightX / gp.TILE_SIZE; 
		int entityTopRow = entityTopY / gp.TILE_SIZE; 
		int entityBottomRow = entityBottomY / gp.TILE_SIZE; 

		int tileNum1, tileNum2;

		switch (entity.getDirection()) {
			case "up":
				int nextTopRow = (entityTopY - entity.getSpeed()) / gp.TILE_SIZE;
				tileNum1 = gp.tileM.mapTileNum[entityLeftCol][nextTopRow];
				tileNum2 = gp.tileM.mapTileNum[entityRightCol][nextTopRow];
				if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
					entity.setCollision(true);
				}
				break;
			case "down":
				int nextBottomRow = (entityBottomY + entity.getSpeed()) / gp.TILE_SIZE;
				tileNum1 = gp.tileM.mapTileNum[entityLeftCol][nextBottomRow];
				tileNum2 = gp.tileM.mapTileNum[entityRightCol][nextBottomRow];
				if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
					entity.setCollision(true);
				}
				break;
			case "left":
				int nextLeftCol = (entityLeftX - entity.getSpeed()) / gp.TILE_SIZE;
				tileNum1 = gp.tileM.mapTileNum[nextLeftCol][entityTopRow];
				tileNum2 = gp.tileM.mapTileNum[nextLeftCol][entityBottomRow];
				if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
					entity.setCollision(true);
				}
				break;
			case "right":
				int nextRightCol = (entityRightX + entity.getSpeed()) / gp.TILE_SIZE;
				tileNum1 = gp.tileM.mapTileNum[nextRightCol][entityTopRow];
				tileNum2 = gp.tileM.mapTileNum[nextRightCol][entityBottomRow];
				if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
					entity.setCollision(true);
				}
				break;
		}
	}
}
