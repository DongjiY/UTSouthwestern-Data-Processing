import java.util.ArrayList;

public class Detector extends ArrayList<Double> implements Comparable<Detector> {
    private static final long serialVersionUID = 2019_08_06_001L;

    private String name;
    private Double act, dct, ddct, rq;

    public Detector(String name) {
        super();
        this.name = name;
    }

    public void calculateACT() {
        if (size() == 0) {
            act = 0d;
        } else {
            double total = 0;
            for (int x = 0; x < size(); x++)
                total += get(x);
            act = total / size();
        }
    }

    public Double getACT() {
        return act;
    }

    public void calculateDCT(Double subtractor) {
        if (act == null || subtractor == null)
            return;
        dct = act - subtractor;
    }

    public Double getDCT() {
        return dct;
    }

    public void calculateDDCT(Double subtractor) {
        if (dct == null || subtractor == null)
            return;
        ddct = dct - subtractor;
    }

    public Double getDDCT() {
        return ddct;
    }

    public void calculateRQ() {
        if (ddct == null)
            return;
        rq = Math.pow(2, -ddct);
    }

    public Double getRQ() {
        return rq;
    }

    public String getName() {
        return name;
    }

    public int compareTo(Detector e) {
        return name.compareTo(e.name);
    }

    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (o instanceof Detector) {
            Detector other = (Detector) o;
            return name.equals(other.name);
        }
        return false;
    }

    public int hashCode() {
        return name.hashCode();
    }
}