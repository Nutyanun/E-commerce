import java.util.List;
import DataModels.*;
import DecoratorPattern.*;
import FactoryMethodPattern.*;
import ObserverPattern.*;
import StrategyPattern.*;

public class ManualTestRunner {
    public static void main(String[] args) {
        System.out.println("--- E-commerce Simulation ---");

        // ---1.Setup---
        Product labtop = new Product("P001" , "Laptop" , 30000.0);
        Product mouse = new Product("P002" , "Mouse" , 800.0);
        Order myOrder = new Order("ORD-001" , List.of(labtop, mouse), "customer@example.com");

        OrderCalculator calculator = new OrderCalculator();
        ShipmentFactory shipmentFactory = new ShipmentFactory();

        OrderProcessor orderProcessor = new OrderProcessor();
        InventoryService inventory = new InventoryService();
        EmailService emailer = new EmailService();
        orderProcessor.register(inventory);
        orderProcessor.register(emailer);

        System.out.println("\n--- 2. Testing Strategy Pattern (Disconts) ---");
        double originalPrice = myOrder.getTotalPrice();
        System.out.println("Original Price: " + originalPrice);

        DiscountStrategy tenPercentOff = new PercentageDiscount(10);
        double priceAfterPercentage = calculator.calculateFinalPrice(myOrder, tenPercentOff);
        System.out.println("Price with 10% discount: " + priceAfterPercentage);

        DiscountStrategy fiveHundredOff = new FixedDiscount(500);
        double priceAfterFixed = calculator.calculateFinalPrice(myOrder, fiveHundredOff);
        System.out.println("Price with 500 THB discount: " + priceAfterFixed);

        System.out.println("\n--- 3. Testing Factory and Decorator Patterns (shipment) ---");
        //การสร้างการจัดการแบบมาตรฐาน
        Shipment standardShipment = shipmentFactory.createShipment("STANDARD");
        System.out.println("Base Shipment: " + standardShipment.getInfo() + ", Cost: " + standardShipment.getCost());

        // "ห่อ" ด้วยบริการห่อของขวัญ
        Shipment giftWrapped = new GiftWrapDecorator(standardShipment);
        System.out.println("Decorated: " + giftWrapped.getInfo() + ", Cost " + giftWrapped.getCost());

        // "ห่อ" ทับด้วยประกันสินค้า
        Shipment fullyloedad = new InsuranceDecorator(giftWrapped, myOrder);
        System.out.println("Fully Decorated: " + fullyloedad.getInfo() + ", Cost " + fullyloedad.getCost());

        System.out.println("\n--- 4.Printing Final Summary ---");
        double finalPrice = priceAfterPercentage; // สมมุติว่าใช้ส่วนลด 10%
        double totalCost = finalPrice + fullyloedad.getCost();
        System.out.println("Final price after discount: " + finalPrice);
        System.out.println("Final shipment cost: " + fullyloedad.getCost());
        System.out.println("TOTAL TO PAY: " + totalCost);

        // --- 5. Testing Observer Panttern (Processing Order) ---
        orderProcessor.processOrder(myOrder);
        

    }
}
