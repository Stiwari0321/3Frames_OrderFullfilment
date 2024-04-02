package orderfulfillment;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private String name;
    private Map<String, Integer> stock;

    public Warehouse(String name) {
        this.name = name;
        this.stock = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public synchronized void addStock(String productId, int quantity) {
        stock.put(productId, stock.getOrDefault(productId, 0) + quantity);
    }

    public synchronized boolean hasStock(String productId, int quantity) {
        return stock.getOrDefault(productId, 0) >= quantity;
    }

    public synchronized boolean fulfillOrder(String productId, int quantity) {
        if (hasStock(productId, quantity)) {
            stock.put(productId, stock.get(productId) - quantity);
            return true;
        }
        return false;
    }

    public synchronized int getStock(String productId) {
        return stock.getOrDefault(productId, 0);
    }
}
