package DEV.b_SOLID_principles;

public class O_DiscountServices {
    // lets say we give different types of discount based on customer type
    public double calculateDiscount(String customerType){
        if(customerType.equals("Premium")){
            return 20.00;
        }
        else if(customerType.equals("Basic")){
            return 5.00;
        }
        return 0.0;
    }
}

// Now suppose in future we get more types of customers, we will have to modify the ENTIRE O_DiscountServices
// without doing this we wont be able to extend functionality
// But this is the violation of Open-Closed principle

// SOLUTION
// Create Interface Discount
// create a method calculateDiscount(String customerType) in it
// Make as many classes as you want which implement this interface - eg; class VIPDiscount implements Discount / PremiumCustDiscount implements Discount etc
// Now in this way, even though any new type of customers come up in the future, we will not have to modify any existing class
// But we will only have to ADD a new class, which implements the existing interface
