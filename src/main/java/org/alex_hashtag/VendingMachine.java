package org.alex_hashtag;

import java.util.Scanner;


class Item
{
    public final String name;
    public final double price;
    public int quantity;

    public Item(String name, double price, int quantity)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}


public class VendingMachine
{
    private final Item[] items;
    private int totalSales;

    public VendingMachine(Item... items)
    {
        this.items = items;
        this.totalSales = 0;
    }

    public void displayItems()
    {
        int nameWidth = "Name".length();
        for (Item item : items)
        {
            if (item.name.length() > nameWidth)
            {
                nameWidth = item.name.length();
            }
        }

        String nameFormat = "%-" + nameWidth + "s";
        String headerFormat = "║ %-3s ║ " + nameFormat + " ║ %-5s ║ %-8s ║%n";
        String rowFormat = "║  %-3d ║ " + nameFormat + " ║ %-5.2f ║ %-8d ║%n";

        System.out.println("Available Items:");
        System.out.println("╔══════╦" + "═".repeat(nameWidth + 2) + "╦═══════╦══════════╗");
        System.out.printf(headerFormat, "Code", "Name", "Price", "Quantity");
        System.out.println("╠══════╬" + "═".repeat(nameWidth + 2) + "╬═══════╬══════════╣");
        for (int i = 0; i < items.length; i++)
        {
            System.out.printf(rowFormat, (i + 1), items[i].name, items[i].price, items[i].quantity);
        }
        System.out.println("╚══════╩" + "═".repeat(nameWidth + 2) + "╩═══════╩══════════╝");
    }

    public void purchaseItem(int itemIndex, Scanner scanner)
    {
        if (items[itemIndex].quantity > 0)
        {
            System.out.printf("You selected %s. Please insert $%.2f:%n", items[itemIndex].name, items[itemIndex].price);
            double moneyInserted = scanner.nextDouble();

            if (moneyInserted >= items[itemIndex].price)
            {
                items[itemIndex].quantity--;
                totalSales++;
                double change = moneyInserted - items[itemIndex].price;
                System.out.printf("Dispensing %s. Your change is $%.2f.%n", items[itemIndex].name, change);
            }
            else
            {
                System.out.println("Insufficient funds. Transaction canceled.");
            }
        }
        else
        {
            System.out.printf("Sorry, %s is out of stock.%n", items[itemIndex].name);
        }
    }

    public int getItemCount()
    {
        return items.length;
    }

    public double getTotalSales()
    {
        return totalSales;
    }

    public static class Admin
    {

        public static void refillInventory(VendingMachine machine, int itemIndex, int quantity)
        {
            if (itemIndex >= 0 && itemIndex < machine.items.length)
            {
                machine.items[itemIndex].quantity += quantity;
                System.out.printf("Refilled %s. New quantity: %d.%n", machine.items[itemIndex].name, machine.items[itemIndex].quantity);
            }
            else
            {
                System.out.println("Invalid item index.");
            }
        }

        public static void checkInventory(VendingMachine machine)
        {
            System.out.println("Current Inventory:");
            for (int i = 0; i < machine.items.length; i++)
            {
                System.out.printf("%d. %s: %d%n", (i + 1), machine.items[i].name, machine.items[i].quantity);
            }
        }

        public static void displayTotalSales(VendingMachine machine)
        {
            System.out.println("Total sales: " + machine.getTotalSales());
        }
    }
}