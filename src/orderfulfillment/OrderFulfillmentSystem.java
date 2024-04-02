package orderfulfillment;

import java.util.List;

public class OrderFulfillmentSystem {
    private List<Warehouse> warehouses;

    public OrderFulfillmentSystem(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    private Warehouse findNearestWarehouse(String destination) {
        // Implement nearest warehouse finding logic here
        return warehouses.get(0); // Placeholder
    }

    public synchronized boolean processOrder(Order order) {
        Warehouse nearestWarehouse = findNearestWarehouse(order.getDestination());
        if (nearestWarehouse.hasStock(order.getProductId(), order.getQuantity())) {
            return nearestWarehouse.fulfillOrder(order.getProductId(), order.getQuantity());
        }
        return false;
    }
}
