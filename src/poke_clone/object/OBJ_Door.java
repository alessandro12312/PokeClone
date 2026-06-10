package poke_clone.object;

import java.io.IOException;
import javax.imageio.ImageIO;
import poke_clone.entity.Entity;

public class OBJ_Door extends Entity {
    public OBJ_Door() {
        name = "Door"; 
        collision=true ; 
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/pixel_door.png")); 
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
}
