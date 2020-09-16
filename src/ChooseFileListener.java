import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;

public class ChooseFileListener implements ActionListener {
    private Main main;

    public ChooseFileListener(Main main) {
        this.main = main;
    }

    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        chooser.setDialogTitle("Choose a file");
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            main.setFilePath(chooser.getSelectedFile().getAbsolutePath());
            main.readData();
            main.clearDropDowns();
            main.addDataToDropDowns();
        }
    }
}