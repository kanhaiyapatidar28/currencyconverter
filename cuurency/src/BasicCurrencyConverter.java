import java.util.Scanner;
import java.util.TreeMap;

public class BasicCurrencyConverter {

    public static void main(String[] args) {
        TreeMap<String, Double> rates = loadRates();
        displayCurrencyOptions(rates);

        Scanner input = new Scanner(System.in);

        System.out.print("Enter the source currency code (e.g., USD, INR): ");
        String sourceCurrency = input.nextLine().toUpperCase();

        if (!rates.containsKey(sourceCurrency)) {
            System.out.println("Invalid source currency. Restart the program.");
            input.close();
            return;
        }

        System.out.print("Enter the target currency code (e.g., USD, INR): ");
        String targetCurrency = input.nextLine().toUpperCase();

        if (!rates.containsKey(targetCurrency)) {
            System.out.println("Invalid target currency. Restart the program.");
            input.close();
            return;
        }

        System.out.print("Enter the amount to convert: ");
        double amount;
        try {
            amount = input.nextDouble();
            if (amount <= 0) {
                System.out.println("Amount must be greater than zero.");
                input.close();
                return;
            }
        } catch (Exception e) {
            System.out.println("Invalid amount input. Restart the program with a valid number.");
            input.close();
            return;
        }

        double convertedAmount = calculateConversion(amount, sourceCurrency, targetCurrency, rates);
        System.out.printf("Converted amount: %.2f %s%n", convertedAmount, targetCurrency);

        input.close();
    }

    private static TreeMap<String, Double> loadRates() {
        TreeMap<String, Double> rates = new TreeMap<>();
        rates.put("USD", 1.0);  // United States Dollar
        rates.put("INR", 82.5); // Indian Rupee
        rates.put("EUR", 0.92); // Euro
        rates.put("JPY", 131.2); // Japanese Yen
        return rates;
    }

    private static void displayCurrencyOptions(TreeMap<String, Double> rates) {
        System.out.println("Available currency codes for conversion:");
        for (String currency : rates.keySet()) {
            System.out.println("- " + currency);
        }
    }

    private static double calculateConversion(double amount, String source, String target, TreeMap<String, Double> rates) {
        double sourceRate = rates.get(source);
        double targetRate = rates.get(target);
        return amount * (targetRate / sourceRate);
    }
}
