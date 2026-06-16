package poke_clone;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import poke_clone.entity.Player;
import poke_clone.tile.TileManager;
import poke_clone.entity.Entity;

public class GamePanel extends JPanel implements Runnable {

	// Impostazioni dello schermo
	private static final int ORIGINAL_TILE_SIZE = 16; 
	private static final int SCALE = 3; 
	
	public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // 48x48 pixel
	public static final int MAX_SCREEN_COL = 16;
	public static final int MAX_SCREEN_ROW = 12;
	public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;
	public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;
	private static final double NANO_SECOND = 1000000000.0;
	public static final int MAX_WORLD_COL = 50 ; 
	public static final int MAX_WORLD_ROW = 50;
	public static final int WORLD_WIDTH = TILE_SIZE * MAX_WORLD_COL ;
	public static final int WORLD_HEIGHT = TILE_SIZE * MAX_WORLD_ROW ;

	public Thread gameThread;
	private static final int FPS = 60;

	KeyHandler keyHandler = new KeyHandler();

	public Player player;
	TileManager tileM;

	public Entity obj[] = new Entity[10];
	public AssetSetter aSetter = new AssetSetter(this);
	public CollisionChecker cChecker = new CollisionChecker(this);

	public UI ui;

	public GamePanel() {
		setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		addKeyListener(keyHandler);
		setFocusable(true);
		player = new Player(this, keyHandler);
		tileM = new TileManager(this);
		ui = new UI(this) ;
	}

	public void setupGame() {
		aSetter.setObject();
	}

	public void startGameThread() {
		gameThread = new Thread(this); 
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = NANO_SECOND / FPS;
		double nextDrawTime = System.nanoTime() + drawInterval; 

		while (gameThread != null) {                                                                   
			update();                                                                                  
			repaint();                                                                                 
                                                                                                   
			try {                                                                                      
				double remainingTime = nextDrawTime - System.nanoTime();                               
				remainingTime = remainingTime / 1000000;                
                                                                                                   
				if (remainingTime < 0) {                                                               
					remainingTime = 0;                                                                 
				}                                                                                      
                                                                                                   
				Thread.sleep((long) remainingTime);                                                    
				nextDrawTime += drawInterval;                                                                       
			} catch (InterruptedException e) {                                                         
				e.printStackTrace();                                                                   
			}                                                                                          
		}                                                                                              
	}                                  

	public void update() {
		player.update();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g; 
		tileM.draw(g2);

		for (int i = 0; i < obj.length; i++) {
			if (obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}

		player.draw(g2);
		ui.draw(g2);
		g2.dispose();

	}
}
