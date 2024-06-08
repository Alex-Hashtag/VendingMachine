package org.alex_hashtag;

import java.util.Scanner;


public class Main
{

    public static void main(String[] args)
    {
        VendingMachine vendingMachine = new VendingMachine(
                new Item("Soda", 1.5, 5),
                new Item("Chips", 1.0, 5),
                new Item("Chocolate Bar", 1.25, 5),
                new Item("Water", 0.75, 5)
        );

        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (true)
        {
            System.out.println("1. Customer Mode");
            System.out.println("2. Admin Mode");
            System.out.println("0. Exit");
            System.out.print("Select mode: ");
            int mode = scanner.nextInt();

            if (mode == 1)
            {
                while (choice != 0)
                {
                    vendingMachine.displayItems();
                    System.out.println("Enter the code of the item you want to purchase (or 0 to exit):");
                    choice = scanner.nextInt();

                    if (choice > 0 && choice <= vendingMachine.getItemCount())
                        vendingMachine.purchaseItem(choice - 1, scanner);
                }
            }
            else if (mode == 2)
            {
                System.out.println("Admin Mode:");
                System.out.println("1. Refill Inventory");
                System.out.println("2. Check Inventory");
                System.out.println("3. Display Total Sales");
                System.out.println("0. Exit Admin Mode");
                System.out.print("Enter your choice: ");
                int adminChoice = scanner.nextInt();

                switch (adminChoice)
                {
                    case 1 ->
                    {
                        System.out.print("Enter the item code to refill: ");
                        int itemCode = scanner.nextInt();
                        System.out.print("Enter the quantity to add: ");
                        int quantity = scanner.nextInt();
                        VendingMachine.Admin.refillInventory(vendingMachine, itemCode - 1, quantity);
                    }
                    case 2 -> VendingMachine.Admin.checkInventory(vendingMachine);
                    case 3 -> VendingMachine.Admin.displayTotalSales(vendingMachine);
                    case 0 ->
                    {
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
            else if (mode == 0)
            {
                VendingMachine.Admin.displayTotalSales(vendingMachine);
                System.out.println("Thank you for using the vending machine. Goodbye!");
                break;
            }
            else
                System.out.println("Invalid mode. Please try again.");
        }
    }
}
