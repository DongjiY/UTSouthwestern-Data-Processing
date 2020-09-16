import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class OpenFolderListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        try {
            Desktop.getDesktop().open(new File(System.getProperty("user.dir")));
        } catch (Exception err) {
            System.err.println(err);
        }
    }
}