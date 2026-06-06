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

        // STEP 8.10: Chiama il metodo per preparare il gioco e gli oggetti prima di avviare il thread di gioco.
        // TODO: Chiama 'gamepanel.setupGame();' prima di 'gamepanel.startGameThread();'
        gamepanel.startGameThread();
    }

}
