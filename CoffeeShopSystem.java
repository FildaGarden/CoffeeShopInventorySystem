import java.util.List;
import java.util.Scanner;

public class CoffeeShopSystem {
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean runAgain = true;

        // Main loop allowing program to run unless exited.
        while (runAgain) {

            // Local variables
            int choice = 0;
            List<Item> items = InventoryLoader.readInventory("data/inventory.csv");
            int EXIT = items.size() + 1;
            int CHECKOUT = items.size() + 2;
            boolean validItemChoice = false;
            Cart cart = new Cart();

            // Display main menu loop
            Menu.clearMenu();
            Menu.displayMenu();

            while (!validItemChoice) {
                try {
                    choice = scanner.nextInt();
                    // Unless user chooses either to exit or proceed to checkout -> stay in loop
                    while (choice != EXIT && choice != CHECKOUT) {
                        Menu.clearMenu();
                        Menu.displayMenu();
                        System.out.printf("\n%sYou added %s to the cart%s\n", Menu.RED, items.get(choice - 1).getName(),
                                Menu.RESET);
                        cart.addToCart(items.get(choice));
                        choice = scanner.nextInt();
                    }
                    validItemChoice = true;
                } catch (Exception e) {
                    System.out.println(Menu.RED + "\nInvalid input" + Menu.RESET);
                    scanner.nextLine();
                }
            }

            if (choice == EXIT) {
                System.out.println(Menu.RED + "Goodbye" + Menu.RESET);
                break;
            } else if (cart.getCart().isEmpty()) {
                System.out.println(Menu.RED + "Your cart is empty" + Menu.RESET);
            } else if (choice == CHECKOUT && !cart.getCart().isEmpty()) {

                TransactionHandler tHandler = new TransactionHandler();
                double totalPrice = cart.getTotalPrice();

                // Display total price
                Menu.displayTotalPrice(totalPrice);

                boolean validPaymentMethod = false;
                int paymentMethod = 0;

                // Display menu for checkout
                Menu.displayCheckoutMenu();
                while (!validPaymentMethod) {
                    try {
                        paymentMethod = scanner.nextInt();
                        if (paymentMethod == 1 || paymentMethod == 2) {
                            validPaymentMethod = true;
                        } else {
                            System.out.println(Menu.RED + "Invalid input" + Menu.RESET);
                        }
                    } catch (Exception e) {
                        System.out.println(Menu.RED + "Invalid input" + Menu.RESET);
                        scanner.nextLine();
                    }
                }

                // Get users choice for payment method
                if (paymentMethod == 1) {
                    boolean validCreditCardChoice = false;
                    int creditCardChoice = 0;

                    Menu.displayCreditCardMenu();
                    while (!validCreditCardChoice) {
                        try {
                            creditCardChoice = scanner.nextInt();
                            if (creditCardChoice == 1 || creditCardChoice == 2) {
                                validCreditCardChoice = true;
                            } else {
                                System.out.println(Menu.RED + "Invalid input" + Menu.RESET);
                            }
                        } catch (Exception e) {
                            System.out.println(Menu.RED + "Invalid input" + Menu.RESET);
                            scanner.nextLine();
                        }
                    }

                    // Get users choice for credit card type
                    if (creditCardChoice == 1) {
                        tHandler.setPaymentMethod("Visa");
                    } else if (creditCardChoice == 2) {
                        tHandler.setPaymentMethod("MasterCard");
                    }
                    // Finishing the order
                    tHandler.proceedToCheckout(cart, tHandler.getPaymentMethod());
                }
                // If user chose cash as payment method
                else if (paymentMethod == 2) {
                    boolean validCashAmount = false;
                    double tenderedAmount = 0;
                    tHandler.setPaymentMethod("Cash");
                    Menu.displayCashMenu();
                    while (!validCashAmount) {
                        try {
                            tenderedAmount = scanner.nextDouble();
                            if (tenderedAmount >= 0) {
                                validCashAmount = true;
                            } else {
                                System.out.println(Menu.RED + "Invalid input" + Menu.RESET);
                            }
                        } catch (Exception e) {
                            System.out.println(Menu.RED + "Invalid input" + Menu.RESET);
                            scanner.nextLine();
                        }
                    }

                    // Make sure user paid correct amount
                    boolean correctAmountPaid = false;
                    while (!correctAmountPaid && tenderedAmount < totalPrice) {
                        System.out.println("-------------------------------");
                        System.out.printf("%sYou are %.2f short%s\n", Menu.RED, totalPrice - tenderedAmount,
                                Menu.RESET);
                        System.out.println("-------------------------------");
                        System.out.println("Please enter missing amount: ");
                        try {
                            tenderedAmount += scanner.nextDouble();
                            if (tenderedAmount >= totalPrice) {
                                correctAmountPaid = true;
                            }
                        } catch (Exception e) {
                            System.out.println(Menu.RED + "Invalid input" + Menu.RESET);
                            scanner.nextLine();
                        }

                    }
                    // Set amount user has paid and handle the change
                    tHandler.setTenderedAmount(tenderedAmount);
                    tHandler.handleCash(tenderedAmount, cart);
                    Menu.clearMenu();

                    // Finishing the order
                    tHandler.proceedToCheckout(cart, tHandler.getPaymentMethod(), tenderedAmount, tHandler.getChange());
                }
            }

            // Press any key to continue
            System.out.println("Press any key to continue");
            scanner.next();
        }
        scanner.close();
    }
}
