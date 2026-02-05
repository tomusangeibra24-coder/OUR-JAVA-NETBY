import java.util.Scanner;

public class VehicleAuction {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String regNumber;
        double vehicleCost;
        double deposits;
        double expenses;
        double balance;

        double[] bids = new double[3];
        int highestBidder = 0;

        System.out.print("Enter vehicle registration number: ");
        regNumber = input.nextLine();

        System.out.print("Enter vehicle cost: ");
        vehicleCost = input.nextDouble();

        System.out.print("Enter total deposits made: ");
        deposits = input.nextDouble();

        System.out.print("Enter expenses incurred: ");
        expenses = input.nextDouble();

        System.out.print("Enter balance left on vehicle: ");
        balance = input.nextDouble();

        for (int i = 0; i < 3; i++) {
            System.out.print("Enter bid amount for bidder " + (i + 1) + ": ");
            bids[i] = input.nextDouble();
        }

        for (int i = 1; i < 3; i++) {
            if (bids[i] > bids[highestBidder]) {
                highestBidder = i;
            }
        }

        double sellingPrice = bids[highestBidder];
        double totalCost = vehicleCost + expenses;
        double profitOrLoss = sellingPrice - totalCost;

        System.out.println("\n--- Vehicle Auction Summary ---");
        System.out.println("Registration Number: " + regNumber);
        System.out.println("Winning Bidder: Bidder " + (highestBidder + 1));
        System.out.println("Selling Price: " + sellingPrice);
        System.out.println("Balance Cleared: " + balance);

        if (profitOrLoss > 0) {
            System.out.println("Profit Made: " + profitOrLoss);
        } else if (profitOrLoss < 0) {
            System.out.println("Loss Made: " + Math.abs(profitOrLoss));
        } else {
            System.out.println("No Profit or Loss Made");
        }
    }
}