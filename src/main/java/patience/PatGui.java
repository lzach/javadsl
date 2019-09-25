package patience;

import javax.swing.*;
import patience.interpreter.DSLPatGame;
/**
 * Created by ludvig on 24/06/15.
 */
public class PatGui extends JFrame {
    PatGame game;//= /*new KlondikeGame();//*/new DSLPatGame("golf.pat");

    PatGui(String gameName) {
        game = /*new KlondikeGame();//*/new DSLPatGame(gameName + ".pat");
        game.setup();
        add(new PatView(game));
    }

    public static void main(String[] args) {
        for ( String arg : args )
          System.out.println(arg);
        String name = args.length >= 1 ? args[0] : "klondike-new";
        PatGui p = new PatGui(name);
        p.setVisible(true);
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setSize(1000, 700);
    }
}
