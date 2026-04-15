package com.app;

import com.models.Customer;
import com.models.Product;
import com.models.Order;
import com.models.OrderStatus;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Get customer credentials
        System.out.println("=== Customer Registration ===");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        Customer customer = new Customer(name, email);

        // 2. Get product details
        System.out.println("\n=== Product Details ===");
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        Product product = new Product(productName, price);

        // 3. Get quantity
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // 4. Create the order (status = PENDING automatically)
        Order order = new Order(customer, product, quantity);

        // 5. Show order summary
        System.out.println("\n=== Order Created ===");
        System.out.println("Customer: " + order.getCustomer().getName() + " (" + order.getCustomer().getEmail() + ")");
        System.out.println("Product: " + order.getProduct().getName());
        System.out.printf("Price per unit: $%.2f%n", order.getProduct().getPrice());
        System.out.println("Quantity: " + order.getQuantity());
        System.out.printf("Total: $%.2f%n", order.getQuantity() * order.getProduct().getPrice());
        System.out.println("Status: " + order.getStatus());

        // 6. Optional: allow status update
        System.out.println("\n=== Update Order Status ===");
        System.out.println("Available statuses: PENDING, SHIPPED, DELIVERED, CANCELLED");
        System.out.print("Enter new status (or press Enter to skip): ");
        String statusInput = scanner.nextLine().trim().toUpperCase();

        if (!statusInput.isEmpty()) {
            OrderStatus newStatus = null;
            switch (statusInput) {
                case "PENDING":
                    newStatus = OrderStatus.PENDING;
                    break;
                case "SHIPPED":
                    newStatus = OrderStatus.SHIPPED;
                    break;
                case "DELIVERED":
                    newStatus = OrderStatus.DELIVERED;
                    break;
                case "CANCELLED":
                    newStatus = OrderStatus.CANCELLED;
                    break;
                default:
                    System.out.println("Invalid status. No change made.");
            }
            if (newStatus != null) {
                order.setStatus(newStatus);
                System.out.println("Order status updated to: " + order.getStatus());
            }
        }

        // 7. Final order details
        System.out.println("\n=== Final Order ===");
        System.out.println(order.getCustomer().getName() + " ordered " + order.getQuantity() + " x " + order.getProduct().getName());
        System.out.println("Current status: " + order.getStatus());

        scanner.close();
    }
}