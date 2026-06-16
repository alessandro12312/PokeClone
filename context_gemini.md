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
│   │   ├── GamePanel.java (pannello principale, gestisce il loop di gioco, il thread e gli oggetti)
│   │   ├── KeyHandler.java (gestore input tastiera: W, A, S, D e frecce)
│   │   ├── CollisionChecker.java (gestore collisioni AABB contro le tessere e gli oggetti)
│   │   ├── AssetSetter.java (posiziona gli oggetti di gioco all'avvio)
│   │   ├── entity/
│   │   │   ├── Entity.java (classe base astratta per tutti gli elementi di gioco)
│   │   │   └── Player.java (gestisce il giocatore, il suo disegno, le collisioni e l'inventario)
│   │   └── object/
│   │       ├── OBJ_Key.java (chiave, raccoglibile, collision = false)
│   │       ├── OBJ_Door.java (porta sbarrata, solida, collision = true)
│   │       └── OBJ_Chest.java (forziere di vittoria, collision = true)
│   └── res/ (risorse del gioco)
│       ├── maps/
│       │   └── map01.txt (matrice di testo 16x12 per la mappa dello sfondo)
│       ├── tiles/
│       │   ├── grass.png, wall.png, water.png
│       │   └── ...
│       ├── player/
│       │   ├── up1.png, up2.png, down1.png, down2.png, left1.png, left2.png, right1.png, right2.png
│       └── objects/
│           ├── key.png
│           ├── pixel_door.png (porta personalizzata inserita dall'utente)
│           └── chest.png
```

## Stato Corrente dello Sviluppo: Step 10 (Telecamera di Gioco e World Map) - IN CORSO
Fino ad ora sono stati completati con successo i seguenti passaggi:
1. **Finestra di Gioco (Step 1)**: Creazione della finestra principale `JFrame` con `GamePanel`.
2. **Game Loop (Step 2)**: Loop preciso a 60 FPS basato su calcolo Delta/Sleep.
3. **Movimento (Step 3)**: Cattura tasti W, A, S, D per muovere il player.
4. **OOP Structure (Step 4)**: Estrapolazione delle classi base `Entity` (astratta) e `Player` che estende `Entity`.
5. **Mappa & Sfondo (Step 5)**: Caricamento tessere in pixel art e caricamento tramite file di testo `map01.txt` gestito da `TileManager`.
6. **Collisioni (Step 6)**: Rilevamento delle collisioni precise del giocatore rispetto alle tessere con collisione attiva (`wall` e `water`).
7. **Animazione Player (Step 7)**: Caricamento dei file sprite (Up, Down, Left, Right) e implementazione del ciclo d'animazione a 2 frame per la camminata.
8. **Oggetti Statici (Step 8)**: Creazione degli oggetti `OBJ_Key`, `OBJ_Door`, `OBJ_Chest` e posizionamento tramite `AssetSetter`.
9. **Interazione Oggetti (Step 9)**: Metodo `checkObject()` implementato in `CollisionChecker` e logica di raccolta chiave/apertura porta/vittoria implementata in `Player.java`.

### Step 10 - Checklist (TODO già inseriti nel codice come commenti):
- [x] **10.6** `src/res/maps/map01.txt`: mappa espansa a 50x50 tessere (bordo di muri, prato
      all'interno, laghetto d'acqua nella stessa posizione relativa di prima).
- [ ] **10.1** `GamePanel.java`: aggiungere `MAX_WORLD_COL`/`MAX_WORLD_ROW`/`WORLD_WIDTH`/`WORLD_HEIGHT`
      e rendere pubblico il campo `player`.
- [ ] **10.2** `Entity.java` + dipendenti (`Player`, `CollisionChecker`, `AssetSetter`): rinominare
      `x`/`y` in `worldX`/`worldY` e `getX`/`setX`/`getY`/`setY` in
      `getWorldX`/`setWorldX`/`getWorldY`/`setWorldY`, aggiornando tutti i riferimenti.
- [ ] **10.3** `Player.java`: aggiungere `screenX`/`screenY` fissati al centro schermo
      (inizializzati in `setDefaultValues()`); `update()` muove `worldX`/`worldY`, `draw()`
      disegna a `screenX`/`screenY`.
- [ ] **10.4** `Entity.java` (`draw()`): disegnare gli oggetti statici (Key, Door, Chest) calcolando
      `screenX`/`screenY` relativi alla telecamera a partire da `worldX`/`worldY` e dalla posizione
      di `gp.player`.
- [ ] **10.5** `TileManager.java`: ridimensionare `mapTileNum` a `[MAX_WORLD_COL][MAX_WORLD_ROW]`,
      aggiornare `loadMap()` per leggere 50x50 valori, e `draw()` per disegnare la mappa centrata
      sul giocatore con culling delle tessere fuori schermo.

Ogni punto ha un commento `// STEP 10.x: ...` con istruzioni dettagliate nel file corrispondente.
Aggiorna questa checklist (e la riga "Stato Corrente") man mano che i punti vengono completati;
quando tutti sono fatti, segna lo Step 10 come completato e descrivi qui la nuova architettura
world/camera, poi proponi lo Step 11.
