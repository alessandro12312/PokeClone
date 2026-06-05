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

## Stato Corrente dello Sviluppo: Step 7 (Animazione del Giocatore)
Fino ad ora sono stati completati con successo i seguenti passaggi:
1. **Finestra di Gioco (Step 1)**: Creazione della finestra principale `JFrame` con `GamePanel`.
2. **Game Loop (Step 2)**: Loop preciso a 60 FPS basato su calcolo Delta/Sleep.
3. **Movimento (Step 3)**: Cattura tasti W, A, S, D per muovere il player.
4. **OOP Structure (Step 4)**: Estrapolazione delle classi base `Entity` (astratta) e `Player` che estende `Entity`.
5. **Mappa & Sfondo (Step 5)**: Creazione dei tile di gioco in pixel art e caricamento tramite file di testo `map01.txt` gestito da `TileManager`.
6. **Collisioni (Step 6)**: Rilevamento delle collisioni preciso sui quattro lati della `solidArea` del giocatore rispetto alle tessere con collisione attiva (`wall` e `water`), impedendo il movimento se `collision = true`.

### Cosa c'è da fare adesso (Compito per la prossima AI):
Completare lo **Step 7** in **[Player.java](src/poke_clone/entity/Player.java)** seguendo i `TODO` commentati nel file:
1. Dichiarare le variabili `BufferedImage` per gli sprite (`up1`, `up2`, `down1`, `down2`, `left1`, `left2`, `right1`, `right2`).
2. Dichiarare `spriteCounter` e `spriteNum` per l'animazione.
3. Caricare le immagini PNG da `/res/player/` nel metodo `getPlayerImage()`.
4. Nel metodo `update()`, alternare `spriteNum` (1 o 2) incrementando `spriteCounter` solo quando il giocatore si sta muovendo.
5. Nel metodo `draw()`, disegnare l'immagine dello sprite corretto in base alla direzione e al numero di sprite correnti invece del rettangolo bianco di test.
