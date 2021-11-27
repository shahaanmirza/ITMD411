/*Name: Shahaan Mirza
Project Description: Read data from a CSV file and print out the first 25 rows.
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankRecords extends Client {

    //setup static objects for IO processing
    //array of BankRecords objects
    static BankRecords robjs[] = new BankRecords[600];
    //arraylist to hold spreadsheet rows & columns
    static ArrayList<List<String>> array = new ArrayList<>();

    //instance fields
    private String id;
    private int age;
    private int children;
    private String sex;
    private String region;
    private String married;
    private String car;
    private String save_act;
    private String current_act;
    private String mortgage;
    private String pep;
    private final String path="/Users/shahaanmirza/Desktop/School/ITM411/Lab2/bank-Detail.csv";
    public double income;

    //getter methods
    public int getAge() {
        return age;
    }
    public int getChildren() {
        return children;
    }
    public String getId() {
        return id;
    }
    public String getSex() {
        return sex;
    }
    public String getRegion() {
        return region;
    }
    public String getMarried() {
        return married;
    }
    public String getCar() {
        return car;
    }
    public String getSave_act() {
        return save_act;
    }
    public String getCurrent_act() {
        return current_act;
    }
    public String getMortgage() {
        return mortgage;
    }
    public String getPep() {
        return pep;
    }
    public double getIncome() {
        return income;
    }

    //setter methods
    public void setId(String id) {
        this.id = id;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setChildren(int chil){
        this.children = chil;
    }
    public void setSex(String sex){
        this.sex = sex;
    }
    public void setRegion(String reg){
        this.region = reg;
    }
    public void setMarried(String mar){
        this.married = mar;
    }
    public void setCar(String car){
        this.car = car;
    }
    public void setSave_act(String sact){
        this.save_act = sact;
    }
    public void setCurrent_act(String cact){
        this.current_act = cact;
    }
    public void setMortgage(String mort){
        this.mortgage = mort;
    }
    public void setPep(String pep){
        this.pep = pep;
    }
    public void setIncome(double inc){
        this.income = inc;
    }

    //overridden abstract methods
    @Override
    public void readData(){
        //create buffered reader to read file path
        {
            try {
                String line;
                BufferedReader br;
                br = new BufferedReader(new FileReader("bank-Detail.csv"));
                //parse csv lines
                while((line = br.readLine()) != null){
                    array.add(Arrays.asList(line.split(",")));
                }
                processData();  //call function for processing record data
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void processData() {
        //create index for array while iterating thru arraylist
        int idx = 0;

        //create for each loop to cycle thru arraylist of values
        //and PASS that data into your record objects' setters
        for (List<String> rowData : array) {
            //initialize array of objects
            robjs[idx] = new BankRecords();
            //call setters below and populate them, item by item
            robjs[idx].setId(rowData.get(0)); //get 1st column
            robjs[idx].setAge(Integer.parseInt(rowData.get(1))); //get 2nd column
            robjs[idx].setSex(rowData.get(2)); //get 3rd column
            robjs[idx].setRegion(rowData.get(3)); //get 4th column
            robjs[idx].setIncome(Double.parseDouble(rowData.get(4))); //get  5th column
            robjs[idx].setMarried(rowData.get(5)); //get 6th column
            robjs[idx].setChildren(Integer.parseInt(rowData.get(6))); //get 7th column
            robjs[idx].setCar(rowData.get(7)); //get 8th column
            robjs[idx].setSave_act(rowData.get(8)); //get 9th column
            robjs[idx].setCurrent_act(rowData.get(9)); //get 10th column
            robjs[idx].setMortgage(rowData.get(10)); //get 11th column

              /*continue processing arraylist item values into each array
                object-> robjs[] by index*/
            idx++;
        }
        printData();  //call function to print objects held in memory
    }


    @Override
    public void printData() {
        for(int i =0;i<25;i++){
            System.out.println(robjs[i].getId()+"\t"+robjs[i].getAge()+"\t"+robjs[i].getSex()+"\t"+robjs[i].getRegion()+"\t"+robjs[i].getIncome()+"\t\t"+robjs[i].getMortgage());
        }
    }
}
