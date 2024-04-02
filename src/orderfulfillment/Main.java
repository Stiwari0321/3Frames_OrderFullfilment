package orderfulfillment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        List<orderfulfillment.Warehouse> warehouses = new ArrayList<>();
        warehouses.add(new Warehouse("Warehouse A"));
        warehouses.add(new Warehouse("Warehouse B"));

        // Initialize stock in warehouses
        warehouses.get(0).addStock("Product1", 10000);
        warehouses.get(1).addStock("Product1", 5000);

        OrderFulfillmentSystem system = new OrderFulfillmentSystem(warehouses);

        // Simulate concurrent order processing
        int numOrders = 100000;
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < numOrders; i++) {
            int orderNumber = i;
            executorService.submit(() -> {
                String productId = "Product1";
                int quantity = ThreadLocalRandom.current().nextInt(1, 10);
                String destination = "Destination";
                Order order = new Order("Order" + orderNumber, productId, quantity, destination);
                boolean success = system.processOrder(order);
                if (success) {
                    System.out.println("Order " + order.getOrderId() + " processed successfully");
                } else {
                    System.out.println("Order " + order.getOrderId() + " cannot be fulfilled");
                }
            });
        }

        // Update stock in warehouses periodically
        ScheduledExecutorService stockUpdaterService = Executors.newSingleThreadScheduledExecutor();
        stockUpdaterService.scheduleAtFixedRate(() -> {
            for (Warehouse warehouse : warehouses) {
                // Simulate real-time stock updates
                int quantity = ThreadLocalRandom.current().nextInt(100, 1000);
                executorService.submit(new StockUpdater(warehouse, "Product1", quantity));
            }
        }, 0, 1, TimeUnit.SECONDS);

        executorService.shutdown();
        stockUpdaterService.shutdown();
    }
}
