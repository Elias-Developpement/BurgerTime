import javax.swing.JFrame;

public class Window extends JFrame {
  public Window() {
    // Window options
    this.setTitle("Frogger");
    this.setSize(800, 600);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Game game = new Game();
    this.setContentPane(game);
    this.setVisible(true);
  }
}
