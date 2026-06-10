package poke_clone.object;

import java.io.IOException;
import javax.imageio.ImageIO;
import poke_clone.entity.Entity;

public class OBJ_Chest extends Entity {
    public OBJ_Chest() {
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/chest.png")); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
