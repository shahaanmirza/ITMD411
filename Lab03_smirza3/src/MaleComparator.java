import java.util.Comparator;

public class MaleComparator implements Comparator<BankRecords> {
    public int compare(BankRecords o1, BankRecords o2) {
        int result = o1.getSex().compareTo(o2.getSex());
        return result;
    }
}