package ObserverPattern;
import DataModels.*;

/**
 * Concrete Observer ระบบจัดการคลังสินค้า
 */
public class InventoryService implements OrderObserver {

    @Override
    public void update(Order order) {
        System.out.println("[Inventory Service] Stock has been update for order: " + order.orderId());
    }
    
}
