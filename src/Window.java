import javax.swing.JFrame;

public class Window extends JFrame {
  public Window() {
    // Window options
    this.setTitle("Frogger");
    this.setSize(672, 480);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Game game = new Game();
    this.setContentPane(game);
    this.setVisible(true);
  }
}
