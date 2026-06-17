package poke_clone;

import poke_clone.entity.NPC_OldMan;
import poke_clone.object.OBJ_Chest;
import poke_clone.object.OBJ_Door;
import poke_clone.object.OBJ_Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    public void setNPC(){
        gp.npc[0] = new NPC_OldMan(gp) ; 
        gp.npc[0].setWorldX(5*gp.TILE_SIZE);
        gp.npc[0].setWorldY(9*gp.TILE_SIZE);
    }

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
