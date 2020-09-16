import java.util.TreeMap;

public class Sample extends TreeMap<String, Detector> implements Comparable<Sample> {
    private static final long serialVersionUID = 2019_08_06_001L;

    private String name;

    public Sample(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int compareTo(Sample e) {
        return name.compareTo(e.name);
    }

    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (o instanceof Sample) {
            Sample other = (Sample) o;
            return name.equals(other.name);
        }
        return false;
    }

    public int hashCode() {
        return name.hashCode();
    }
}