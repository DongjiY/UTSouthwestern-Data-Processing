import java.awt.Dimension;
import java.awt.Font;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Create {
    private Create() {
        // prevent instantiation
    }

    public static JComboBox<String> jComboBox(int width, int height) {
        Dimension dimension = new Dimension(width, height);
        JComboBox<String> ret = new JComboBox<>();
        ret.setPreferredSize(dimension);
        ret.setMaximumSize(dimension);
        ret.setMinimumSize(dimension);
        ret.setAlignmentX(Component.CENTER_ALIGNMENT);
        ret.setFont(Font.decode("Consolas"));
        return ret;
    }

    public static JLabel label(String text) {
        JLabel ret = new JLabel(text, SwingConstants.CENTER);
        ret.setAlignmentX(Component.CENTER_ALIGNMENT);
        ret.setFont(Font.decode("Trebuchet MS"));
        return ret;
    }

    public static JLabel label(String text, int width, int height) {
        JLabel ret = Create.label(text);
        Dimension dim = new Dimension(width, height);
        ret.setMaximumSize(dim);
        ret.setPreferredSize(dim);
        ret.setMinimumSize(dim);
        return ret;
    }

    public static JButton button(String text, int width, int height) {
        Dimension dimension = new Dimension(width, height);
        JButton ret = new JButton(text);
        ret.setPreferredSize(dimension);
        ret.setMaximumSize(dimension);
        ret.setMinimumSize(dimension);
        ret.setAlignmentX(Component.CENTER_ALIGNMENT);
        ret.setFont(Font.decode("Trebuchet MS"));
        return ret;
    }

    public static Box.Filler filler(int width, int height) {
        Dimension dim = new Dimension(width, height);
        return new Box.Filler(dim, dim, dim);
    }
}