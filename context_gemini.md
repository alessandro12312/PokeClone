# Contesto del Progetto: PokeClone

Questo file serve da contesto per consentire a un assistente AI di riprendere lo sviluppo del progetto senza perdere traccia di quanto fatto finora.

## Tecnologie e Ambiente di Sviluppo
- **Linguaggio**: Java (Standard SE)
- **Librerie Grafiche**: Swing / AWT (`java.desktop` richiesto in `module-info.java`)
- **IDE**: VS Code (configurato con Java Extension Pack) / Eclipse
- **Risoluzione di Gioco**: 768x576 pixel (16 colonne x 12 righe di tessere da 48x48 pixel). La dimensione originale degli sprite è 16x16 pixel scalata 3x.

## Struttura delle Cartelle
```
PokeClone/
├── .git/
├── src/
│   ├── module-info.java (contiene 'requires java.desktop;')
│   ├── poke_clone/
│   │   ├── Main.java (punto di ingresso, configura il JFrame)
│   │   ├── GamePanel.java (pannello principale, gestisce il loop di gioco e il thread)
│   │   ├── KeyHandler.java (gestore input tastiera: W, A, S, D e frecce)
│   │   ├── CollisionChecker.java (gestore collisioni AABB contro le tessere)
│   │   ├── entity/
│   │   │   ├── Entity.java (classe base astratta per tutti gli elementi di gioco)
│   │   │   └── Player.java (gestisce il giocatore, il suo disegno e le collisioni)
│   │   └── tile/
│   │       ├── Tile.java (struttura della singola tessera: immagine + collisione)
│   │       └── TileManager.java (carica i file immagine, legge la mappa e la disegna)
│   └── res/ (risorse del gioco)
│       ├── maps/
│       │   └── map01.txt (matrice di testo 16x12 per la mappa dello sfondo)
│       ├── tiles/
│       │   ├── grass.png (erba, attraversabile)
│       │   ├── wall.png (muro di pietra, solido con collisione)
│       │   └── water.png (acqua, solido con collisione)
│       └── player/
│           ├── up1.png, up2.png
│           ├── down1.png, down2.png
│           ├── left1.png, left2.png
│           └── right1.png, right2.png
```

## Stato Corrente dello Sviluppo: Completato Step 7 (Animazione del Giocatore)
Fino ad ora sono stati completati con successo i seguenti passaggi:
1. **Finestra di Gioco (Step 1)**: Creazione della finestra principale `JFrame` con `GamePanel`.
2. **Game Loop (Step 2)**: Loop preciso a 60 FPS basato su calcolo Delta/Sleep.
3. **Movimento (Step 3)**: Cattura tasti W, A, S, D per muovere il player.
4. **OOP Structure (Step 4)**: Estrapolazione delle classi base `Entity` (astratta) e `Player` che estende `Entity`.
5. **Mappa & Sfondo (Step 5)**: Creazione dei tile di gioco in pixel art e caricamento tramite file di testo `map01.txt` gestito da `TileManager`.
6. **Collisioni (Step 6)**: Rilevamento delle collisioni preciso sui quattro lati della `solidArea` del giocatore rispetto alle tessere con collisione attiva (`wall` e `water`), impedendo il movimento se `collision = true`.
7. **Animazione del Giocatore (Step 7)**: Caricamento degli sprite PNG ed alternanza delle immagini (`spriteNum` 1 e 2) basata su `spriteCounter` durante il movimento.

### Cosa c'è da fare adesso (Compito per la prossima AI):
Completare lo **Step 8 (Oggetti di Gioco e Asset Placement)** seguendo i `TODO` sparsi nei vari file. In questa architettura riutilizziamo la classe astratta `Entity` come superclasse anche per gli oggetti statici:

1. **[Entity.java](src/poke_clone/entity/Entity.java)**:
   - *Completato*: Aggiunti i campi `image` e `name` (con getter/setter) e il metodo base `draw(Graphics2D, GamePanel)` per consentire il disegno degli oggetti statici.

2. **Subclassi degli Oggetti (tutte estendono `Entity`)**:
   - **Step 8.3** in **[OBJ_Key.java](src/poke_clone/object/OBJ_Key.java)**: Implementare il costruttore per impostare il nome `"Key"` (usando `setName("Key")` o la variabile protetta) e caricare la texture `/res/objects/key.png` nel campo `image`.
   - **Step 8.4** in **[OBJ_Door.java](src/poke_clone/object/OBJ_Door.java)**: Implementare il costruttore per impostare il nome `"Door"`, attivare la collisione (`collision = true`) e caricare `/res/objects/door.png`.
   - **Step 8.5** in **[OBJ_Chest.java](src/poke_clone/object/OBJ_Chest.java)**: Implementare il costruttore per impostare il nome `"Chest"` e caricare `/res/objects/chest.png`.

3. **[AssetSetter.java](src/poke_clone/AssetSetter.java)**:
   - **Step 8.6**: Implementare il metodo `setObject()` per posizionare le istanze degli oggetti nella mappa (impostando gli indici dell'array `obj` in `GamePanel` con x e y adeguati).

4. **[GamePanel.java](src/poke_clone/GamePanel.java)**:
   - **Step 8.7**: Dichiarare l'array di oggetti `public Entity obj[] = new Entity[10];` e istanziare `AssetSetter`.
   - **Step 8.8**: Implementare il metodo `setupGame()` richiamando `aSetter.setObject()`.
   - **Step 8.9**: Nel metodo `paintComponent()`, scorrere l'array `obj` e disegnare gli oggetti (se non nulli) richiamando `obj[i].draw(g2, this);` prima del giocatore.

5. **[Main.java](src/poke_clone/Main.java)**:
   - **Step 8.10**: Richiamare `gamepanel.setupGame();` prima di avviare il thread di gioco.

> [!IMPORTANT]
> Ricordati di creare una cartella `/src/res/objects/` e inserire all'interno le immagini PNG degli oggetti (`key.png`, `door.png`, `chest.png`) per permettere il caricamento corretto dei file senza sollevare eccezioni `NullPointerException` durante `ImageIO.read()`.



