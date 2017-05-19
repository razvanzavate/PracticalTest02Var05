package ro.pub.cs.systems.eim.practicaltest02var05;

/**
 * Created by student on 19.05.2017.
 */

public class Information {
    private String value;
    private String time;

    public Information() {
        this.value = null;
        this.time = null;
    }

    public Information(String value, String time) {
        this.value = value;
        this.time = time;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Information{" +
                "value='" + value+ '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
