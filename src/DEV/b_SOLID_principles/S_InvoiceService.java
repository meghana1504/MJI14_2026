package DEV.b_SOLID_principles;

// THIS Class Violates SRP principle, because it has multiple unrelated responsibilities
// NOT GOOD
public class S_InvoiceService {
    void calculateInvoice() {

    }

    void saveToDatabase(){

    }

    void sendNotfication(){

    }
}

// SOLUTION
// Create 3 different classes, 1 class for each responsibility
// - So each class will have 1 JOB
// - We avoid fat classes
// - Changes are isolated
// - Code is easily testable
// - Production bugs and team/code conflicts are avoided

/*
* PRODUCT Thinking >
* 1. Payment
* 2. save
* 3. Notification
* - These should be 3 different services/ classes
*
* */