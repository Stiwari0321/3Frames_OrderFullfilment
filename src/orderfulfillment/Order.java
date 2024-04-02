package orderfulfillment;

public class Order {
    private String orderId;
    private String productId;
    private int quantity;
    private String destination;

    public Order(String orderId, String productId, int quantity, String destination) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.destination = destination;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDestination() {
        return destination;
    }
}
