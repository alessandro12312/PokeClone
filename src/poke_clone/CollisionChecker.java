package poke_clone;

import poke_clone.entity.Entity;

/**
 * CollisionChecker si occupa di controllare se un'entità (es. il Player)
 * sta collidendo con una tessera solida della mappa (es. Muro, Acqua) o con un oggetto.
 */
public class CollisionChecker {

	GamePanel gp;

	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}

	public void checkTile(Entity entity) {
		int entityLeftX = entity.getWorldX() + entity.getSolidArea().x;
		int entityRightX = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width;
		int entityTopY = entity.getWorldY() + entity.getSolidArea().y;
		int entityBottomY = entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height;

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

	public int checkEntity(Entity entity, Entity[] target) {
	    int index = 999;
	    for (int i = 0; i < target.length; i++) {
	        if (target[i] != null) {
	            entity.solidArea.x = entity.getWorldX() + entity.solidArea.x;
	            entity.solidArea.y = entity.getWorldY() + entity.solidArea.y;
	            target[i].solidArea.x = target[i].getWorldX() + target[i].solidArea.x;
	            target[i].solidArea.y = target[i].getWorldY() + target[i].solidArea.y;
	            switch (entity.getDirection()) {
	                case "up":    entity.solidArea.y -= entity.getSpeed(); break;
	                case "down":  entity.solidArea.y += entity.getSpeed(); break;
	                case "left":  entity.solidArea.x -= entity.getSpeed(); break;
	                case "right": entity.solidArea.x += entity.getSpeed(); break;
	            }
	            if (entity.solidArea.intersects(target[i].solidArea)) { index = i; }
	            entity.solidArea.x = entity.getSolidAreaDefaultX();
	            entity.solidArea.y = entity.getSolidAreaDefaultY();
	            target[i].solidArea.x = target[i].getSolidAreaDefaultX();
	            target[i].solidArea.y = target[i].getSolidAreaDefaultY();
	        }
	    }
	    return index;
	}

	public int checkObject(Entity entity, boolean player) {
		int index = 999; 
		for (int i = 0; i < gp.obj.length; i++) {
			if (gp.obj[i] != null) {
				entity.solidArea.x = entity.getWorldX() + entity.solidArea.x; 
				entity.solidArea.y = entity.getWorldY() + entity.solidArea.y; 

				gp.obj[i].solidArea.x = gp.obj[i].getWorldX() + gp.obj[i].solidArea.x; 
				gp.obj[i].solidArea.y = gp.obj[i].getWorldY() + gp.obj[i].solidArea.y;
				
				switch (entity.getDirection()) {
					case "up":
						entity.solidArea.y -= entity.getSpeed(); break;
					case "down":
						entity.solidArea.y += entity.getSpeed(); break;
					case "left":
						entity.solidArea.x -= entity.getSpeed(); break;
					case "right":
						entity.solidArea.x += entity.getSpeed(); break;
				}

				if (entity.solidArea.intersects(gp.obj[i].solidArea)) {		
					if (gp.obj[i].isCollision()) {
						entity.setCollision(true);
					}
					if (player) {
						index = i; 
					}
				}

				entity.solidArea.x = entity.getSolidAreaDefaultX();
				entity.solidArea.y = entity.getSolidAreaDefaultY();
				gp.obj[i].solidArea.x = gp.obj[i].getSolidAreaDefaultX();
				gp.obj[i].solidArea.y = gp.obj[i].getSolidAreaDefaultY();
			}
		}
		return index; 
	}
}
