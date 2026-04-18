package Learning;

import java.util.ArrayList;
import java.util.Scanner;

class BankSys {
    private String accountHolder;
    private double accountBalance;
    private char accountType;
    private int noOfTransactions;
    private ArrayList<String> transactions = new ArrayList<>();
    public BankSys(String accountHolder, double accountBalance, char accountType ){
        this.accountHolder= accountHolder;
        this.accountBalance= accountBalance;
        this.accountType= accountType;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public char getAccountType() {
        return accountType;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setAccountType(char accountType) {
        this.accountType = accountType;
    }

    public void setNoOfTransactions(int noOfTransactions) {
        this.noOfTransactions = noOfTransactions;
    }

    public int getNoOfTransactions() {
        return noOfTransactions;
    }

    public void depositMoney(double amount){
        if (amount>0){
            double balance = amount + this.getAccountBalance();
            this.setAccountBalance(balance);
            System.out.println("Deposit of amount "+amount+" is successful ! \n The total balance is : "+balance);
            this.transactions.add("Deposited "+balance);
            noOfTransactions++;
        }
        else
            System.out.println("Please enter a positive amount");
    }

    public void withDrawMoney(double amount){
        double balance = this.getAccountBalance();
        char accType = this.getAccountType();
        if(amount>0 && this.accountBalance>=amount){
            double minBalance = balance-amount;
            if( (accType == 'S' && minBalance>=500) || (accType == 'C' && minBalance>=1000) ){
                balance-=amount;
                this.setAccountBalance(balance);
                System.out.println("Withdrawal of amount "+amount+" is successful ! \n The total balance is "+ balance);
                transactions.add("Withdrew "+balance);
                noOfTransactions++;
            }
            else{
                System.out.println("Minimum balance for account type "+accType+" is not maintained !");
            }
        }
        else{
            System.out.println("Withdrawal failed, check your balance !: "+balance);
        }
    }

    public void addInterest(){
        double accBalance =this.getAccountBalance();
        if(this.getAccountType()=='S'){
            System.out.println("Congrats, you earned interest of Rs: "+accBalance*0.05);
            double interest =accBalance*0.05;
            accBalance+=interest;
            System.out.println("After adding interest the total balance is : "+accBalance);
            this.setAccountBalance(accBalance);
            transactions.add("Interest added "+interest);
            noOfTransactions++;
        }
        else{
            System.out.println("Your account is not a Savings account !");
        }
    }

    public int printTransactions(){
        for(String transaction: transactions)
            System.out.println(transaction);
//        System.out.println("Total Transactions : "+this.noOfTransactions);
        return this.noOfTransactions;
    }

    public static void main(String[] args) {
        BankSys account1 = new BankSys("Meghana",80000,'S');
        int option;
        System.out.println("*********************** Welcome to MJ Bank ***********************");
        do{
            Scanner scan = new Scanner(System.in);
            System.out.println("Choose desired option :\n 1. Deposit\n" +
                    "2. Withdraw\n" +
                    "3. Check Balance\n" +
                    "4. Add Interest\n" +
                    "5. Exit  );");
            option=scan.nextInt();
            switch(option){
                case 1: {
                    double amount;
                    System.out.println("How much amount do you want to Deposit?");
                    amount=scan.nextInt();
                    account1.depositMoney(amount);
                    break;
                }
                case 2: {
                    double amount;
                    System.out.println("How much amount do you want to Withdraw?");
                    amount=scan.nextInt();
                    account1.withDrawMoney(amount);
                    break;
                }
                case 3: {
                    System.out.println("Available balance is : "+account1.getAccountBalance());
                    break;
                }
                case 4: {
                    System.out.println("Adding interest in your account !");
                    account1.addInterest();
                    break;
                }
                case 5: {
                    System.out.println("Your past transactions are as below : \n"+account1.printTransactions()+"\n");
                    System.out.println("*********** Thank you for using our services, Now exiting...! ***********");
                    break;
                }
                default : {
                    System.out.println("Please choose any of the available options !");
                    break;
                }
            }
        }
        while(option!=5);
    }
}