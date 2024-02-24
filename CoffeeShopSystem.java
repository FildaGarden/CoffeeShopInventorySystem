import java.util.Scanner;

public class CoffeeShopSystem {
    public void run() {
        //Local variables
        int choice;
        int EXIT = Menu.menuItems.size() + 1;
        int CHECKOUT = Menu.menuItems.size() + 2;
        Cart cart = new Cart();

        //Display main menu loop
        Menu.displayMenu();
        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextInt();

        //Unless user chooses either to exit or proceed to checkout -> stay in loop
        while(choice != EXIT && choice != CHECKOUT) {
            Menu.clearMenu();
            Menu.displayMenu();
            System.out.println(Menu.RED + "You added " + Menu.menuItems.get(choice-1).getName() + " to the cart" + Menu.RESET);
            cart.addToCart(Menu.menuItems.get(choice-1).getOptionNumber(), Menu.menuItems.get(choice-1).getName(), Menu.menuItems.get(choice-1).getPrice());
            choice = scanner.nextInt();
        }
    
        if(choice == EXIT) {
            System.out.println("Goodbye");
        }
        else if(choice == CHECKOUT) {
            TransactionHandler tHandler = new TransactionHandler();

            //Display total price
            System.out.println("-------------------------------");
            System.out.printf("Your total is %.2f\n", cart.getTotalPrice());
            System.out.println("-------------------------------");
            
            //Display menu for checkout
            Menu.displayCheckoutMenu();

            //Get users choice for payment method
            int paymentMethod = scanner.nextInt();
            if(paymentMethod == 1){
                Menu.displayCreditCardMenu();
                int creditCardChoice = scanner.nextInt();

                //Get users choice for credit card type
                if(creditCardChoice == 1) {
                    tHandler.setPaymentMethod("Visa");
                }
                else if(creditCardChoice == 2) {
                    tHandler.setPaymentMethod("MasterCard");
                }
                //Finishing the order
                tHandler.proceedToCheckout(cart, tHandler.getPaymentMethod());
            }
            //If user chose cash as payment method
            else if(paymentMethod == 2) {
                tHandler.setPaymentMethod("Cash");
                Menu.displayCashMenu();
                double tenderedAmount = scanner.nextDouble();

                //Make sure user paid correct amount
                while(tenderedAmount < cart.getTotalPrice()) {
                    System.out.println("-------------------------------");
                    System.out.printf("You are %.2f short\n\n", cart.getTotalPrice() - tenderedAmount);
                    System.out.println("Please enter missing amount: ");
                    tenderedAmount += scanner.nextDouble();
                }
                //Set amount user has paid and handle the change
                tHandler.setTenderedAmount(tenderedAmount);   
                tHandler.handleCash(tenderedAmount, cart);
                //Finishing the order
                tHandler.proceedToCheckout(cart, tHandler.getPaymentMethod(), tenderedAmount, tHandler.getChange());
            }
        }
    scanner.close();    
    }
    
}
