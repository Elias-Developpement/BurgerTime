import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
  public static final int WIDTH = 13 * 32;
  public static final int HEIGHT = 15 * 32 + 3;
  public Window() {
    // Window options
    this.setTitle("Frogger");
    this.setSize(WIDTH, HEIGHT);
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Game game = new Game();
    this.setContentPane(game);
    this.setVisible(true);
  }
}
