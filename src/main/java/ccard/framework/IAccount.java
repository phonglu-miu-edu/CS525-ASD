package ccard.framework;

import java.util.List;

public interface IAccount {
    void addEntry(Entry entry);
    double getCurrentBalance();
    Object generateReport();
    void addInterest();
}
