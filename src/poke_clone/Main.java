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
        
        gamepanel.setupGame();
        gamepanel.startGameThread();
    }
}
