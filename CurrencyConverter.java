import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {

    private static Map<String, Double> exchangeRates = new HashMap<>();

    static {
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.85);
        exchangeRates.put("JPY", 110.0);
        exchangeRates.put("GBP", 0.75);
        exchangeRates.put("AUD", 1.35);
        exchangeRates.put("INR", 83.0);
    }

    public static double convert(double amount, String fromCurrency, String toCurrency) {
        if (!exchangeRates.containsKey(fromCurrency) || !exchangeRates.containsKey(toCurrency)) {
            throw new IllegalArgumentException("Invalid currency code");
        }
        double baseAmount = amount / exchangeRates.get(fromCurrency);
        return baseAmount * exchangeRates.get(toCurrency);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCurrency Converter");
            System.out.print("Enter amount (or type 'exit' to quit): ");
            String input = scanner.next();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the currency converter.");
                break;
            }

            double amount;
            try {
                amount = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a valid number.");
                continue;
            }

            System.out.print("Enter source currency (USD, EUR, JPY, GBP, AUD, INR): ");
            String fromCurrency = scanner.next().toUpperCase();

            System.out.print("Enter target currency (USD, EUR, JPY, GBP, AUD, INR): ");
            String toCurrency = scanner.next().toUpperCase();

            try {
                double convertedAmount = convert(amount, fromCurrency, toCurrency);
                System.out.printf("%.2f %s is equal to %.2f %s%n", amount, fromCurrency, convertedAmount, toCurrency);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }
}
