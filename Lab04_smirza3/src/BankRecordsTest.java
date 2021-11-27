public class BankRecordsTest {
    public static void main(String[] args) {
        BankRecords recordObject = new BankRecords();
        System.out.println("ID \t\t"+"AGE\t"+"SEX\t\t"+"REGION\t"+"INCOME\t\t"+"MORTGAGE");
        recordObject.readData();
    }
}
