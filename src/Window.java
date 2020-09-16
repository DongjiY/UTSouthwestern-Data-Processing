import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class Window extends JFrame {
    private static final long serialVersionUID = 2019_08_05_001L;
    private final int WIDTH = 500, HEIGHT = 500;

    public Window(String title) {
        super(title);
        Dimension dimension = new Dimension(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setResizable(true);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    }
}