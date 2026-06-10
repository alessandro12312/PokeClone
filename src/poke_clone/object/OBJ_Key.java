package poke_clone.object;

import java.io.IOException;
import javax.imageio.ImageIO;
import poke_clone.entity.Entity;

public class OBJ_Key extends Entity {
    public OBJ_Key() {
        name = "Key"; 
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/key.png")); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
