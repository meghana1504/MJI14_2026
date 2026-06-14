package DEV.a_JavaBasics.A6_InnerClasses;

public class C_LocalInnerClass {
    // Check out ---- NOT IMPLEMENTED :) Due to time constraints

    /*
    * Payment example
    * Payment Interface has pay() method
    * We can define this method DIRECTLY in the main class using below syntax
      shoppingCart.processPayment( new Payment()  {
            @Override
            public void pay(double amount){
                sout("Payment done");
            }
       })
    * So I dont have to create a SEPARATE CREDIT CARD PAYMENT CLASS
    *
    * WELL, this is a bad example since in real life we have n ways to make a payment
    *
    * BUT, This is useful where, an interface has ONLY ONE METHOD AND ONLY ONE IMPLEMENTATION
    *
    * AND later this can be replaced by LAMBDA expressions and FUNCTIONAL interfaces
    *
    * */
}
