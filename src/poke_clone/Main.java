package poke_clone;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame finestra = new JFrame();
		finestra.setTitle("PokeClone");
		finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		finestra.setResizable(false);
		
		GamePanel gamepanel = new GamePanel(); 
		finestra.add(gamepanel); 
		
		finestra.pack();
		finestra.setLocationRelativeTo(null);
		finestra.setVisible(true);

		// STEP 2.7: Avvia il thread di gioco.
		// Ora che la finestra è visibile, dobbiamo far partire il ciclo continuo del gioco.
		// TODO: Chiama il metodo startGameThread() sull'oggetto gamepanel.
		gamepanel.startGameThread();
	}

}
