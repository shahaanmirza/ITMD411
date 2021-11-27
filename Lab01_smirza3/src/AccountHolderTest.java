/*Name: Shahaan Mirza
Lab: 1
Project Description:
This test file acts as an account for a user.
The User is prompted to enter an initial balance for their account.
A following prompt asks them to enter the amount that they wish to deposit.
Lastly the user is prompted to enter the amount they wish to withdraw.
 */
import java.util.*;
public class AccountHolderTest {
    public static void main(String[] args){ //driver class
        Scanner sc = new Scanner(System.in); //create new scanner object
        System.out.println("Please enter your initial balance");
        double balance = sc.nextDouble(); //accept user input as initial balance
        AccountHolder acct1 = new AccountHolder(balance); //create new accountHolder object
        System.out.println("Please enter a deposit amount");
        double deposit = sc.nextDouble();
        acct1.deposit(deposit); //accept user input as deposit amount and run deposit method
        System.out.println("Please enter a withdrawal amount");
        //memory position for test balance value
        double withdraw = sc.nextDouble();
        acct1.withdraw(withdraw); //accept user input as withdrawal amount and run withdraw method
        acct1.monthlyInterest(); //calculates and adds monthlyInterest
        System.out.printf("Balance with interest applied = $%.2f",acct1.balance); //prints final balance output
    }
}
