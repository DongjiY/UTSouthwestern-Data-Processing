import java.awt.Font;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class Main {
    private String filepath;
    private Data data;
    private Window window;
    private JComboBox<String> sampleList, detectorList;
    private JLabel progress;

    public Main() {
        createWindow();
    }

    public void readData() {
        data = new Data();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), "utf-8"),
                1 << 16)) {
            String line = br.readLine(); // skip the first line
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                Sample sample = new Sample(arr[0]);
                if (data.containsKey(sample.getName()))
                    sample = data.get(sample.getName());
                else
                    data.put(sample.getName(), sample);
                Detector detector = new Detector(arr[1]);
                if (sample.containsKey(detector.getName()))
                    detector = sample.get(detector.getName());
                else
                    sample.put(detector.getName(), detector);
                detector.add(Double.parseDouble(arr[2]));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void clearDropDowns() {
        if (sampleList.getItemCount() > 0)
            sampleList.removeAllItems();
        if (detectorList.getItemCount() > 0)
            detectorList.removeAllItems();
    }

    public void addDataToDropDowns() {
        for (String sample : data.keySet())
            sampleList.addItem(sample);
        for (String detector : data.firstEntry().getValue().keySet())
            detectorList.addItem(detector);
    }

    private void createWindow() {
        window = new Window("Data Analyzer");

        // label
        JLabel label = Create.label("Select two options and click OK");
        window.add(Create.filler(0, 10));
        window.add(label);
        window.add(Create.filler(0, 10));

        // labels for dropdowns
        JLabel sampleLabel = Create.label("Sample", 120, 15);
        JLabel detectorLabel = Create.label("Detector", 120, 15);
        JPanel pane1 = new JPanel();
        pane1.setLayout(new BoxLayout(pane1, BoxLayout.X_AXIS));
        pane1.add(sampleLabel);
        pane1.add(Create.filler(5, 0));
        pane1.add(detectorLabel);
        window.add(pane1);

        window.add(Create.filler(0, 5));

        // dropdowns
        sampleList = Create.jComboBox(120, 30);
        detectorList = Create.jComboBox(120, 30);
        JPanel pane2 = new JPanel();
        pane2.setLayout(new BoxLayout(pane2, BoxLayout.X_AXIS));
        pane2.add(sampleList);
        pane2.add(Create.filler(5, 0));
        pane2.add(detectorList);
        window.add(pane2);

        window.add(Create.filler(0, 10));

        // ok and output buttons
        JButton okButton = Create.button("OK", 50, 30);
        okButton.addActionListener(new ProcessDataListener(this));
        JButton openButton = Create.button("Open Output Location", 190, 30);
        openButton.addActionListener(new OpenFolderListener());
        JPanel pane3 = new JPanel();
        pane3.setLayout(new BoxLayout(pane3, BoxLayout.X_AXIS));
        pane3.add(okButton);
        pane3.add(Create.filler(5, 0));
        pane3.add(openButton);
        window.add(pane3);

        window.add(Create.filler(0, 10));

        // file chooser button
        JButton chooseButton = Create.button("Choose File", 100, 30);
        chooseButton.addActionListener(new ChooseFileListener(this));
        JPanel pane4 = new JPanel();
        pane4.setLayout(new BoxLayout(pane4, BoxLayout.X_AXIS));
        pane4.add(chooseButton);
        window.add(pane4);

        window.add(Create.filler(0, 10));

        // progress label
        progress = Create.label("");
        progress.setFont(Font.decode("Consolas"));
        window.add(progress);

        window.setVisible(true);
    }

    public void setFilePath(String filepath) {
        this.filepath = filepath;
    }

    public String getFilePath() {
        return filepath;
    }

    public Data getData() {
        return data;
    }

    public Window getWindow() {
        return window;
    }

    public JComboBox<String> getSampleDropDown() {
        return sampleList;
    }

    public JComboBox<String> getDetectorDropDown() {
        return detectorList;
    }

    public JLabel getProgressLabel() {
        return progress;
    }

    public static void main(String[] args) {
        new Main();
    }
}