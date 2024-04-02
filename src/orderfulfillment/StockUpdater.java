package orderfulfillment;

public class StockUpdater implements Runnable {
    private Warehouse warehouse;
    private String productId;
    private int quantity;

    public StockUpdater(Warehouse warehouse, String productId, int quantity) {
        this.warehouse = warehouse;
        this.productId = productId;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        warehouse.addStock(productId, quantity);
    }
}
