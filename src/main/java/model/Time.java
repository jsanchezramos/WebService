package model;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;

@XmlRootElement
public final class Time {
    private final Timestamp result;

    public Time(Timestamp result) {

        this.result = result;
    }

    public Timestamp getResult() {
        return result;
    }
}
