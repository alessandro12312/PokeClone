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

## Stato Corrente dello Sviluppo: Step 10 (Progettazione della Telecamera/World Map)
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

### Cosa c'è da fare adesso (Compito per la prossima AI):
Iniziare lo **Step 10: La Telecamera di Gioco e la World Map (Mappa Globale)**:
- Espandere la mappa da una singola schermata a una mappa globale (es. 50x50 tessere).
- Introdurre il concetto di World Coordinates (`worldX`, `worldY` per ogni entità/tessera) e Screen Coordinates (`screenX`, `screenY` per il disegno a schermo).
- Centrare la telecamera sul giocatore mantenendo `player.screenX` e `player.screenY` fissi al centro dello schermo.
- Modificare `TileManager.java` e `CollisionChecker.java` per supportare il disegno ed il controllo collisioni scalati sulle coordinate del mondo.
