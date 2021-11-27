public class AccountHolder { //abstract accountHolder class holds methods
    protected double balance; //account holder's balance attribute
    protected static double annualInterestRate = .04; //account holder's interest rate (4%)

    public AccountHolder(double x){ //constructor
        if(x<0)
            throw new IllegalArgumentException("ERROR: starting balance must be positive");
        else
            this.balance = x;
    }
    public void deposit(double x){
        this.balance += x;
    }
    public void withdraw(double x){
        if((this.balance - x)<50.0)
            throw new IllegalArgumentException("ERROR: balance cannot be below 50");
        else
            this.balance = this.balance - x;
    }
    public void monthlyInterest(){
        double temp = (this.balance * (annualInterestRate / 12.0));
        this.balance += temp;
    }
}
