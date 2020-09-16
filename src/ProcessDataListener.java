import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Set;
import javax.swing.Timer;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class ProcessDataListener implements ActionListener {
    private Main main;
    private String outputFilePath;

    public ProcessDataListener(Main main) {
        this.main = main;
        outputFilePath = System.getProperty("user.dir") + "\\output.csv";
    }

    public void actionPerformed(ActionEvent e) {
        if (main.getSampleDropDown().getItemCount() == 0 || main.getDetectorDropDown().getItemCount() == 0)
            return;
        main.getProgressLabel().setText("Processing Data...");
        String sampleSelection = (String) main.getSampleDropDown().getSelectedItem();
        String detectorSelection = (String) main.getDetectorDropDown().getSelectedItem();
        Collection<Sample> samples = main.getData().values();
        Set<String> detectorNames = main.getData().firstEntry().getValue().keySet();
        calculateACT(samples);
        calculateDCT(samples, detectorNames, detectorSelection);
        calculateDDCT(samples, detectorNames, sampleSelection);
        calculateRQ(samples);
        writeToFile(samples, detectorSelection);
        main.getProgressLabel().setText("Finished Processing Data!");
        new Timer(5000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.getProgressLabel().setText("");
            }
        }).start();
    }

    private void calculateACT(Collection<Sample> samples) {
        // System.out.println("ACT");
        for (Sample sample : samples) {
            // System.out.println("- " + sample.getName());
            for (Detector detector : sample.values()) {
                detector.calculateACT();
                // System.out.println("-- " + detector.getName() + " " + detector.getACT());
            }
        }
    }

    private void calculateDCT(Collection<Sample> samples, Set<String> detectorNames, String detectorSelection) {
        // System.out.println("DCT");
        for (Sample sample : samples) {
            // System.out.println("- " + sample.getName());
            Detector chosen = sample.get(detectorSelection);
            for (Detector detector : sample.values()) {
                detector.calculateDCT(chosen.getACT());
                // System.out.println("-- " + detector.getName() + " " + detector.getDCT());
            }
        }
    }

    private void calculateDDCT(Collection<Sample> samples, Set<String> detectorNames, String sampleSelection) {
        // System.out.println("DDCT");
        for (String detectorName : detectorNames) {
            // System.out.println("- " + detectorName);
            Sample chosen = main.getData().get(sampleSelection);
            for (Sample sample : samples) {
                Detector current = sample.get(detectorName);
                current.calculateDDCT(chosen.get(detectorName).getDCT());
                // System.out.println("-- " + sample.getName() + " " + current.getDDCT());
            }
        }
    }

    private void calculateRQ(Collection<Sample> samples) {
        // System.out.println("RQ");
        for (Sample sample : samples) {
            // System.out.println("- " + sample.getName());
            for (Detector detector : sample.values()) {
                detector.calculateRQ();
                // System.out.println("-- " + detector.getName() + " " + detector.getRQ());
            }
        }
    }

    private void writeToFile(Collection<Sample> samples, String detectorSelection) {
        try (PrintWriter out = new PrintWriter(
                new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFilePath)), 1 << 16))) {
            for (Sample sample : samples) {
                for (Detector detector : sample.values()) {
                    if (detector.getName().equals(detectorSelection))
                        continue;
                    out.println(sample.getName() + "," + detector.getName() + "," + detector.getRQ());
                }
            }
        } catch (Exception err) {
            System.err.println(err);
        }
    }
}