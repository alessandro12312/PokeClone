package poke_clone.object;

import java.io.IOException;
import javax.imageio.ImageIO;
import poke_clone.GamePanel;
import poke_clone.entity.Entity;

public class OBJ_Door extends Entity {

    // STEP 8.4: Crea il costruttore di OBJ_Door.
    // - Imposta 'name = "Door";'
    // - Imposta 'collision = true;' (visto che una porta sbarrata impedisce il passaggio)
    // - Carica l'immagine '/res/objects/door.png' all'interno di un blocco try-catch usando ImageIO.read(getClass().getResourceAsStream(...)).
    // TODO: Implementa il costruttore per inizializzare l'oggetto porta ed estendere Entity.
}
