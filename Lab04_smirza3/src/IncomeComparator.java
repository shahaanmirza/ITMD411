import java.util.Comparator;

public class IncomeComparator implements Comparator<BankRecords> {
    public int compare(BankRecords o1, BankRecords o2) {
        int result = String.valueOf(o1.getIncome()).compareTo(String.valueOf(o2.getIncome()));
        return result;
    }
}