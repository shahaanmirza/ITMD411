import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class Records extends BankRecords {

    //create formatted object to write output directly to console & file
    static FileWriter fw = null;

    public Records() {
        try {
            fw = new FileWriter("bankrecords.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Records br = new Records();
        br.readData();

        // call functions to perform analytics
        AvgComp();     // analyze average income per gender
        femsComp();  // female count w. mort/savings accounts
        malesComp(); // male counts per loc. w. car & 1 child

        // ADD NAME AND DATE
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        try {
            fw.write("\nName: Shahaan Mirza" + "\nDate: " + dateFormat.format(cal.getTime()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // *** close out file object ***//
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void AvgComp() {

        Arrays.sort(robjs, new IncomeComparator());

        // set up needed variables to gather counts & income by sex
        // to determine average income by sex

        int maleCt = 0, femCt = 0;
        double maleInc = 0, femInc = 0;

        for (BankRecords robj : robjs)
            if (robj.getSex().equals("FEMALE")) {
                femInc += robj.getIncome();
                femCt++;
            } else {
                maleInc += robj.getIncome();
                maleCt++;
            }
        // display resulting averages to console and to file
        System.out.println("\n\n\n\n\n");
        System.out.println(". . .");
        System.out.println("Data analytics results:");
        System.out.printf("\nAverage income for Females: $%.2f", (femInc/femCt));
        System.out.printf("\nAverage income for Males: $%.2f", (maleInc/maleCt));

        try {
            fw.write("Average income for Females: $" + String.format("%.2f",femInc/femCt));
            fw.write("\nAverage income for Males: $" + String.format("%.2f",maleInc/maleCt));
            fw.write("\n...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void femsComp() {

        Arrays.sort(robjs, new FemComparator());

        int femCt = 0;
        for (int i= 0; i < robjs.length; i++) {
            if (robjs[i].getSex().equals("FEMALE") && robjs[i].getMortgage().equals("YES") && robjs[i].getSave_act().equals("YES")) {
                femCt++;
            }
        }

        System.out.println("\n\nNumber of Females with a Mortgage and Savings account: " + femCt);
        try {
            fw.write("\nNumber of Females with a Mortgage and Savings account: " + femCt);
            fw.write("\n...");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void malesComp() {

        Arrays.sort(robjs, new MaleComparator());

        //setup variables
        int innerCityCt = 0, ruralCt = 0, suburbanCt = 0, townCt = 0;

        for (BankRecords robj : robjs) {
            if (robj.getSex().equals("MALE") && robj.getRegion().equals("INNER_CITY") && robj.getCar().equals("YES") && robj.getChildren() == 1) {
                innerCityCt++;
            }
            if (robj.getSex().equals("MALE") && robj.getRegion().equals("RURAL") && robj.getCar().equals("YES") && robj.getChildren() == 1) {
                ruralCt++;
            }
            if (robj.getSex().equals("MALE") && robj.getRegion().equals("SUBURBAN") && robj.getCar().equals("YES") && robj.getChildren() == 1) {
                suburbanCt++;
            }
            if (robj.getSex().equals("MALE") && robj.getRegion().equals("TOWN") && robj.getCar().equals("YES") && robj.getChildren() == 1) {
                townCt++;
            }
        }

        System.out.println("\nInner City Males with a car and 1 child: " + innerCityCt);
        System.out.println("Rural Males with a car and 1 child: " + ruralCt);
        System.out.println("Suburban Males with a car and 1 child: " + suburbanCt);
        System.out.println("Town Males with a car and 1 child: " + townCt);

        try {
            fw.write("\nNumber of Inner City Males with a car and 1 child: " + innerCityCt);
            fw.write("\nNumber of Rural Males with a car and 1 child: " + ruralCt);
            fw.write("\nNumber of Suburban Males with a car and 1 child: " + suburbanCt);
            fw.write("\nNumber of Town Males with a car and 1 child: " + townCt);
            fw.write("\n...");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
