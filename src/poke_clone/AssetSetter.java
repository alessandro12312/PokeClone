package poke_clone;

import poke_clone.object.OBJ_Chest;
import poke_clone.object.OBJ_Door;
import poke_clone.object.OBJ_Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].setX(4 * gp.TILE_SIZE);
        gp.obj[0].setY(3 * gp.TILE_SIZE);

        gp.obj[1] = new OBJ_Door();
        gp.obj[1].setX(8 * gp.TILE_SIZE);
        gp.obj[1].setY(5 * gp.TILE_SIZE);

        gp.obj[2] = new OBJ_Chest();
        gp.obj[2].setX(12 * gp.TILE_SIZE);
        gp.obj[2].setY(8 * gp.TILE_SIZE);
    }
}
