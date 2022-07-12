package framework.model;

import java.util.Date;

public interface IEntry {
    void process();
    double getAmount();
    Date getDate();
}
