package poke_clone;

import poke_clone.object.OBJ_Chest;
import poke_clone.object.OBJ_Door;
import poke_clone.object.OBJ_Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    // STEP 10.2 (continua): dopo aver rinominato setX/setY in setWorldX/setWorldY in Entity.java,
    // aggiorna le chiamate qui sotto. Le coordinate restano espresse in pixel (colonna/riga *
    // TILE_SIZE): con la mappa ora a 50x50 tessere puoi anche spostare questi oggetti più lontano
    // dallo spawn del giocatore per avere più spazio da esplorare.
    public void setObject() {
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].setWorldX(4 * gp.TILE_SIZE);
        gp.obj[0].setWorldY(3 * gp.TILE_SIZE);

        gp.obj[1] = new OBJ_Door();
        gp.obj[1].setWorldX(8 * gp.TILE_SIZE);
        gp.obj[1].setWorldY(5 * gp.TILE_SIZE);

        gp.obj[2] = new OBJ_Chest();
        gp.obj[2].setWorldX(12 * gp.TILE_SIZE);
        gp.obj[2].setWorldY(8 * gp.TILE_SIZE);
    }
}
